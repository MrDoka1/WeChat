package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.krizhanovsky.WeChat.classes.LocalDateTimeToTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// ***** База данных с информацией о пользователях *****
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url_photo")
    private String urlPhoto;

    private String nick;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "last_online")
    private LocalDateTime lastOnline;

    private boolean privateProfile;

    public User() {
    }

    public User(String nick, String firstname, String lastname, String birthDate, boolean privateProfile) {
        this.nick = nick;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.lastOnline = LocalDateTime.now();
        this.privateProfile = privateProfile;
    }
    public User(String firstname, String lastname, String birthDate, boolean privateProfile) {
        this.nick = String.valueOf(this.getId());
        this.firstname = firstname;
        this.lastname = lastname;
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

    public long getAge() {
        return ChronoUnit.YEARS.between(LocalDate.parse(birthDate), LocalDate.now());
    }

}