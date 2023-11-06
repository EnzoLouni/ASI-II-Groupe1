package com.cpe.irc5.asi2.grp1.commons.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userClient", url = "${user.ribbon.listOfServers}/userapi")
public interface UserClient {

    @PostMapping("/private/auth")
    boolean authentication(@RequestParam("login") String login, @RequestParam("password") String password);
}
