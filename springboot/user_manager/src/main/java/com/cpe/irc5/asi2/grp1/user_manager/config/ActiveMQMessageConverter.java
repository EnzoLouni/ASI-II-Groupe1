package com.cpe.irc5.asi2.grp1.user_manager.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MessageNotWriteableException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ActiveMQMessageConverter {

    private static ObjectMapper objectMapper = new ObjectMapper();
    public static ActiveMQTextMessage toTextMessage(Map<String, Object> content) throws MessageNotWriteableException, JsonProcessingException {
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        message.setGroupID(content.get("head").toString());
        content.remove("head");
        message.setText(objectMapper.writeValueAsString(content));
        return message;
    }

    public static Map<String, Object> toMap(ActiveMQTextMessage message) throws JMSException, JsonProcessingException {
        Map<String, Object> mapOfData = objectMapper.readValue(message.getText(), Map.class);
        return mapOfData;
    }
}
