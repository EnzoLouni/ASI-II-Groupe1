package com.cpe.irc5.asi2.grp1.commons.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;

@Service
public interface Bus {
    void pushInQueue(ObjectNode content, String busName) throws MessageNotWriteableException, JsonProcessingException, ConnectException;
}
