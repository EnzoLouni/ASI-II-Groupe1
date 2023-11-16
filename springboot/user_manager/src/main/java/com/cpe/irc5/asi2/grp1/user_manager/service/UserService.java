package com.cpe.irc5.asi2.grp1.user_manager.service;


import com.cpe.irc5.asi2.grp1.card_manager.client.CardClient;
import com.cpe.irc5.asi2.grp1.card_manager.dtos.CardDto;
import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestOrigin;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.commons.exception.NotFoundResourceException;
import com.cpe.irc5.asi2.grp1.commons.model.BusMessage;
import com.cpe.irc5.asi2.grp1.notif_manager.bus.NotificationBusService;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
import com.cpe.irc5.asi2.grp1.user_manager.bus.UserBusService;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDto;
import com.cpe.irc5.asi2.grp1.user_manager.mapper.UserMapper;
import com.cpe.irc5.asi2.grp1.user_manager.model.User;
import com.cpe.irc5.asi2.grp1.user_manager.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.jms.MessageNotWriteableException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.CARD_NOT_FOUND;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.GROUP;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.ORIGIN;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.SUCCESS;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.TYPE;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.USER_NOT_CREATED;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.USER_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final CardClient cardClient;

    private final UserBusService userBusService;
    private final NotificationBusService notificationBusService;

    public boolean canCredentialsMatch(String login, String password) throws DataAccessResourceFailureException {
        log.info("Matching Credentials");
        return userRepository.findUserByLoginAndPassword(login, DigestUtils.sha256Hex(password)) != null;
    }

    public UserDto getUser(Integer userId) throws CannotCreateTransactionException {
        log.info("Getting User with ID {}", userId);
        return userRepository.findById(userId).map(userMapper::toUserDto).orElse(null);
    }

    public List<UserDto> getAllUsers() throws CannotCreateTransactionException {
        log.info("Getting all Users");
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).map(userMapper::toUserDto).collect(toList());
    }

    public void createUserRequest(UserDto userToCreate) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Create User Request received");
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Users)
                .requestType(RequestType.POST)
                .origin(RequestOrigin.IN)
                .dataBusObject(userToCreate)
                .classOfDataBusObject(userToCreate.getClass())
                .build();
        userBusService.pushInQueue(busMessage);
    }

    public void createUser(UserDto userToCreate) throws CannotCreateTransactionException, MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Creating User");
        NotificationResponse response = new NotificationResponse();
        User userCreated = null;
        try {
            userToCreate.setPassword(DigestUtils.sha256Hex(userToCreate.getPassword()));
            userCreated = userRepository.save(userMapper.toUser(userToCreate));
            cardClient.createCardsForUser(userMapper.toUserDto(userCreated));
        } catch(Exception e) {
            log.error(USER_NOT_CREATED);
            if(userCreated != null)
                userRepository.deleteById(userCreated.getId());
            response.setMessage(USER_NOT_CREATED);
            response.setErrors(Arrays.asList(USER_NOT_CREATED));
            response.setOperationsWereMade(false);
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Notifications)
                    .socketId(UUID.randomUUID().toString())
                    .dataBusObject(response)
                    .classOfDataBusObject(response.getClass())
                    .build();
            notificationBusService.pushInQueue(busMessage);
        }
    }

    public void callBackCreationUser(CardDto cardInstance) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("CallBack For User Creation Called");
        NotificationResponse response = new NotificationResponse();
        UserDto userDto = cardInstance.getUserDto();
        if(userDto.getLogin() != null) {
            response.setMessage(SUCCESS);
            response.setOperationsWereMade(true);
        }
        else {
            log.error(USER_NOT_CREATED);
            userRepository.deleteById(userDto.getId());
            response.setMessage(USER_NOT_CREATED);
            response.setErrors(Arrays.asList(USER_NOT_CREATED));
            response.setOperationsWereMade(false);
        }
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Notifications)
                .socketId(UUID.randomUUID().toString())
                .dataBusObject(response)
                .build();
        notificationBusService.pushInQueue(busMessage);
    }

    public void updateUserRequest(Integer id, UserDto userToUpdate) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Update User Request received");
        try {
            getUser(id).getId();
            userToUpdate.setId(id);
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Users)
                    .requestType(RequestType.POST)
                    .origin(RequestOrigin.IN)
                    .dataBusObject(userToUpdate)
                    .build();
            userBusService.pushInQueue(busMessage);
        } catch(NullPointerException e) {
            throw new NotFoundResourceException(RequestType.PUT, id);
        }
    }

    public void updateUserRequestFromUser(Integer id, UserDto userToUpdate) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Update User Request received");
        userToUpdate.setId(id);
        BusMessage busMessage = BusMessage.builder()
                .groupID(GroupID.Users)
                .requestType(RequestType.POST)
                .origin(RequestOrigin.OUT)
                .dataBusObject(userToUpdate)
                .build();
        userBusService.pushInQueue(busMessage);
    }

    public void updateUser(Integer id, UserDto userUpdated) throws CannotCreateTransactionException, MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Updating User with ID: {}", userUpdated.getId());
        NotificationResponse response = new NotificationResponse();
        try {
            userRepository.findById(id);
            userUpdated.setId(id);
            userUpdated.setPassword(DigestUtils.sha256Hex(userUpdated.getPassword()));
            userRepository.save(userMapper.toUser(userUpdated));
            response.setMessage(SUCCESS);
            response.setOperationsWereMade(true);
        } catch(EmptyResultDataAccessException e) {
            log.error(USER_NOT_FOUND, userUpdated.getLogin());
            response.setMessage(USER_NOT_FOUND);
            response.setErrors(Arrays.asList(String.format(USER_NOT_FOUND, userUpdated.getLogin())));
            response.setOperationsWereMade(false);
        } finally {
            BusMessage busMessage = BusMessage.builder()
                    .groupID(GroupID.Notifications)
                    .socketId(UUID.randomUUID().toString())
                    .dataBusObject(response)
                    .build();
            notificationBusService.pushInQueue(busMessage);
        }
    }
}
