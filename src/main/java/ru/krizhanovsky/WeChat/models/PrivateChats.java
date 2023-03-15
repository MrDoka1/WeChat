package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PrivateChats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // user_id1 - тот, кто начал диалог
    private int user_id1;
    private int user_id2;

    public PrivateChats() {
    }

    public PrivateChats(int user_id1, int user_id2) {
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;
        // написать создание таблицы с сообщениями
    }
}
