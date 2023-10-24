package com.cpe.irc5.asi2.grp1.user_manager.controller.privates;

import com.cpe.irc5.asi2.grp1.user_manager.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @PostMapping("/stack")
    @Async
    void pushRequestInQueue(@RequestBody Map<String, Object> content) throws MessageNotWriteableException, JsonProcessingException {
        userService.pushInQueue(content);
    }
}
