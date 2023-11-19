package com.cpe.irc5.asi2.grp1.store_manager.service;

import com.cpe.irc5.asi2.grp1.card_manager.client.CardClient;
import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.notif_manager.bus.NotificationBusService;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
import com.cpe.irc5.asi2.grp1.store_manager.bus.StoreBusService;
import com.cpe.irc5.asi2.grp1.store_manager.dto.StoreOrder;
import com.cpe.irc5.asi2.grp1.store_manager.dto.StoreTransactionDto;
import com.cpe.irc5.asi2.grp1.store_manager.enums.StoreAction;
import com.cpe.irc5.asi2.grp1.store_manager.mapper.StoreMapper;
import com.cpe.irc5.asi2.grp1.store_manager.model.StoreTransaction;
import com.cpe.irc5.asi2.grp1.store_manager.model.StoreTransactionTemp;
import com.cpe.irc5.asi2.grp1.store_manager.repository.StoreRepository;
import com.cpe.irc5.asi2.grp1.store_manager.repository.StoreTempRepository;
import com.cpe.irc5.asi2.grp1.user_manager.client.UserClient;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.RESOURCE_NOT_FOUND;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.SUCCESS;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.TRANSACTION_NOT_CREATED;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreTempRepository storeTempRepository;

    private final StoreMapper storeMapper;

    private final CardClient cardClient;
    private final UserClient userClient;

    private final StoreBusService storeBusService;

    private final NotificationBusService notificationBusService;

    public List<StoreTransactionDto> getTransactions() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false).map(storeMapper::toStoreTransactionDto).collect(toList());
    }

    public void sellRequest(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Sell Request received");
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Stores)
                .requestType(RequestType.SELL)
                .origin(RequestOrigin.IN)
                .dataBusObject(order)
                .classOfDataBusObject(order.getClass())
                .build();
        storeBusService.pushInQueue(busMessage);
    }

    public void sell(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Selling Card");
        NotificationResponse response = new NotificationResponse();
        CardDto cardSold = cardClient.getCard(order.getCardId());
        UserDto seller = userClient.getUser(order.getUserId());
        if(cardSold.getUserDto().getId() == seller.getId()) {
            try {
                cardSold.setUserDto(null);
                StoreTransactionTemp newStoreTransaction = StoreTransactionTemp.builder()
                        .action(StoreAction.SELL.name())
                        .cardId(cardSold.getId())
                        .userId(seller.getId())
                        .timestamp(new Date())
                        .build();
                storeTempRepository.save(newStoreTransaction);
                cardClient.updateCard(cardSold.getId(), RequestType.SELL, cardSold);
            } catch (Exception e) {
                log.error(TRANSACTION_NOT_CREATED, seller.getLogin());
                response.setOperationsWereMade(false);
                response.setMessage(RESOURCE_NOT_FOUND);
                response.setErrors(Arrays.asList(String.format(TRANSACTION_NOT_CREATED, seller.getLogin())));
                BusMessage busMessage = BusMessage.builder()
                        .groupID(GroupID.Notifications)
                        .origin(RequestOrigin.OUT)
                        .dataBusObject(order)
                        .classOfDataBusObject(order.getClass())
                        .build();
                notificationBusService.pushInQueue(busMessage);
            }
        }
        else {
            log.error("User {} does no possess this card", seller.getLogin());
            response.setOperationsWereMade(false);
            response.setMessage(RESOURCE_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format("User %s does no possess this card", seller.getLogin())));
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Notifications)
                    .origin(RequestOrigin.OUT)
                    .dataBusObject(order)
                    .classOfDataBusObject(order.getClass())
                    .build();
            notificationBusService.pushInQueue(busMessage);
        }
    }

    public void callBackSell(CardDto cardSold) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("CallBack For Store Sell Called");
        NotificationResponse response = new NotificationResponse();
        UserDto seller = cardSold.getUserDto();
        if(seller == null) {
            try {
                StoreTransactionTemp transactionStored = storeTempRepository.findByCardIdAndAction(cardSold.getId(), StoreAction.SELL.name());
                seller = userClient.getUser(transactionStored.getUserId());
                seller.setWallet(seller.getWallet() + cardSold.getPrice());
                userClient.updateUser(seller.getId(), seller);
                storeTempRepository.delete(transactionStored);
                StoreTransaction newStoreTransaction = new StoreTransaction(transactionStored);
                storeRepository.save(newStoreTransaction);
                response.setMessage(SUCCESS);
                response.setOperationsWereMade(true);
            } catch (Exception e) {
                log.error(TRANSACTION_NOT_CREATED, seller.getLogin());
                response.setOperationsWereMade(false);
                response.setMessage(RESOURCE_NOT_FOUND);
                response.setErrors(Arrays.asList(String.format(TRANSACTION_NOT_CREATED, seller.getLogin())));
            }
        }
        else {
            log.error(TRANSACTION_NOT_CREATED, seller.getLogin());
            response.setOperationsWereMade(false);
            response.setMessage(RESOURCE_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format(TRANSACTION_NOT_CREATED, seller.getLogin())));
        }
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Notifications)
                .origin(RequestOrigin.OUT)
                .dataBusObject(response)
                .classOfDataBusObject(response.getClass())
                .build();
        notificationBusService.pushInQueue(busMessage);
    }

    public void buyRequest(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Buy Request received");
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Stores)
                .requestType(RequestType.BUY)
                .origin(RequestOrigin.IN)
                .dataBusObject(order)
                .classOfDataBusObject(order.getClass())
                .build();
        storeBusService.pushInQueue(busMessage);
    }

    public void buy(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        NotificationResponse response = new NotificationResponse();
        CardDto cardBought = cardClient.getCard(order.getCardId());
        UserDto newOwner = userClient.getUser(order.getUserId());
        Double remainingBalance = (newOwner.getWallet() - cardBought.getPrice());
        if(cardBought.getUserDto() == null &&  remainingBalance >= 0) {
            try {
                newOwner.setWallet(remainingBalance);
                cardBought.setUserDto(newOwner);
                StoreTransactionTemp newStoreTransaction = StoreTransactionTemp.builder()
                        .action(StoreAction.BUY.name())
                        .cardId(cardBought.getId())
                        .userId(newOwner.getId())
                        .timestamp(new Date())
                        .build();
                storeTempRepository.save(newStoreTransaction);
                cardClient.updateCard(cardBought.getId(), RequestType.BUY, cardBought);
            } catch(Exception e) {
                log.error(TRANSACTION_NOT_CREATED, newOwner.getLogin());
                response.setOperationsWereMade(false);
                response.setMessage(RESOURCE_NOT_FOUND);
                response.setErrors(Arrays.asList(String.format(TRANSACTION_NOT_CREATED, newOwner.getLogin())));
            }
        }
        else {
            log.error(TRANSACTION_NOT_CREATED, newOwner.getLogin());
            response.setOperationsWereMade(false);
            response.setMessage(RESOURCE_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format(TRANSACTION_NOT_CREATED, newOwner.getLogin())));
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Notifications)
                    .origin(RequestOrigin.OUT)
                    .dataBusObject(order)
                    .classOfDataBusObject(order.getClass())
                    .build();
            notificationBusService.pushInQueue(busMessage);
        }
    }

    public void callBackBuy(CardDto cardBought) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("CallBack For Store Buy Called");
        NotificationResponse response = new NotificationResponse();
        UserDto buyer = cardBought.getUserDto();
        if(buyer == null) {
            try {
                StoreTransactionTemp transactionStored = storeTempRepository.findByCardIdAndAction(cardBought.getId(), StoreAction.BUY.name());
                buyer = userClient.getUser(transactionStored.getUserId());
                buyer.setWallet(buyer.getWallet() - cardBought.getPrice());
                userClient.updateUser(buyer.getId(), buyer);
                storeTempRepository.delete(transactionStored);
                StoreTransaction storeTransaction = new StoreTransaction(transactionStored);
                storeRepository.save(storeTransaction);
                response.setMessage(SUCCESS);
                response.setOperationsWereMade(true);
            } catch (Exception e) {
                log.error(TRANSACTION_NOT_CREATED, buyer.getLogin());
                response.setOperationsWereMade(false);
                response.setMessage(RESOURCE_NOT_FOUND);
                response.setErrors(Arrays.asList(String.format(TRANSACTION_NOT_CREATED, buyer.getLogin())));
            }
        }
        else {
            log.error(TRANSACTION_NOT_CREATED, buyer.getLogin());
            response.setOperationsWereMade(false);
            response.setMessage(RESOURCE_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format(TRANSACTION_NOT_CREATED, buyer.getLogin())));
        }
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Notifications)
                .origin(RequestOrigin.OUT)
                .dataBusObject(response)
                .classOfDataBusObject(response.getClass())
                .build();
        notificationBusService.pushInQueue(busMessage);
    }
}
