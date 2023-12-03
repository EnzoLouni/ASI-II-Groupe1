package com.cpe.irc5.asi2.grp1.card_manager.service;

import com.cpe.irc5.asi2.grp1.card_manager.bus.CardBusService;
import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.card_manager.mapper.CardMapper;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.card_manager.repository.CardModelRepository;
import com.cpe.irc5.asi2.grp1.card_manager.repository.CardReferenceRepository;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.store_manager.bus.StoreBusService;
import com.cpe.irc5.asi2.grp1.user_manager.bus.UserBusService;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.CARD_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Slf4j
@Service
public class CardModelService {

    private final CardModelRepository cardModelRepository;
    private final CardReferenceRepository cardReferenceRepository;

    private final CardMapper cardMapper;

    private final CardReferenceService cardReferenceService;
    private final CardBusService cardBusService;
    private final UserBusService userBusService;
    private final StoreBusService storeBusService;

    public CardDto getCard(Integer cardId) {
        log.info("Getting Card with ID {}", cardId);
        return cardModelRepository.findById(cardId).map(cardMapper::toCardDto).orElse(null);
    }

    public List<CardDto> getCards() {
        log.info("Getting all Cards");
        return StreamSupport.stream(cardModelRepository.findAll().spliterator(),false).map(cardMapper::toCardDto).collect(toList());
    }

    public List<CardDto> getCardsByUser(Integer userId) {
        log.info("Getting all Cards By User");
        List<CardModel> cardModels = cardModelRepository.findAllByUserId(userId);
        Collections.sort(cardModels);
        return StreamSupport.stream(cardModels.spliterator(),false).map(cardMapper::toCardDto).collect(toList());
    }

    public List<CardDto> getCardsToSell() {
        log.info("Getting all Cards to Sell");
        List<CardModel> cardModels = cardModelRepository.findCardModelByUserIdIsNull();
        Collections.sort(cardModels);
        return StreamSupport.stream(cardModels.spliterator(),false).map(cardMapper::toCardDto).collect(toList());
    }

    public void createCardRequest(UserDto newUser) throws JsonProcessingException, MessageNotWriteableException, ConnectException {
        log.info("Create Card Request received");
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Cards)
                .requestType(RequestType.POST)
                .origin(RequestOrigin.OUT)
                .dataBusObject(newUser)
                .classOfDataBusObject(newUser.getClass())
                .build();
        cardBusService.pushInQueue(busMessage);
    }

    public void createCardsForUser(Integer userId) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Creating 5 random cards for User with ID {}", userId);
        List<CardModel> cardsCreated = new ArrayList<>();
        CardDto witnessCard = new CardDto();
        witnessCard.setUserDto(UserDto.builder()
                .id(userId)
                .build());
        try {
            for(CardReference cardReference: cardReferenceService.getRandomCardReferences()) {
                cardsCreated.add(createCardWithReference(cardReference, userId));
            }
            witnessCard = cardsCreated.stream().map(cardMapper::toCardDto).collect(toList()).get(0);
        } catch (Exception e) {
            log.error("Creation of cards failed");
            for(CardModel cardCreated : cardsCreated) {
                cardModelRepository.deleteById(cardCreated.getId());
            }
        } finally {
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Users)
                    .requestType(RequestType.CALLBACK)
                    .origin(RequestOrigin.OUT)
                    .dataBusObject(witnessCard)
                    .classOfDataBusObject(witnessCard.getClass())
                    .build();
            userBusService.pushInQueue(busMessage);
        }
    }

    private CardModel createCardWithReference(CardReference cardReference, Integer userId) throws EmptyResultDataAccessException {
        CardModel newCard = CardModel.builder()
                .hp(Math.floor(Math.random()*10000)/100)
                .attack(Math.floor(Math.random()*10000)/100)
                .defense(Math.floor(Math.random()*10000)/100)
                .energy(Math.floor(Math.random()*10000)/100)
                .price(Math.floor(Math.random()*100000)/100)
                .userId(userId)
                .cardReferenceId(cardReference.getId())
                .build();
        return cardModelRepository.save(newCard);
    }

    public void updateCardRequest(Integer id, RequestType type, CardDto cardToUpdate) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Updating Card Request received");
        cardToUpdate.setId(id);
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Cards)
                .requestType(type)
                .origin(RequestOrigin.OUT)
                .dataBusObject(cardToUpdate)
                .classOfDataBusObject(cardToUpdate.getClass())
                .build();
        cardBusService.pushInQueue(busMessage);
    }

    public void updateCard(Integer cardId, RequestType type,CardDto cardToUpdate) throws CannotCreateTransactionException, MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Update Card with ID: {}", cardToUpdate.getId());
        CardModel currentCard = null;
        try {
            currentCard = cardModelRepository.findById(cardId).get();
            cardToUpdate.setId(cardId);
            currentCard = cardModelRepository.save(cardMapper.toCardModel(cardToUpdate));
        } catch(EmptyResultDataAccessException e) {
            log.error(CARD_NOT_FOUND, cardToUpdate.getName());
            cardModelRepository.save(currentCard);
        } finally {
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Stores)
                    .requestType(type)
                    .origin(RequestOrigin.OUT)
                    .socketId(UUID.randomUUID().toString())
                    .dataBusObject(cardMapper.toCardDto(currentCard))
                    .classOfDataBusObject(CardDto.class)
                    .build();
            storeBusService.pushInQueue(busMessage);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeMarket() {
        List<CardReference> newCardReferences = StreamSupport.stream(cardReferenceRepository.findAll().spliterator(),false).collect(toList());
        List<CardModel> cardsAlreadyOnMarket = StreamSupport.stream(cardModelRepository.findAll().spliterator(),false).collect(toList());
        if(cardsAlreadyOnMarket.size() == 0) {
            log.info("Initializing Market");
            for(CardReference cardReference: newCardReferences) {
                createCardWithReference(cardReference,null);
            }
        }
    }
}
