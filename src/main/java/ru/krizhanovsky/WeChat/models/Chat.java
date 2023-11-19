package ru.krizhanovsky.WeChat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "url_photo")
    private String urlPhoto;

    @ManyToOne
    @JoinColumn
    private User creator;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "is_private")
    private boolean isPrivate;

    public Chat() {}

    public Chat(String name, String urlPhoto, User creator, LocalDateTime creationTime, boolean isPrivate) {
        this.name = name;
        this.urlPhoto = urlPhoto;
        this.creator = creator;
        this.creationTime = creationTime;
        this.isPrivate = isPrivate;
    }
}