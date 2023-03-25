package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import ru.krizhanovsky.WeChat.classes.Password;

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
    private String bithdate;


    public UserAuth() {
    }

    public UserAuth(String email, String password, String firstName, String lastName, String bithdate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bithdate = bithdate;
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

    public String getBithdate() {
        return bithdate;
    }

    public void setBithdate(String bithdate) {
        this.bithdate = bithdate;
    }
}