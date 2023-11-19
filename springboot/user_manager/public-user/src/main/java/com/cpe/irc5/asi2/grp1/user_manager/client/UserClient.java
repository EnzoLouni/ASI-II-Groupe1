package com.cpe.irc5.asi2.grp1.user_manager.client;

import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(name = "userClient", url = "${user.ribbon.listOfServers}/userapi")
public interface UserClient {

    @PostMapping("/private/auth")
    UserDto authentication(@RequestParam("login") String login, @RequestParam("password") String password);

    @GetMapping("/public/users/{id}")
    UserDto getUser(@PathVariable(name = "id") Integer id);

    @PutMapping("/private/users/{id}")
    void updateUser(@PathVariable(name = "id") Integer id, @RequestBody @Valid UserDto newUser);
}
