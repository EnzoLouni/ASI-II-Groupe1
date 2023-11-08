package com.cpe.irc5.asi2.grp1.card_manager.config;

import com.cpe.irc5.asi2.grp1.card_manager.service.CardModelService;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toObjectNode;

@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {

    private final UserService userService;

    private final CardModelService CardService;


    @Value("${user.busName}")
    private String busName;

    @JmsListener(destination = "${user.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) {
        log.info("[{}] dequeued message with Group ID: {}", busName, content.getGroupID());
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectNode objectNode = toObjectNode(content);
            if(content.getGroupID().equals(GroupID.Users.name())) {
                if(content.getType().equals(RequestType.PUT.name())) {
                    UserDto userToUpdate = mapper.convertValue(objectNode, UserDto.class);
                    //userService.updateUser(userToUpdate.getId(), userToUpdate);
                }
                else if(content.getType().equals(RequestType.DELETE.name())) {
                    Integer userToDeleteId = mapper.convertValue(objectNode.get("id"), Integer.class);
                    CardService.deleteUser(userToDeleteId);
                }
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
