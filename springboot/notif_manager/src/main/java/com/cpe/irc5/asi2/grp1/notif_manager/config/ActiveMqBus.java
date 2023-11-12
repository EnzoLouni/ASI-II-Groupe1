package com.cpe.irc5.asi2.grp1.notif_manager.config;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.notif_manager.publicnotif.model.NotificationResponse;
import com.cpe.irc5.asi2.grp1.notif_manager.service.NotificationService;
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
import java.util.UUID;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toObjectNode;

@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {
    private final NotificationService notificationService;

    @Value("${notification.busName}")
    private String busName;

    @JmsListener(destination = "${notification.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) {
        log.info("[{}] dequeued message with Group ID: {}", busName, content.getGroupID());
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectNode objectNode = toObjectNode(content);
            if(content.getGroupID().equals(GroupID.Notifications.name())) {
                notificationService.sendNotificationResponse(UUID.fromString(content.getUserID()), mapper.convertValue(objectNode, NotificationResponse.class));
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
