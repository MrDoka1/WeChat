package ru.krizhanovsky.WeChat.classes;

import lombok.Data;
import ru.krizhanovsky.WeChat.models.Message;

import java.time.LocalDateTime;
@Data
public class MessageOutput {
    private Long id;
    private Long chatId;
    private Long dialogId;
    private Long senderId;

    private String text;
    private LocalDateTime time;

    public MessageOutput(Message message) {
        this.id = message.getId();
        if (message.getDialog() == null) {
            this.chatId = message.getChat().getId();
        } else {
            this.dialogId = message.getDialog().getId();
        }
        this.senderId = message.getSender().getId();
        this.text = message.getText();
        this.time = message.getTime();
    }
}
