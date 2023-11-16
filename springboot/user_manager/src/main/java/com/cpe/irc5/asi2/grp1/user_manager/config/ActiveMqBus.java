package com.cpe.irc5.asi2.grp1.user_manager.config;

import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.cpe.irc5.asi2.grp1.user_manager.service.UserService;
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

    private final UserService userService;

    @Value("${user.busName}")
    private String busName;

    @JmsListener(destination = "${user.busName}", containerFactory = "activeMqFactory")
    public void processMessage(ActiveMQTextMessage content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            BusMessage busMessage = toBusMessage(content);
            log.info("[{}] dequeued message with Group ID: {}", busName, busMessage.getGroupID());
            if(busMessage.getGroupID().equals(GroupID.Users)) {
                if(busMessage.getRequestType().equals(RequestType.PUT)) {
                    if(busMessage.getOrigin().equals(RequestOrigin.IN)) {
                        UserDto userToUpdate = mapper.convertValue(busMessage.getDataBusObject(), UserDto.class);
                        userService.updateUser(userToUpdate.getId(), userToUpdate);
                    }
                    else if(busMessage.getOrigin().equals(RequestOrigin.OUT)) {

                    }
                }
                else if (busMessage.getRequestType().equals(RequestType.POST)) {
                    UserDto userToCreate = mapper.convertValue(busMessage.getDataBusObject(), UserDto.class);
                    userService.createUser(userToCreate);
                }
                else if (busMessage.getRequestType().equals(RequestType.CALLBACK)) {
                    CardDto cardInstance = mapper.convertValue(busMessage.getDataBusObject(), CardDto.class);
                    userService.callBackCreationUser(cardInstance);
                }
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (ConnectException e) {
            throw new RuntimeException(e);
        }
    }
}
