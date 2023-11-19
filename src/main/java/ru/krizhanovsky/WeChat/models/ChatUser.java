package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_user")
@Data
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Chat chat;

    @ManyToOne
    @JoinColumn
    private User user;

    ChatRole role;

    @Column(name = "time_connect")
    LocalDateTime timeConnect;

    public ChatUser() {
    }

    public ChatUser(Chat chat, User user, ChatRole role, LocalDateTime timeConnect) {
        this.chat = chat;
        this.user = user;
        this.role = role;
        this.timeConnect = timeConnect;
    }
}
