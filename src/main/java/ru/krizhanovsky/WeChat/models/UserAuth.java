package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

// ***** База данных для аутентификации пользователей *****
@Entity
@Data
@Table(name = "users_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String email;
    private String password;

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> role;


    public UserAuth() {
    }

    public UserAuth(String email, String password, User user) {
        this.email = email;
        this.password = password;
        this.user = user;
    }
}