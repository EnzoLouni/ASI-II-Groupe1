package com.cpe.irc5.asi2.grp1.store_manager.service;

import com.cpe.irc5.asi2.grp1.store_manager.mapper.StoreMapper;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreTransactionDto;
import com.cpe.irc5.asi2.grp1.store_manager.repository.StoreRepository;
import com.cpe.irc5.asi2.grp1.user_manager.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    //private final CardClient cardClient;
    private final UserClient userClient;

    public List<StoreTransactionDto> getTransactions() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false).map(storeMapper::toStoreTransactionDto).collect(toList());
    }

    /*public boolean sell(StoreOrder order) {
        CardDto cardSold = cardClient.getCard(order.getCardId());
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
            return true;
        }
        return false;
    }

    public boolean buy(StoreOrder order) {
        CardDto cardBought = cardClient.getCard(order.getCardId());
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
            return true;
        }
        return false;
    }*/
}
