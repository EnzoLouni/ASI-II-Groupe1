package com.cpe.irc5.asi2.grp1.store_manager.config;

import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.store_manager.dto.StoreOrder;
import com.cpe.irc5.asi2.grp1.store_manager.enums.StoreAction;
import com.cpe.irc5.asi2.grp1.store_manager.service.StoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

import java.net.ConnectException;

import static com.cpe.irc5.asi2.grp1.commons.config.ActiveMQMessageConverter.toBusMessage;

@RequiredArgsConstructor
@Component
@Slf4j
public class ActiveMqBus {
    private final StoreService storeService;

    @Value("${store.busName}")
    private String busName;

    @JmsListener(destination = "${store.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) throws ConnectException {
        log.info("[{}] dequeued message with Group ID: {}", busName, content.getGroupID());
        ObjectMapper mapper = new ObjectMapper();
        try {
            BusMessage busMessage = toBusMessage(content);
            if(busMessage.getGroupID().equals(GroupID.Stores)) {
                if(busMessage.getRequestType().equals(RequestType.SELL)) {
                    if(busMessage.getRequestType().equals(RequestOrigin.IN)) {
                        StoreOrder sellOrder = mapper.convertValue(busMessage.getDataBusObject(), StoreOrder.class);
                        storeService.sell(sellOrder);
                    }
                    else if(busMessage.getRequestType().equals(RequestOrigin.OUT)){
                        CardDto cardSold = mapper.convertValue(busMessage.getDataBusObject(), CardDto.class);
                        storeService.callBackSell(cardSold);
                    }
                }
                else if(busMessage.getRequestType().equals(RequestType.BUY)) {
                    if(busMessage.getRequestType().equals(RequestOrigin.IN)) {
                        StoreOrder buyOrder = mapper.convertValue(busMessage.getDataBusObject(), StoreOrder.class);
                        storeService.buy(buyOrder);
                    }
                    else if(busMessage.getRequestType().equals(RequestOrigin.OUT)){
                        CardDto cardBought = mapper.convertValue(busMessage.getDataBusObject(), CardDto.class);
                        storeService.callBackBuy(cardBought);
                    }
                }
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
