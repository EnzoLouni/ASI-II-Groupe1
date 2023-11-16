package com.cpe.irc5.asi2.grp1.commons.config;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.commons.model.DataBusObject;
import com.cpe.irc5.asi2.grp1.commons.service.Bus;
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
    public static ActiveMQTextMessage toTextMessage(BusMessage content) throws MessageNotWriteableException, JsonProcessingException {
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        message.setText(objectMapper.writeValueAsString(content));
        return message;
    }

    public static BusMessage toBusMessage(ActiveMQTextMessage message) throws JMSException, JsonProcessingException {
        ObjectNode objectNode = (ObjectNode) objectMapper.readTree(message.getText());
        GroupID groupID = objectMapper.convertValue(objectNode.get("groupID"), GroupID.class);
        RequestType requestType = objectMapper.convertValue(objectNode.get("requestType"), RequestType.class);
        RequestOrigin origin = objectMapper.convertValue(objectNode.get("origin"), RequestOrigin.class);
        String socketId = objectMapper.convertValue(objectNode.get("socketId"), String.class);
        Class classOfDataBusObject = objectMapper.convertValue(objectNode.get("classOfDataBusObject"), Class.class);
        DataBusObject dataBusObject = (DataBusObject) objectMapper.convertValue(objectNode.get("dataBusObject"), classOfDataBusObject);
        return BusMessage.builder()
                .groupID(groupID)
                .requestType(requestType)
                .origin(origin)
                .socketId(socketId)
                .classOfDataBusObject(classOfDataBusObject)
                .dataBusObject(dataBusObject)
                .build();
    }
}
