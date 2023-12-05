package com.cpe.irc5.asi2.grp1.logger_manager.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessagingMessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Session;


@Configuration
public class ActiveMqConfiguration {

    /*@Bean
    public JmsListenerContainerFactory<?> queueConnectionFactory(ConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false); // Utilisation d'une file d'attente
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE); // Utilisation d'un mode auto-acknowledge
        //factory.setConcurrency("1"); // Nombre de threads de consommation
        //factory.setRecoveryInterval(1000L); // Intervalle de récupération en cas d'échec
        return factory;
    }*/

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public DefaultJmsListenerContainerFactory activeMqFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, new ActiveMQConnectionFactory(brokerUrl));
        factory.setMessageConverter(new MessagingMessageConverter());
        factory.setErrorHandler(new LoggerExceptionHandler());
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicConnectionFactory(ConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);

        factory.setPubSubDomain(true);
        return factory;
    }
}
