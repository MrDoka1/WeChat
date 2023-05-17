package ru.krizhanovsky.WeChat.objects;

import lombok.Data;

@Data
public class ClientMessageInChat {
    private long id;
    private boolean isCommand;
    private String textMessage;
    private String nameCommand;
    private String arguments;
}
