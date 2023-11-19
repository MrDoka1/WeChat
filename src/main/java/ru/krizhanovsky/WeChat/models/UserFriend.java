package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;
    // тот, кто отправляет заявку

    @ManyToOne
    @JoinColumn
    private User friend;
    //тот, кому отправили заявку

    public UserFriend() {
    }

    public UserFriend(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }
}
