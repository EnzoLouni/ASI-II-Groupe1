package com.cpe.irc5.asi2.grp1.store_manager.service;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.notif_manager.publicnotif.bus.NotificationBusService;
import com.cpe.irc5.asi2.grp1.store_manager.mapper.StoreMapper;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreOrder;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreTransactionDto;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.enums.StoreAction;
import com.cpe.irc5.asi2.grp1.store_manager.repository.StoreRepository;
import com.cpe.irc5.asi2.grp1.user_manager.client.UserClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.GROUP;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.TYPE;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    //private final CardClient cardClient;
    private final UserClient userClient;

    private final StoreBusService storeBusService;

    private final NotificationBusService notificationBusService;

    private static final ObjectMapper mapper = new ObjectMapper();

    public List<StoreTransactionDto> getTransactions() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false).map(storeMapper::toStoreTransactionDto).collect(toList());
    }

    public void sellRequest(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Sell Request received");
        ObjectNode objectNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(order));
        objectNode.put(GROUP, GroupID.Stores.name());
        objectNode.put(TYPE, StoreAction.SELL.name());
        storeBusService.pushInQueue(objectNode);
    }

    public void sell(StoreOrder order) {
        /*CardDto cardSold = cardClient.getCard(order.getCardId());
        UserDto seller = userClient.getUser(order.getUserId());
        if(cardSold.getUserId() == order.getUserId() && seller.getCardDtos().contains(cardSold)) {
            cardSold.setUserId(null);
            cardClient.updateCard(cardSold.getId(), cardSold);
            seller.setWallet(seller.getWallet() + cardSold.getPrice());
            userClient.updateUser(seller.getId(), seller);
            StoreTransactionDto newStoreTransactionDto = StoreTransactionDto.builder()
                    .cardDto(cardSold)
                    .userDto(userClient.getUser(order.getUserId()))
                    .action(StoreAction.SELL)
                    .timestamp(new Date())
                    .build();
            storeRepository.save(storeMapper.toStoreTransaction(newStoreTransactionDto));
        }*/
    }

    public void buyRequest(StoreOrder order) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Buy Request received");
        ObjectNode objectNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(order));
        objectNode.put(GROUP, GroupID.Stores.name());
        objectNode.put(TYPE, StoreAction.SELL.name());
        storeBusService.pushInQueue(objectNode);
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
