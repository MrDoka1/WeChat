package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Dialogs")
@Data
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // user_id1 - тот, кто начал диалог
    @ManyToOne
    @JoinColumn(name = "user1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2")
    private User user2;

    public Dialog() {
    }

    public Dialog(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

}
