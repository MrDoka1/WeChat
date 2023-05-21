package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PrivateChats")
@Data
public class PrivateChat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // user_id1 - тот, кто начал диалог
    @ManyToOne
    @JoinColumn(name = "user_id1")
    private User userId1;

    @ManyToOne
    @JoinColumn(name = "user_id2")
    private User userId2;

    public PrivateChat() {
    }

    public PrivateChat(User user_id1, User user_id2) {
        this.userId1 = user_id1;
        this.userId2 = user_id2;
        // написать создание таблицы с сообщениями
    }

}
