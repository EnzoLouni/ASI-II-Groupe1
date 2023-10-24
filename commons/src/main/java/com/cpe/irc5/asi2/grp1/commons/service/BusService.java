package com.cpe.irc5.asi2.grp1.commons.service;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Service;

@Service
public interface BusService {
    void pushInQueue(ActiveMQTextMessage content);
}
