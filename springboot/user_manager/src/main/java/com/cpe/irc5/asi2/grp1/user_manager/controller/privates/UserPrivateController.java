package com.cpe.irc5.asi2.grp1.user_manager.controller.privates;

import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.cpe.irc5.asi2.grp1.user_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class UserPrivateController {

    private final UserService userService;

    @PostMapping("/auth")
    boolean authentication(String login, String password) {
        return userService.canCredentialsMatch(login, password);
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }
}
