package ru.krizhanovsky.WeChat.dto;

import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.time.LocalDateTime;

public enum MessageDTO {;
    private interface Id { @Positive Long getId(); }
    private interface ChatId { @Positive Long getChatId(); }
    private interface DialogId { @Positive Long getDialogId(); }
    private interface SenderId { @Positive Long getSenderId(); }

    private interface Text { String getText(); }
    private interface Time { LocalDateTime getTime(); }

    public enum Response {;
        @Value public static class Public implements Id, ChatId, DialogId, SenderId, Text, Time {
            Long id;
            Long chatId;
            Long dialogId;
            Long senderId;

            String text;
            LocalDateTime time;
        }
    }
    
}
