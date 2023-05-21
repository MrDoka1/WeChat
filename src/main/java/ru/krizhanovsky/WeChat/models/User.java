package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.krizhanovsky.WeChat.classes.LocalDateTimeToTime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

// ***** База данных с информацией о пользователях *****
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nick;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "last_online")
    private LocalDateTime lastOnline;

    private boolean privateProfile;

    public User() {
    }

    public User(String nick, String firstName, String lastName, String birthDate, boolean privateProfile) {
        this.nick = nick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.lastOnline = LocalDateTime.now();
        this.privateProfile = privateProfile;
    }

    public boolean isOnline() {
        return lastOnline.until(LocalDateTime.now(), ChronoUnit.SECONDS) < 6;
    }
    public String timeOnline() {
        if (lastOnline.until(LocalDateTime.now(), ChronoUnit.SECONDS) < 6) {
            return "в сети";
        }

        return "был(а) в " + LocalDateTimeToTime.timeString(lastOnline);
    }

    public String timeOnlineClass() {
        if (lastOnline.until(LocalDateTime.now(), ChronoUnit.SECONDS) < 6) {
            return " --online";
        }
        return "";
    }

}