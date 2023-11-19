package ru.krizhanovsky.WeChat.classes;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ChatMessageKafka {
    private Long senderId;
    private String chat;
    private String text;
    private String timeStamp;

    public ChatMessageKafka(Long senderId, String chat, String text, LocalDateTime timeStamp) {
        this.senderId = senderId;
        this.chat = chat;
        this.text = text;
        this.timeStamp = timeStamp.toString();
    }

    public LocalDateTime getTimeStamp() {
        return LocalDateTime.parse(this.timeStamp);
    }
}
