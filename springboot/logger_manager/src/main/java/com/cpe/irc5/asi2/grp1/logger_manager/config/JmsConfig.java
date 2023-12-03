package com.cpe.irc5.asi2.grp1.logger_manager.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Session;


@Configuration
@EnableJms

public class JmsConfig {

    @Bean
    public JmsListenerContainerFactory<?> queueConnectionFactory(ConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false); // Utilisation d'une file d'attente
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE); // Utilisation d'un mode auto-acknowledge
        factory.setPubSubDomain(false);
        factory.setConcurrency("1"); // Nombre de threads de consommation
        factory.setRecoveryInterval(1000L); // Intervalle de récupération en cas d'échec


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
