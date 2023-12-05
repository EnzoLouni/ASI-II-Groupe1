package com.cpe.irc5.asi2.grp1.logger_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@ComponentScan("com.cpe.irc5.asi2.grp1.*")
public class LoggerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerManagerApplication.class, args);
	}

}
