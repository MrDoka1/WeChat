package ru.krizhanovsky.WeChat.socket;

import lombok.Data;

@Data
public class SocketMessage {
    private SocketMessageType type;
    private String message;
    private String room;

    public SocketMessage() {
    }

    public SocketMessage(SocketMessageType type, String message) {
        this.type = type;
        this.message = message;
    }

    public SocketMessage(SocketMessageType type, String message, String room) {
        this.type = type;
        this.message = message;
        this.room = room;
    }
}
