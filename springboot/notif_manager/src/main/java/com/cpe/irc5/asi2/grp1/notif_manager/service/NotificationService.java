package com.cpe.irc5.asi2.grp1.notif_manager.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.cpe.irc5.asi2.grp1.notif_manager.model.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class NotificationService {
    public void sendNotificationResponse(SocketIOClient client, String eventName, NotificationResponse notificationResponse) {
        //log.info("Server has send message to user " + clientId + " and message is " + notificationResponse.getMessage());
        /*for(SocketIOClient client: socketServer.getAllClients()) {
            log.info(client.getRemoteAddress().toString());
        }*/
        client.sendEvent(eventName, notificationResponse);
    }
}
