package com.cpe.irc5.asi2.grp1.commons.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

public interface ActiveMqConfigurationTemplate {
    @Bean
    DefaultJmsListenerContainerFactory activeMqFactory(DefaultJmsListenerContainerFactoryConfigurer configurer);
}
