package com.cpe.irc5.asi2.grp1.chatHisto_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableJms
@EnableAsync
@SpringBootApplication
public class ChatHistoManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatHistoManagerApplication.class, args);
	}

}
