package com.cpe.irc5.asi2.grp1.card_manager.service;

import com.cpe.irc5.asi2.grp1.card_manager.bus.CardBusService;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.card_manager.repository.CardReferenceRepository;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.notif_manager.bus.NotificationBusService;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.CARD_NOT_CREATED;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.CARD_NOT_FOUND;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.CARD_REFERENCE_NOT_CREATED;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.RESOURCE_NOT_FOUND;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.SUCCESS;

@RequiredArgsConstructor
@Service
@Slf4j
public class CardReferenceService {

    private final CardReferenceRepository cardRepository;

    private final CardBusService cardBusService;
    private final NotificationBusService notificationBusService;

    public void createCardReferenceRequest(CardDto cardDto) throws JsonProcessingException, MessageNotWriteableException, ConnectException {
        log.info("Create Card Reference Request received");
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Cards)
                .requestType(RequestType.POST)
                .origin(RequestOrigin.IN)
                .dataBusObject(cardDto)
                .classOfDataBusObject(cardDto.getClass())
                .build();
        cardBusService.pushInQueue(busMessage);
    }

    public void updateCardReferenceRequest(Integer cardReferenceId, CardDto cardReferenceToUpdate) throws JsonProcessingException, MessageNotWriteableException, ConnectException {
        log.info("Update Card Reference Request received");
        cardReferenceToUpdate.setId(cardReferenceId);
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Cards)
                .requestType(RequestType.PUT)
                .origin(RequestOrigin.IN)
                .dataBusObject(cardReferenceToUpdate)
                .classOfDataBusObject(cardReferenceToUpdate.getClass())
                .build();
        cardBusService.pushInQueue(busMessage);
    }

    public void createCardReference(CardReference cardReference) throws CannotCreateTransactionException, MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Create Card Reference");
        NotificationResponse response = new NotificationResponse();
        try {
            cardRepository.save(cardReference);
            response.setMessage(SUCCESS);
            response.setOperationsWereMade(true);
        } catch(Exception e) {
            log.error(CARD_REFERENCE_NOT_CREATED, cardReference.getName());
            response.setMessage(RESOURCE_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format(CARD_REFERENCE_NOT_CREATED, cardReference.getName())));
            response.setOperationsWereMade(false);
        } finally {
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Notifications)
                    .origin(RequestOrigin.OUT)
                    .socketId(UUID.randomUUID().toString())
                    .dataBusObject(response)
                    .classOfDataBusObject(response.getClass())
                    .build();
            notificationBusService.pushInQueue(busMessage);
        }
    }

    public void updateCardReference(Integer cardReferenceId, CardReference cardReference) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Updating Card Reference with ID {}", cardReferenceId);
        NotificationResponse response = new NotificationResponse();
        try {
            cardRepository.findById(cardReferenceId).get();
            cardReference.setId(cardReferenceId);
            cardRepository.save(cardReference);
            response.setMessage(SUCCESS);
            response.setOperationsWereMade(true);
        } catch(EmptyResultDataAccessException e) {
            log.error(CARD_NOT_FOUND, cardReference.getName());
            response.setMessage(RESOURCE_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format(CARD_NOT_FOUND, cardReference.getName())));
            response.setOperationsWereMade(false);
        } catch(Exception e) {
            log.error(CARD_NOT_CREATED, cardReference.getName());
            response.setMessage(RESOURCE_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format(CARD_NOT_CREATED, cardReference.getName())));
            response.setOperationsWereMade(false);
        } finally {
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Notifications)
                    .origin(RequestOrigin.OUT)
                    .socketId(UUID.randomUUID().toString())
                    .dataBusObject(response)
                    .classOfDataBusObject(response.getClass())
                    .build();
            notificationBusService.pushInQueue(busMessage);
        }
    }

    public List<CardReference> getRandomCardReferences() {
        return cardRepository.findRandomCardReferences();
    }

    public CardReference getCardReference(Integer cardReferenceId) { return cardRepository.findById(cardReferenceId).get(); }

    public CardReference getCardReferenceIdByName(String cardReferenceName) { return cardRepository.findIdByName(cardReferenceName); }
}
