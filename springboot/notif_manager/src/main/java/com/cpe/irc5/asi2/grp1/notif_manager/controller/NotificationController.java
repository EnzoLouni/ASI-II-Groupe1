package com.cpe.irc5.asi2.grp1.notif_manager.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
import com.cpe.irc5.asi2.grp1.notif_manager.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
@Slf4j
public class NotificationController {
    @Autowired
    private SocketIOServer socketServer;

    private final NotificationService notificationService;

    public NotificationController(SocketIOServer socketServer, NotificationService notificationService){
        this.socketServer=socketServer;
        this.notificationService=notificationService;

        this.socketServer.addConnectListener(onConnected());
        this.socketServer.addDisconnectListener(onDisconnected());

        /**
         * Here we create only one event listener
         * but we can create any number of listener
         * messageSendToUser is socket end point after socket connection user have to send message payload on messageSendToUser event
         */
        this.socketServer.addEventListener("notify_user", NotificationResponse.class, notifyUser());

    }

    public DataListener<NotificationResponse> notifyUser() {
        NotificationResponse response = new NotificationResponse();
        response.setMessage("Non");
        response.setOperationsWereMade(false);
        response.setErrors(Arrays.asList("non"));
        return (senderClient, data, ackSender) -> {
            notificationService.sendNotificationResponse(senderClient, "get_notif",response);
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {
            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };

    }


    private DisconnectListener onDisconnected() {
        return (client) -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

    public DataListener<NotificationResponse> onSendMessage = new DataListener<NotificationResponse>() {
        @Override
        public void onData(SocketIOClient client, NotificationResponse message, AckRequest acknowledge) throws Exception {

            /**
             * Sending message to target user
             * target user should subscribe the socket event with his/her name.
             * Send the same payload to user*/


            log.info("Server has send message to user "+message.getMessage()+" and message is "+message.getMessage());
            socketServer.getBroadcastOperations().sendEvent(message.getMessage(),client, message);


            /**
             * After sending message to target user we can send acknowledge to sender*/

            acknowledge.sendAckData("Message send to target user successfully");
        }
    };
}
