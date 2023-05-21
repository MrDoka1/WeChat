package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.krizhanovsky.WeChat.classes.LocalDateTimeToTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class MessageInChat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "sender_id")
    private Long senderId;

    private String text;
    private LocalDateTime time;

    public MessageInChat() {
    }

    public MessageInChat(Long chatId, Long senderId, String text, LocalDateTime time) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.text = text;
        this.time = time;
    }

    public String forWho(long id) {
        if (senderId == id) {
            return "_for-them";
        }
        return "_for-me";
    }

    public String timeString() {
        return LocalDateTimeToTime.timeString(time);
    }
}
