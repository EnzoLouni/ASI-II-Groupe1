package com.cpe.irc5.asi2.grp1.notif_manager.publicnotif.bus;

import com.cpe.irc5.asi2.grp1.commons.service.AbstractBusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;


@Service
public class NotificationBusService extends AbstractBusService {

    @Value("${notification.busName}")
    private String busName;
    public NotificationBusService(JmsTemplate jmsTemplate) {
        super(jmsTemplate);
    }
    public void pushInQueue(ObjectNode content) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        this.pushInQueue(content, busName);
    }
}
