package com.cpe.irc5.asi2.grp1.store_manager.config;

import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.dto.StoreOrder;
import com.cpe.irc5.asi2.grp1.store_manager.publicstore.enums.StoreAction;
import com.cpe.irc5.asi2.grp1.store_manager.service.StoreService;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toObjectNode;

@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {
    private final StoreService storeService;

    @Value("${store.busName}")
    private String busName;

    @JmsListener(destination = "${store.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) {
        log.info("[{}] dequeued message with Group ID: {}", busName, content.getGroupID());
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectNode objectNode = toObjectNode(content);
            if(content.getGroupID().equals(GroupID.Stores.name())) {
                if(content.getType().equals(StoreAction.SELL.name())) {
                    StoreOrder sellOrder = mapper.convertValue(objectNode, StoreOrder.class);
                    storeService.sell(sellOrder);
                }
                else if(content.getType().equals(StoreAction.BUY.name())) {
                    StoreOrder buyOrder = mapper.convertValue(objectNode, StoreOrder.class);
                    storeService.buy(buyOrder);
                }
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
