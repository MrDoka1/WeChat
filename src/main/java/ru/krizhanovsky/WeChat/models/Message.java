package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.krizhanovsky.WeChat.classes.LocalDateTimeToTime;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Chat chat;

    @ManyToOne
    @JoinColumn
    private Dialog dialog;

    @ManyToOne
    @JoinColumn
    private User sender;

    private String text;
    private LocalDateTime time;

    public Message() {
    }

    public Message(Chat chat, User sender, String text, LocalDateTime time) {
        this.chat = chat;
        this.sender = sender;
        this.text = text;
        this.time = time;
    }

    public Message(Dialog dialog, User sender, String text, LocalDateTime time) {
        this.dialog = dialog;
        this.sender = sender;
        this.text = text;
        this.time = time;
    }

    public String getAndReplaceText() {
        return this.text.replace("\n", "<br>");
    }

    /*public String forWho(long id) {
        if (sender == id) {
            return "_for-them";
        }
        return "_for-me";
    }*/

    public String timeString() {
        return LocalDateTimeToTime.timeString(time);
    }
}
