package ru.krizhanovsky.WeChat.dto;

import lombok.Data;
import ru.krizhanovsky.WeChat.models.ChatRole;

@Data
public class ChatUsersDTOResponse {
    private long id;
    private ChatRole role;

    public ChatUsersDTOResponse(long id, ChatRole role) {
        this.id = id;
        this.role = role;
    }
}
