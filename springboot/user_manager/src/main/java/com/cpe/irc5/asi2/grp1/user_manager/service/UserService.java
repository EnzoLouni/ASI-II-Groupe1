package com.cpe.irc5.asi2.grp1.user_manager.service;


import com.cpe.irc5.asi2.grp1.commons.enums.GroupID;
import com.cpe.irc5.asi2.grp1.commons.enums.RequestType;
import com.cpe.irc5.asi2.grp1.user_manager.dtos.UserDTO;
import com.cpe.irc5.asi2.grp1.user_manager.mapper.UserMapper;
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
import java.util.List;
import java.util.stream.StreamSupport;

import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.GROUP;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.TYPE;
import static com.cpe.irc5.asi2.grp1.commons.enums.Constants.USER_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserBusService userBusService;
    private final NotificationBusService notificationBusService;

    private static final ObjectMapper mapper = new ObjectMapper();

    public boolean canCredentialsMatch(String login, String password) throws DataAccessResourceFailureException {
        log.info("Matching Credentials");
        return userRepository.findUserByLoginAndPassword(login, DigestUtils.sha256Hex(password)) != null;
    }

    public UserDTO getUser(Integer userId) throws CannotCreateTransactionException {
        log.info("Getting User with ID {}", userId);
        return userRepository.findById(userId).map(userMapper::toUserDto).orElse(null);
    }

    public List<UserDTO> getAllUsers() throws CannotCreateTransactionException {
        log.info("Getting all Users");
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).map(userMapper::toUserDto).collect(toList());
    }

    public void createUserRequest(UserDTO userUpdated) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Create Request received");
        ObjectNode objectNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(userUpdated));
        objectNode.put(GROUP, GroupID.Users.name());
        objectNode.put(TYPE, RequestType.POST.name());
        userBusService.pushInQueue(objectNode);
    }

    public void updateUserRequest(Integer id, UserDTO userUpdated) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Update Request received");
        userUpdated.setId(id);
        ObjectNode objectNode = (ObjectNode) mapper.readTree(mapper.writeValueAsString(userUpdated));
        objectNode.put(GROUP, GroupID.Users.name());
        objectNode.put(TYPE, RequestType.PUT.name());
        userBusService.pushInQueue(objectNode);
    }

    public void updateUser(Integer id, UserDTO userUpdated) throws CannotCreateTransactionException {
        log.info("Update User with ID: {}", userUpdated.getId());
        try {
            userRepository.findById(id);
            userUpdated.setId(id);
            userUpdated.setPassword(DigestUtils.sha256Hex(userUpdated.getPassword()));
            userRepository.save(userMapper.toUser(userUpdated));
        } catch(EmptyResultDataAccessException e) {
            log.error(USER_NOT_FOUND, id);
        }
    }

    public void deleteUserRequest(Integer id) throws MessageNotWriteableException, JsonProcessingException, ConnectException {
        log.info("Delete Request received");
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put(GROUP, GroupID.Users.name());
        objectNode.put(TYPE, RequestType.DELETE.name());
        objectNode.put("id", id);
        userBusService.pushInQueue(objectNode);
    }

    public void deleteUser(Integer id) throws CannotCreateTransactionException {
        log.info("Delete User with ID: {}", id);
        try {
            userRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            log.error(USER_NOT_FOUND, id);
        }
    }
}
