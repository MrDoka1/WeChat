package ru.krizhanovsky.WeChat.objects;

import lombok.Data;

@Data
public class Message {

    private Long chatId;
    private String content;

    public Message(Long chatId, String content) {
        this.chatId =chatId;
        this.content = content;
    }
}
