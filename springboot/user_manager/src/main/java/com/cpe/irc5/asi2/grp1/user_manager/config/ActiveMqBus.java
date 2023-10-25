package com.cpe.irc5.asi2.grp1.user_manager.config;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.user_manager.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.Map;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toMap;

@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {

    private final UserService userService;

    @Value("${user.busName}")
    private String busName;

    @JmsListener(destination = "${user.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) throws JMSException, JsonProcessingException {
        log.info("[" + busName + "] dequeued message with Group ID: " + content.getGroupID());
        Map<String, Object> mapOfData = toMap(content);
        if(content.getGroupID().equals(GroupID.Authentication.name())) {
            String login = mapOfData.get("login").toString();
            String password = mapOfData.get("password").toString();
            userService.canCredentialsMatch(login, password);
        }
    }
}
