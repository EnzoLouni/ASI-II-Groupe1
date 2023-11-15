package com.cpe.irc5.asi2.grp1.card_manager.service;

import com.cpe.irc5.asi2.grp1.card_manager.mapper.CardMapper;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.card_manager.repository.CardModelRepository;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.public_card.dtos.CardDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.inject.Inject;
import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class CardModelService {

    @Inject
    private CardModelRepository cardRepository;

    @Inject
    private CardReferenceService cardRefService;

    @Inject
    private CardMapper cardMapper;

    @Inject
    private CardBusService cardBusService;

    // private final NotificationBusService notificationBusService;

    private static final ObjectMapper mapper = new ObjectMapper();
    private Random rand;

    /* public CardModelService(CardModelRepository cardRepository, CardReferenceService cardRefService, CardMapper cardMapper, CardBusService cardBusService) {
        this.cardMapper = cardMapper;
        this.cardBusService = cardBusService;
        this.rand=new Random();
        // Dependencies injection by constructor
        this.cardRepository=cardRepository;
        this.cardRefService=cardRefService;
    } */

    public List<CardModel> getAllCardModel() {
        List<CardModel> cardList = new ArrayList<>();
        cardRepository.findAll().forEach(cardList::add);
        return cardList;
    }

    public void addCardRequest(CardDTO cardDTO) throws JsonProcessingException, MessageNotWriteableException, ConnectException {

        log.info("Create Request received");
        ObjectNode objectNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(cardDTO));
        objectNode.put(GROUP, GroupID.Cards.name());
        objectNode.put(TYPE, RequestType.POST.name());
        cardBusService.pushInQueue(objectNode);
    }

    public void addCard(CardModel cardAdded) throws CannotCreateTransactionException {

        log.info("Update User with ID: {}", cardAdded.getId());
        try {
            cardRepository.save(cardAdded);
        } catch(EmptyResultDataAccessException e) {
            log.error(USER_NOT_FOUND, cardAdded.getId());
        }
    }

    public void updateCardRequest(Integer id, CardDTO cardUpdated) throws JsonProcessingException, MessageNotWriteableException, ConnectException {

        log.info("Update Request received");
        cardUpdated.setUserId(id);
        ObjectNode objectNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(cardUpdated));
        objectNode.put(GROUP, GroupID.Cards.name());
        objectNode.put(TYPE, RequestType.PUT.name());
        cardBusService.pushInQueue(objectNode);
    }

    public void updateCard(Integer id, CardDTO cardUpdated) throws CannotCreateTransactionException {

        log.info("Update User with ID: {}", cardUpdated.getId());
        try {
            cardRepository.findByUser(id);
            cardUpdated.setId(id);
            cardRepository.save(cardMapper.fromCardDtoToCardModel(cardUpdated));
        } catch(EmptyResultDataAccessException e) {
            log.error(USER_NOT_FOUND, id);
        }
    }


    public Optional<CardModel> getCard(Integer id) {
        return cardRepository.findById(id);
    }

    public void deleteCardModelRequest(Integer id) throws MessageNotWriteableException, JsonProcessingException, ConnectException {

        log.info("Delete Request received");
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put(GROUP, GroupID.Cards.name());
        objectNode.put(TYPE, RequestType.DELETE.name());
        objectNode.put("id", id);
        this.cardBusService.pushInQueue(objectNode);
    }

    public void deleteCardModel(Integer id) throws CannotCreateTransactionException {

        log.info("Delete User with ID: {}", id);
        try {
            cardRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            log.error(USER_NOT_FOUND, id);
        }
    }

    public List<CardModel> getRandCard(int nbr) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        List<CardModel> cardList=new ArrayList<>();
        for(int i=0;i<nbr;i++) {
            CardReference currentCardRef=cardRefService.getRandCardRef();
            CardModel currentCard=new CardModel(currentCardRef);
            currentCard.setAttack(rand.nextFloat()*100);
            currentCard.setDefence(rand.nextFloat()*100);
            currentCard.setEnergy(100);
            currentCard.setHp(rand.nextFloat()*100);
            currentCard.setPrice(currentCard.computePrice());
            //save new card before sending for user creation
            addCardRequest(cardMapper.fromCardModelToCardDTO(currentCard));
            cardList.add(currentCard);
        }
        return cardList;
    }

    public List<CardModel> getAllCardToSell(){
        return this.cardRepository.findByUser(null);
    }

}
