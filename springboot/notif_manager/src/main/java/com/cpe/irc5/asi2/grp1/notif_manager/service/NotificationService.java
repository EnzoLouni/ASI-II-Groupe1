package com.cpe.irc5.asi2.grp1.notif_manager.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.cpe.irc5.asi2.grp1.notif_manager.publicnotif.model.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private SocketIOServer socketServer;

    public NotificationService(SocketIOServer socketServer){
        this.socketServer=socketServer;

        this.socketServer.addConnectListener(onUserConnectWithSocket);
        this.socketServer.addDisconnectListener(onUserDisconnectWithSocket);

        /**
         * Here we create only one event listener
         * but we can create any number of listener
         * messageSendToUser is socket end point after socket connection user have to send message payload on messageSendToUser event
         */
        //this.socketServer.addEventListener("messageSendToUser", NotificationResponse.class, onSendMessage);

    }

    public void sendNotificationResponse(UUID clientId, NotificationResponse notificationResponse) {
        log.info("Server has send message to user " + clientId + " and message is " + notificationResponse.getMessage());
        for(SocketIOClient client: socketServer.getAllClients()) {
            log.info(client.getRemoteAddress().toString());
        }
        socketServer.getClient(clientId).sendEvent("", notificationResponse);
    }


    public ConnectListener onUserConnectWithSocket = new ConnectListener() {
        @Override
        public void onConnect(SocketIOClient client) {
            log.info("Perform operation on user connect in controller");
        }
    };


    public DisconnectListener onUserDisconnectWithSocket = new DisconnectListener() {
        @Override
        public void onDisconnect(SocketIOClient client) {
            log.info("Perform operation on user disconnect in controller");
        }
    };

    /*public DataListener<NotificationResponse> onSendMessage = new DataListener<NotificationResponse>() {
        @Override
        public void onData(SocketIOClient client, NotificationResponse message, AckRequest acknowledge) throws Exception {

            *//**
             * Sending message to target user
             * target user should subscribe the socket event with his/her name.
             * Send the same payload to user*//*


            log.info("Server has send message to user "+message.getMessage()+" and message is "+message.getMessage());
            socketServer.getBroadcastOperations().sendEvent(message.getMessage(),client, message);


            *//**
             * After sending message to target user we can send acknowledge to sender*//*

            acknowledge.sendAckData("Message send to target user successfully");
        }
    };*/
}
