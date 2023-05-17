package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    // тот, кто отправляет заявку
    private Long userId;
    @Column(name = "friend_id")
    //тот, кому отправили заявку
    private Long friendId;

    public UserFriend() {
    }

    public UserFriend(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}
