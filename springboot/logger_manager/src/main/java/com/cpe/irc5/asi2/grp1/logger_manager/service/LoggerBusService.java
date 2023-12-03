package com.cpe.irc5.asi2.grp1.logger_manager.service;


import com.cpe.irc5.asi2.grp1.logger_manager.LoggerManagerApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;

@Service
public class LoggerBusService {


    @Value("${user.busName}")
    private String userBusName;

    @Value("${chat.busName}")
    private String chatBusName;

    @Value("${store.busName}")
    private String storeBusName;

    @Value("${notification.busName}")
    private String notifBusName;

    @Inject
    private JmsTemplate jmsTemplate;

    @Inject
    private ObjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(LoggerBusService.class);

    private void doReceive(String busName, TextMessage message) {

        try {
            // String clazz = message.getStringProperty("ObjectType");

            Object logMessage = mapper.readValue(message.getText(), Object.class);

            logger.info("Bus {} Received message: {}", busName, logMessage.toString());

            System.out.println("[BUSLISTENER] [CHANNEL "+busName+"] RECEIVED Log MSG=["+message.getText()+"]");
        } catch (IOException | JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "userQueue", containerFactory = "queueConnectionFactory")
    public void receiveMessageUser(TextMessage message) {
        doReceive(userBusName, message);
    }

    @JmsListener(destination = "chatQueue", containerFactory = "queueConnectionFactory")
    public void receiveMessageChat(TextMessage message) {
        doReceive(chatBusName, message);
    }


    @JmsListener(destination = "storeQueue", containerFactory = "queueConnectionFactory")
    public void receiveMessageStore(TextMessage message) {
        doReceive(storeBusName, message);
    }

    @JmsListener(destination = "notificationQueue", containerFactory = "queueConnectionFactory")
    public void receiveMessageNotification(TextMessage message) {
        doReceive(notifBusName, message);
    }


}
