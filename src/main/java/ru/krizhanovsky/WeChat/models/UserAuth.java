package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// ***** База данных для аутентификации пользователей *****
@Entity
@Table(name = "users_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String birthdate;
    @Column(name = "last_online")
    private LocalDateTime lastOnline;


    public UserAuth() {
    }

    public UserAuth(String email, String password, String firstName, String lastName, String bithdate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = bithdate;
        this.lastOnline = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDateTime getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
    }

    public boolean isOnline() {
        return this.lastOnline.until(LocalDateTime.now(), ChronoUnit.SECONDS) < 6;
    }
}