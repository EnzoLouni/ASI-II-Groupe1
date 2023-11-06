package com.cpe.irc5.asi2.grp1.user_manager.controller.privates;

import com.cpe.irc5.asi2.grp1.user_manager.service.UserBusService;
import com.cpe.irc5.asi2.grp1.user_manager.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageNotWriteableException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class UserPrivateController {

    private final UserService userService;
    private final UserBusService userBusService;

    @PostMapping("/auth")
    boolean authentication(String login, String password) {
        return userService.canCredentialsMatch(login, password);
    }
}
