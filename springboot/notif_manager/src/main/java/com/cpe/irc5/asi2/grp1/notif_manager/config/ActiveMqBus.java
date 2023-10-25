package com.cpe.irc5.asi2.grp1.notif_manager.config;

import com.cpe.irc5.asi2.grp1.notif_manager.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.Map;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toMap;

@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {

    private final NotificationService notificationService;

    @Value("${notification.busName}")
    private String busName;

    @JmsListener(destination = "${notification.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) throws JMSException, JsonProcessingException {
        log.info("[" + busName + "] dequeued message with Group ID: " + content.getGroupID());
        Map<String, Object> mapOfData = toMap(content);
        log.info("Content: " + mapOfData);
    }
}
