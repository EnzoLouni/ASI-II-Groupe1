package com.cpe.irc5.asi2.grp1.chatHisto_manager.service;


import com.cpe.irc5.asi2.grp1.chatHisto_manager.dto.ChatHistoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.Date;

@Service
public class ChatBusListener {


    @Value("${user.busName}")
    private String busName;

    @Inject
    private JmsTemplate jmsTemplate;


    @Inject
    private ChatHistoService chatHistoService;

    @Inject
    private ObjectMapper mapper;


    private void doReceive(String busName, TextMessage message) {

        try {
            String clazz = message.getStringProperty("ObjectType");

            ChatHistoDTO histo = mapper.readValue(message.getText(), ChatHistoDTO.class);

            if (histo instanceof ChatHistoDTO) {
                ChatHistoDTO chatHistoDTO = histo;

                chatHistoService.createChatHisto(chatHistoDTO);
            }
            System.out.println("[BUSLISTENER] [CHANNEL "+busName+"] RECEIVED String MSG=["+message.getText()+"]");
        } catch (IOException | JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "chatQueue", containerFactory = "queueConnectionFactory")
    public void receiveMessageResult(TextMessage message) {
        doReceive(busName, message);
    }


}
