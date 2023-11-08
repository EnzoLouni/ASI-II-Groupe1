package com.cpe.irc5.asi2.grp1.user_manager.controller.publics;

import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.cpe.irc5.asi2.grp1.user_manager.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageNotWriteableException;
import javax.validation.Valid;
import java.net.ConnectException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/public")
public class UserPublicController {

    private final UserService userService;
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable(name = "id") Integer id, @RequestBody @Valid UserDto newUser) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        userService.updateUserRequest(id,newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(name = "id") Integer id) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        userService.deleteUserRequest(id);
    }

    /*@PostMapping("/user")
    public void createUser(@RequestBody @Valid RequestCreationUser request) {
    }*/
}
