package com.cpe.irc5.asi2.grp1.auth_manager.controller;

import com.cpe.irc5.asi2.grp1.auth_manager.dto.AuthDto;
import com.cpe.irc5.asi2.grp1.auth_manager.service.AuthenticationService;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageNotWriteableException;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/auth")
    public UserDto authentication(@RequestBody @Valid AuthDto request) {
        return authenticationService.authenticate(request);
    }
}
