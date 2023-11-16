package com.cpe.irc5.asi2.grp1.card_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cpe.irc5.asi2.grp1.*")
@EnableFeignClients(basePackages = {"com.cpe.irc5.asi2.grp1.*.client","com.cpe.irc5.asi2.grp1.card_manager.controller"})
public class CardManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardManagerApplication.class, args);
	}

}
