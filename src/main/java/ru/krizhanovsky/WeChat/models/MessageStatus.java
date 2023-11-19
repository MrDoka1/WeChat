package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "messages_status")
@Data
public class MessageStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Message message;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "is_read")
    boolean isRead;

    public MessageStatus() {
    }

    public MessageStatus(Message message, User user, boolean isRead) {
        this.message = message;
        this.user = user;
        this.isRead = isRead;
    }
}
