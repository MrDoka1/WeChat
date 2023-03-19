package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;

import java.util.Set;

// ***** База данных с информацией о пользователях *****
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String nick;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private String birthDate;
    private boolean privateProfile;



    public User() {
    }

    public User(Long user_id, String nick, String first_name, String last_name, String birth_date, boolean privateProfile) {
        this.userId = user_id;
        this.nick = nick;
        this.firstName = first_name;
        this.lastName = last_name;
        this.birthDate = birth_date;
        this.privateProfile = privateProfile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isPrivateProfile() {
        return privateProfile;
    }

    public void setPrivateProfile(boolean privateProfile) {
        this.privateProfile = privateProfile;
    }
}
