package com.cpe.irc5.asi2.grp1.user_manager.service;


import com.cpe.irc5.asi2.grp1.user_manager.model.User;
import com.cpe.irc5.asi2.grp1.user_manager.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.jms.MessageNotWriteableException;
import java.util.Map;

import static com.cpe.irc5.asi2.grp1.user_manager.config.ActiveMQMessageConverter.toTextMessage;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserBusService busService;

    public void pushInQueue(Map<String, Object> content) throws MessageNotWriteableException, JsonProcessingException {
        busService.pushInQueue(toTextMessage(content));
    }
    public void getPasswordByLogin(String login, String password) {
        log.info("Matching Login with Password");
        String hashedPassword = DigestUtils.sha256Hex(password);
        User userFound = userRepository.findUserByLoginAndPassword(login, hashedPassword);
        log.info("User Found: " + userFound);
    }
}
