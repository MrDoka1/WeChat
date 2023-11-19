package ru.krizhanovsky.WeChat.classes;

import lombok.Data;
import ru.krizhanovsky.WeChat.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserOutput {
    private long id;
    private String urlPhoto;
    private String nick;
    private String firstname;
    private String lastname;
    private String birthDate;
    private LocalDateTime lastOnline;
    private boolean privateProfile;
    private Friend friend;
    private List<Long> friendsIds;
    private List<Long> subscribersIds;

    public UserOutput(User user, Friend friend, List<Long> friendsIds, List<Long> subscribersIds) {
        this.id = user.getId();
        this.urlPhoto = user.getUrlPhoto();
        this.nick = user.getNick();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.birthDate = user.getBirthDate();
        this.lastOnline = user.getLastOnline();
        this.privateProfile = user.isPrivateProfile();

        this.friend = friend;
        this.friendsIds = friendsIds;
        this.subscribersIds = subscribersIds;
    }
}
