package com.cpe.irc5.asi2.grp1.card_manager.config;

import com.cpe.irc5.asi2.grp1.card_manager.model.CardModel;
import com.cpe.irc5.asi2.grp1.card_manager.model.CardReference;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardModelService;
import com.cpe.irc5.asi2.grp1.card_manager.service.CardReferenceService;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.public_card.dtos.CardDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.jms.JMSException;
import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toObjectNode;


@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {

    @Inject
    private CardModelService CardService;

    @Inject
    private CardReferenceService cardRefService;


    @Value("${user.busName}")
    private String busName;

    @JmsListener(destination = "${user.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) {
        log.info("[{}] dequeued message with Group ID: {}", busName, content.getGroupID());
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectNode objectNode = toObjectNode(content);
            if(content.getGroupID().equals(GroupID.Cards.name())) {

                if(content.getType().equals(RequestType.PUT.name())) {
                    CardDTO cardToUpdate = mapper.convertValue(objectNode, CardDTO.class);
                    CardService.updateCard(cardToUpdate.getId(), cardToUpdate);
                } else if(content.getType().equals(RequestType.DELETE.name())) {
                    Integer userToDeleteId = mapper.convertValue(objectNode.get("id"), Integer.class);
                    CardService.deleteCardModel(userToDeleteId);
                } else if(content.getType().equals(RequestType.POST.name())) {
                    try {
                        CardModel cardToAdd = mapper.convertValue(objectNode, CardModel.class);
                        CardService.addCard(cardToAdd);
                    } catch (Exception e) {
                        CardReference cardRefToAdd = mapper.convertValue(objectNode, CardReference.class);
                        cardRefService.addCardRef(cardRefToAdd);
                    }
                }
            }
        } catch (JMSException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
