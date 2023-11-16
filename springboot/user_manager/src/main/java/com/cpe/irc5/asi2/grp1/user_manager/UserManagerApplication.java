package com.cpe.irc5.asi2.grp1.user_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJms
@ComponentScan("com.cpe.irc5.asi2.grp1.*")
@EnableFeignClients(basePackages = {"com.cpe.irc5.asi2.grp1.card_manager.client","com.cpe.irc5.asi2.grp1.user_manager.controller"})
public class UserManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }

}
