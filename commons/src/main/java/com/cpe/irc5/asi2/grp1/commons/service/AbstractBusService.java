package com.cpe.irc5.asi2.grp1.commons.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toTextMessage;

@AllArgsConstructor
@Slf4j
@Service
public abstract class AbstractBusService implements Bus {

    private JmsTemplate jmsTemplate;
    @Override
    public void pushInQueue(ObjectNode content, String busName) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("[{}] enqueued a message", busName);
        try {
            ActiveMQTextMessage message = toTextMessage(content);
            jmsTemplate.convertAndSend(busName,message);
        } catch(UncategorizedJmsException e) {
            log.error("Connection to bus {} failed", busName);
            throw new ConnectException();
        }
    }
}
