package com.cpe.irc5.asi2.grp1.auth_manager.service;

import com.cpe.irc5.asi2.grp1.auth_manager.dto.AuthDto;
import com.cpe.irc5.asi2.grp1.commons.client.UserClient;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthenticationService {

    private final UserClient userClient;
    public void authenticate(AuthDto request) {
        log.info("Authentication Process");
        Map<String, Object> mapOfAuthentication = new HashMap<>();
        mapOfAuthentication.put("head", GroupID.Authentication.name());
        mapOfAuthentication.put("login", request.getLogin());
        mapOfAuthentication.put("password", request.getPassword());
        userClient.pushRequestInQueue(mapOfAuthentication);
    }
}
