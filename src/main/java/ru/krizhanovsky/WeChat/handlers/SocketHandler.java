package ru.krizhanovsky.WeChat.handlers;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*@Component
public class SocketHandler {

    private final SocketIOServer server;

    @Autowired
    private SocketHandler(SocketIOServer server) {
        this.server = server;
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("Socket connect ..");
        if (client != null) {
            String username = client.getHandshakeData().getSingleUrlParam("username");
            // Single users may log in more terminals
            client.joinRoom(username);

            String sessionId = client.getSessionId().toString();
            System.out.println("username=" + username + ", sessionId=" + sessionId);
        } else {
            System.out.println("client is empty");
        }
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("Socket disconnect ..");
        System.out.println("sessionId=" + client.getSessionId().toString());
        client.disconnect();
    }

    *//**
     * Front end to the rear system
     *
     * @param client
     * @param request
     * @param message
     *//*
    @OnEvent(value = "topic")
    public void onEvent(SocketIOClient client, AckRequest request, String message) {
        System.out.println("Socket receive message ..");
        System.out.println("message=" + message);
        if (request.isAckRequested()) {
            request.sendAckData(message);
        }
    }

    *//**
     * Push the message forward
     *
     * @param username
     * @param message
     *//*
    public void broadcast(String username, String message) {
        System.out.println("Socket post message ..");
        System.out.println("username=" + username);
        server.getRoomOperations(username).sendEvent("broadcast", message);
    }
}*/
