package com.cpe.irc5.asi2.grp1.card_manager.config;

import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.card_manager.mapper.CardMapper;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardModelService;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardReferenceService;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.net.ConnectException;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toBusMessage;


@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {

    private final CardMapper cardMapper;

    private final CardModelService cardModelService;
    private final CardReferenceService cardReferenceService;

    @Value("${card.busName}")
    private String busName;

    private final ObjectMapper mapper = new ObjectMapper();

    @JmsListener(destination = "${card.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) {
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info("[{}] dequeued message with Group ID: {}", busName, busMessage.getGroupID());
            if(busMessage.getGroupID().equals(GroupID.Cards)) {
                if(busMessage.getRequestType().equals(RequestType.PUT)) {
                    if(busMessage.getOrigin().equals(RequestOrigin.IN)) {
                        CardDto cardToUpdate = mapper.convertValue(busMessage.getDataBusObject(), CardDto.class);
                        cardReferenceService.updateCardReference(cardToUpdate.getId(), cardMapper.toCardReference(cardToUpdate));
                    }
                }  else if(busMessage.getRequestType().equals(RequestType.POST)) {
                    if(busMessage.getOrigin().equals(RequestOrigin.OUT)) {
                        UserDto newUser = mapper.convertValue(busMessage.getDataBusObject(), UserDto.class);
                        cardModelService.createCardsForUser(newUser.getId());
                    }
                    else if(busMessage.getOrigin().equals(RequestOrigin.IN)) {
                        CardDto cardToUpdate = mapper.convertValue(busMessage.getDataBusObject(), CardDto.class);
                        cardReferenceService.createCardReference(cardMapper.toCardReference(cardToUpdate));
                    }
                }  else if(busMessage.getRequestType().equals(RequestType.BUY) || busMessage.getRequestType().equals(RequestType.SELL)) {
                    if(busMessage.getOrigin().equals(RequestOrigin.OUT)) {
                        CardDto cardToUpdate = mapper.convertValue(busMessage.getDataBusObject(), CardDto.class);
                        cardModelService.updateCard(cardToUpdate.getId(), busMessage.getRequestType(), cardToUpdate);
                    }
                }
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ConnectException e) {
            throw new RuntimeException(e);
        }
    }
}
