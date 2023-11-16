package com.cpe.irc5.asi2.grp1.card_manager.config;

import com.cpe.irc5.asi2.grp1.commons.config.ActiveMqConfigurationTemplate;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessagingMessageConverter;

@Configuration
public class ActiveMqConfiguration implements ActiveMqConfigurationTemplate {
    @Bean
    public DefaultJmsListenerContainerFactory activeMqFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, new ActiveMQConnectionFactory());
        factory.setMessageConverter(new MessagingMessageConverter());
        factory.setErrorHandler(new CardExceptionHandler());
        return factory;
    }
}
