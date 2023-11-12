package com.cpe.irc5.asi2.grp1.commons.config;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MessageNotWriteableException;

import java.util.UUID;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.GROUP;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.TYPE;

@RequiredArgsConstructor
@Component
public class ActiveMQMessageConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static ActiveMQTextMessage toTextMessage(ObjectNode content) throws MessageNotWriteableException, JsonProcessingException {
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        GroupID groupID = objectMapper.convertValue(content.get(GROUP), GroupID.class);
        RequestType requestType = objectMapper.convertValue(content.get(TYPE), RequestType.class);
        String userId = objectMapper.convertValue(content.get("userId"), String.class);
        message.setGroupID(groupID.name());
        content.remove(GROUP);
        if(requestType != null) {
            message.setType(requestType.name());
            content.remove(TYPE);
        }
        if(userId != null) {
            message.setUserID(userId);
            content.remove("userId");
        }
        message.setText(objectMapper.writeValueAsString(content));
        return message;
    }

    public static ObjectNode toObjectNode(ActiveMQTextMessage message) throws JMSException, JsonProcessingException {
        return objectMapper.readValue(message.getText(), ObjectNode.class);
    }
}
