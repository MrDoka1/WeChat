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

    private String phonenumber;
    private String password;
    private boolean is_twofactor_auth;

    public UserAuth() {
    }

    public UserAuth(String phonenumber, String password, boolean is_twofactor_auth) {
        Password password1 = new Password();

        this.phonenumber = phonenumber;
        this.password = password1.encodePassword(password);
        this.is_twofactor_auth = is_twofactor_auth;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_twofactor_auth() {
        return is_twofactor_auth;
    }

    public void setIs_twofactor_auth(boolean is_twofactor_auth) {
        this.is_twofactor_auth = is_twofactor_auth;
    }
}