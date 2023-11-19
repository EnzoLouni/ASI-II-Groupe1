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
import com.cpe.irc5.asi2.grp1.notif_manager.bus.NotificationBusService;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.CARD_NOT_FOUND;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.RESOURCE_NOT_FOUND;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.SUCCESS;
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
        return StreamSupport.stream(cardModelRepository.findAllByUserId(userId).spliterator(),false).map(cardMapper::toCardDto).collect(toList());
    }

    public List<CardDto> getCardsToSell() {
        log.info("Getting all Cards to Sell");
        return StreamSupport.stream(cardModelRepository.findCardModelByUserIdIsNull().spliterator(),false).map(cardMapper::toCardDto).collect(toList());
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
        Random random = new Random();
        CardModel newCard = CardModel.builder()
                .hp(random.nextFloat()*100)
                .attack(random.nextFloat()*100)
                .defense(random.nextFloat()*100)
                .energy(random.nextFloat()*100)
                .price(random.nextFloat()*1000)
                .userId(userId)
                .cardReferenceId(cardReference.getId())
                .build();
        return cardModelRepository.save(newCard);
    }

    public void updateCardRequest(Integer id, CardDto cardToUpdate) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Updating Card Request received");
        cardToUpdate.setId(id);
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Cards)
                .requestType(RequestType.PUT)
                .origin(RequestOrigin.OUT)
                .dataBusObject(cardToUpdate)
                .classOfDataBusObject(cardToUpdate.getClass())
                .build();
        cardBusService.pushInQueue(busMessage);
    }

    public void updateCard(Integer cardId, CardDto cardToUpdate) throws CannotCreateTransactionException, MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Update Card with ID: {}", cardToUpdate.getId());
        CardModel currentCard = null;
        try {
            currentCard = cardModelRepository.findById(cardId).get();
            cardToUpdate.setId(cardId);
            cardModelRepository.save(cardMapper.toCardModel(cardToUpdate));
        } catch(EmptyResultDataAccessException e) {
            log.error(CARD_NOT_FOUND, cardToUpdate.getName());
            cardModelRepository.save(currentCard);
        } finally {
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Stores)
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
