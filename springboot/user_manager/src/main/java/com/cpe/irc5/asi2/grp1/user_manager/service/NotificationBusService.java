package com.cpe.irc5.asi2.grp1.user_manager.service;

import com.cpe.irc5.asi2.grp1.commons.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationBusService implements BusService {

    private final JmsTemplate jmsTemplate;

    @Value("${notification.busName}")
    private String busName;
    @Override
    public void pushInQueue(ActiveMQTextMessage content) {
        log.info("[" + busName + "] enqueued a message");
        jmsTemplate.convertAndSend(busName,content);
    }
}
