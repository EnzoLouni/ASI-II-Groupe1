package com.cpe.irc5.asi2.grp1.user_manager.controller.privates;

import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.cpe.irc5.asi2.grp1.user_manager.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageNotWriteableException;
import javax.validation.Valid;
import java.net.ConnectException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class UserPrivateController {

    private final UserService userService;

    @PostMapping("/auth")
    UserDto authentication(String login, String password) {
        return userService.canCredentialsMatch(login, password);
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable(name = "id") Integer id, @RequestBody @Valid UserDto newUser) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        userService.updateUserRequestFromUser(id,newUser);
    }
}
