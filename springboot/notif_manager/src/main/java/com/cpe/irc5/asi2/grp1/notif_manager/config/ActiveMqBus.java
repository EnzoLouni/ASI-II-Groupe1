package com.cpe.irc5.asi2.grp1.notif_manager.config;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
import com.cpe.irc5.asi2.grp1.notif_manager.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.UUID;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toBusMessage;

@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {
    private final NotificationService notificationService;

    @Value("${notification.busName}")
    private String busName;

    @JmsListener(destination = "${notification.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info("[{}] dequeued message with Group ID: {}", busName, busMessage.getGroupID());
            if(busMessage.getGroupID().equals(GroupID.Notifications)) {
                notificationService.sendNotificationResponse(UUID.fromString(busMessage.getSocketId()), mapper.convertValue(busMessage.getDataBusObject(), NotificationResponse.class));
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
