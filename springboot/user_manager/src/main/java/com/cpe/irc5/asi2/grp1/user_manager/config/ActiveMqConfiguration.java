package com.cpe.irc5.asi2.grp1.user_manager.config;

import com.cpe.irc5.asi2.grp1.commons.config.ActiveMqConfigurationTemplate;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessagingMessageConverter;

import javax.jms.Session;

@Configuration
public class ActiveMqConfiguration implements ActiveMqConfigurationTemplate {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public DefaultJmsListenerContainerFactory activeMqFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, new ActiveMQConnectionFactory(brokerUrl));
        factory.setMessageConverter(new MessagingMessageConverter());
        factory.setErrorHandler(new UserExceptionHandler());
        return factory;
    }
}


