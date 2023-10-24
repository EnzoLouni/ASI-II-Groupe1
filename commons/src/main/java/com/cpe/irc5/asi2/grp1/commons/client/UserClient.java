package com.cpe.irc5.asi2.grp1.commons.client;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "userClient", url = "${user.ribbon.listOfServers}/userapi")
public interface UserClient {

    @PostMapping("/private/stack")
    @Async
    void pushRequestInQueue(@RequestBody Map<String, Object> content);
}
