package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PrivateChats")
public class PrivateChat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // user_id1 - тот, кто начал диалог
    @Column(name = "user_id1")
    private long userId1;
    @Column(name = "user_id2")
    private long userId2;

    public PrivateChat() {
    }

    public PrivateChat(int user_id1, int user_id2) {
        this.userId1 = user_id1;
        this.userId2 = user_id2;
        // написать создание таблицы с сообщениями
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUser_id1() {
        return userId1;
    }

    public void setUser_id1(long user_id1) {
        this.userId1 = user_id1;
    }

    public long getUser_id2() {
        return userId2;
    }

    public void setUser_id2(long user_id2) {
        this.userId2 = user_id2;
    }
}
