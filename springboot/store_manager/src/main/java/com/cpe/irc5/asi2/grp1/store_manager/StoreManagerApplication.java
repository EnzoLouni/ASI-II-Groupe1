package com.cpe.irc5.asi2.grp1.store_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ComponentScan("com.cpe.irc5.asi2.grp1.*")
@EnableFeignClients(basePackages = {"com.cpe.irc5.asi2.grp1.user_manager.client","com.cpe.irc5.asi2.grp1.store_manager.controller"})
public class StoreManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagerApplication.class, args);
    }

}
