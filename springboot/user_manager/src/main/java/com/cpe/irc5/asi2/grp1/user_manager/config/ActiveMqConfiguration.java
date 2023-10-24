package com.cpe.irc5.asi2.grp1.user_manager.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.network.jms.SimpleJmsMessageConvertor;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessagingMessageConverter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class ActiveMqConfiguration {
    @Bean
    public DefaultJmsListenerContainerFactory activeMqFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, new ActiveMQConnectionFactory());
        factory.setMessageConverter(new MessagingMessageConverter());
        return factory;
    }
}
