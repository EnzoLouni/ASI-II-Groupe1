package com.cpe.irc5.asi2.grp1.user_manager.service;


import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.user_manager.model.User;
import com.cpe.irc5.asi2.grp1.user_manager.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.util.HashMap;
import java.util.Map;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toTextMessage;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserBusService userBusService;
    private final NotificationBusService notificationBusService;

    public void pushInQueue(Map<String, Object> content) throws MessageNotWriteableException, JsonProcessingException {
        userBusService.pushInQueue(toTextMessage(content));
    }
    public void canCredentialsMatch(String login, String password) throws MessageNotWriteableException, JsonProcessingException {
        log.info("Matching Login with Password");

        Map<String, Object> response = new HashMap<>();
        response.put("head", GroupID.Authentication.name());

        String hashedPassword = DigestUtils.sha256Hex(password);
        User userFound = userRepository.findUserByLoginAndPassword(login, hashedPassword);

        if(userFound != null)
            response.put("toConnect", true);
        else
            response.put("toConnect", false);

        notificationBusService.pushInQueue(toTextMessage(response));
    }
}
