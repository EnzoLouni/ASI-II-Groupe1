package com.cpe.irc5.asi2.grp1.auth_manager.service;

import com.cpe.irc5.asi2.grp1.auth_manager.config.AuthenticationExceptionHandler;
import com.cpe.irc5.asi2.grp1.auth_manager.dto.AuthDto;
import com.cpe.irc5.asi2.grp1.user_manager.client.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthenticationService extends AuthenticationExceptionHandler {

    private final UserClient userClient;
    public boolean authenticate(AuthDto request) {
        log.info("Authentication Process");
        return userClient.authentication(request.getLogin(), request.getPassword());
    }
}
