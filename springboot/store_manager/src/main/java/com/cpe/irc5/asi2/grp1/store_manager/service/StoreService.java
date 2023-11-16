package com.cpe.irc5.asi2.grp1.store_manager.service;

import com.cpe.irc5.asi2.grp1.card_manager.client.CardClient;
import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.notif_manager.bus.NotificationBusService;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
import com.cpe.irc5.asi2.grp1.store_manager.bus.StoreBusService;
import com.cpe.irc5.asi2.grp1.store_manager.dto.StoreOrder;
import com.cpe.irc5.asi2.grp1.store_manager.dto.StoreTransactionDto;
import com.cpe.irc5.asi2.grp1.store_manager.mapper.StoreMapper;
import com.cpe.irc5.asi2.grp1.store_manager.repository.StoreRepository;
import com.cpe.irc5.asi2.grp1.user_manager.client.UserClient;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.SUCCESS;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    private final CardClient cardClient;
    private final UserClient userClient;

    private final StoreBusService storeBusService;

    private final NotificationBusService notificationBusService;

    private static final ObjectMapper mapper = new ObjectMapper();

    public List<StoreTransactionDto> getTransactions() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false).map(storeMapper::toStoreTransactionDto).collect(toList());
    }

    public void sellRequest(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Sell Request received");
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Stores)
                .origin(RequestOrigin.IN)
                .dataBusObject(order)
                .classOfDataBusObject(order.getClass())
                .build();
        storeBusService.pushInQueue(busMessage);
    }

    public void sell(StoreOrder order) {
        CardDto cardSold = cardClient.getCard(order.getCardId());
        UserDto seller = userClient.getUser(order.getUserId());
        //if(cardSold.getUserDto().getId() == order.getUserId() && seller.getCardDtos().contains(cardSold)) {
            cardSold.setUserDto(null);
            cardClient.updateCard(cardSold.getId(), cardSold);
            seller.setWallet(seller.getWallet() + cardSold.getPrice());
            /*StoreTransactionDto newStoreTransactionDto = StoreTransactionDto.builder()
                    .cardDto(cardSold)
                    .userDto(userClient.getUser(order.getUserId()))
                    .action(StoreAction.SELL)
                    .timestamp(new Date())
                    .build();
            storeRepository.save(storeMapper.toStoreTransaction(newStoreTransactionDto));*/
        //}
    }

    public void callBackSell(CardDto soldCard) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("CallBack For Store Sell Called");
        NotificationResponse response = new NotificationResponse();
        UserDto userDto = soldCard.getUserDto();
        if(userDto.getLogin() == null) {
            response.setMessage(SUCCESS);
            response.setOperationsWereMade(true);
        }
    }

    public void buyRequest(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Buy Request received");
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Stores)
                .origin(RequestOrigin.IN)
                .dataBusObject(order)
                .classOfDataBusObject(order.getClass())
                .build();
        storeBusService.pushInQueue(busMessage);
    }

    public void buy(StoreOrder order) {
        /*CardDto cardBought = cardClient.getCard(order.getCardId());
        UserDto newOwner = userClient.getUser(order.getUserId());
        Double remainingBalance = (newOwner.getWallet() - cardBought.getPrice());
        if(cardBought.getUserId() == null &&  remainingBalance >= 0) {
            newOwner.setWallet(remainingBalance);
            cardBought.setUserId(newOwner.getId());
            cardClient.updateCard(cardBought.getId(), cardBought);
            userClient.updateUser(newOwner.getId(), newOwner);
            StoreTransactionDto newStoreTransactionDto = StoreTransactionDto.builder()
                    .cardDto(cardBought)
                    .userDto(newOwner)
                    .action(StoreAction.BUY)
                    .timestamp(new Date())
                    .build();
            storeRepository.save(storeMapper.toStoreTransaction(newStoreTransactionDto));
        }*/
    }
}
