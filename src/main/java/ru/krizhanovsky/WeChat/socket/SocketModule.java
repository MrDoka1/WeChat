package ru.krizhanovsky.WeChat.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import ru.krizhanovsky.WeChat.classes.ChatMessageKafka;
import ru.krizhanovsky.WeChat.servises.SocketIOService;

@Component
public class SocketModule {


    private final SocketIOServer server;
    private final SocketIOService socketService;
    private final Gson gson;

    public SocketModule(SocketIOServer server, SocketIOService socketService, Gson gson) {
        this.server = server;
        this.socketService = socketService;
        this.gson = gson;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", String.class, onChatReceived());
        server.addEventListener("send_message", String.class, onChatReceived());
    }


    private DataListener<String> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            SocketMessage data1 = gson.fromJson(data, SocketMessage.class);
            socketService.sendMessage(data1.getRoom(), "get_message", senderClient, data1.getMessage());
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
            System.out.println("Socket ID[{}]  Connected to socket " + client.getSessionId().toString());
            System.out.println(client.getAllRooms().size());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            System.out.println("Client[{}] - Disconnected from socket " + client.getSessionId().toString());
            System.out.println(client.getAllRooms().size());
        };
    }

}
