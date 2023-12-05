package com.cpe.irc5.asi2.grp1.logger_manager.config;

import com.cpe.irc5.asi2.grp1.card_manager.bus.CardBusService;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.notif_manager.bus.NotificationBusService;
import com.cpe.irc5.asi2.grp1.store_manager.bus.StoreBusService;
import com.cpe.irc5.asi2.grp1.user_manager.bus.UserBusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

import java.net.ConnectException;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toBusMessage;

@RequiredArgsConstructor
@Service
@Slf4j
public class ActiveMqBus {

    @Value("${user.busName}")
    private String userBusName;

    @Value("${card.busName}")
    private String cardBusName;

    @Value("${store.busName}")
    private String storeBusName;

    @Value("${notification.busName}")
    private String notificationBusName;

    @Value("${chat.busName}")
    private String chatBusName;

    private final UserBusService userBusService;
    private final CardBusService cardBusService;
    private final StoreBusService storeBusService;
    private final NotificationBusService notificationBusService;

    @JmsListener(destination = "${user.busName}", containerFactory = "activeMqFactory")
    public void messageUser(ActiveMQTextMessage content) {
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info(busMessage.getDataBusObject().toString());
            userBusService.pushInQueue(busMessage, userBusName);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ConnectException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "${card.busName}", containerFactory = "activeMqFactory")
    public void messageCard(ActiveMQTextMessage content) {
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info(busMessage.getDataBusObject().toString());
            cardBusService.pushInQueue(busMessage, cardBusName);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ConnectException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "${store.busName}", containerFactory = "activeMqFactory")
    public void messageStore(ActiveMQTextMessage content) {
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info(busMessage.getDataBusObject().toString());
            storeBusService.pushInQueue(busMessage, storeBusName);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ConnectException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "${notification.busName}", containerFactory = "activeMqFactory")
    public void messageNotification(ActiveMQTextMessage content) {
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info(busMessage.getDataBusObject().toString());
            notificationBusService.pushInQueue(busMessage, notificationBusName);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ConnectException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "${chat.busName}", containerFactory = "activeMqFactory")
    public void messageChat(ActiveMQTextMessage content) {
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info(busMessage.getDataBusObject().toString());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
