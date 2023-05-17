package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PrivateChatMessages")
public class PrivateChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "datetime_send")
    private LocalDateTime dateTimeSend;

    @Column(name = "is_read")
    private boolean isRead;

    private String text;
//    private Set<Long> whoWriteIdSet;
}
