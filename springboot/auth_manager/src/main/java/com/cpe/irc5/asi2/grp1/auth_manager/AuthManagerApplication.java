package com.cpe.irc5.asi2.grp1.auth_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cpe.irc5.asi2.grp1.*")
@EnableFeignClients(basePackages = {"com.cpe.irc5.asi2.grp1.user_manager.client","com.cpe.irc5.asi2.grp1.auth_manager.controller"})
public class AuthManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthManagerApplication.class, args);
    }

}
