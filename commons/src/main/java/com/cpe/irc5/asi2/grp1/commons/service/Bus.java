package com.cpe.irc5.asi2.grp1.commons.service;

import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;

@Service
public interface Bus {
    void pushInQueue(BusMessage content, String busName) throws MessageNotWriteableException, JsonProcessingException, ConnectException;
}
