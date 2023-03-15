package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;

@Entity
public class Chats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_id", nullable = false)
    private Long chat_id;

    private String chat_name;
    private String chat_creator_user_id;
    private String chat_creation_time;

    public Chats() {
    }

    public Chats(String chat_name, String chat_creator_user_id, String chat_creation_time) {
        this.chat_name = chat_name;
        this.chat_creator_user_id = chat_creator_user_id;
        this.chat_creation_time = chat_creation_time;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }
    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getChat_creator_user_id() {
        return chat_creator_user_id;
    }

    public void setChat_creator_user_id(String chat_creator_user_id) {
        this.chat_creator_user_id = chat_creator_user_id;
    }

    public String getChat_creation_time() {
        return chat_creation_time;
    }

    public void setChat_creation_time(String chat_creation_time) {
        this.chat_creation_time = chat_creation_time;
    }
}
