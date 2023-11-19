package ru.krizhanovsky.WeChat.classes;

import lombok.Data;
import ru.krizhanovsky.WeChat.models.ChatRole;
import ru.krizhanovsky.WeChat.models.ChatUser;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.User;

import java.time.LocalDateTime;

@Data
public class ChatOutput {
    private Long id;
    private boolean isDialog;
    private CustomDialog dialog;
    private CustomChat chat;

    public ChatOutput(Dialog dialog, User user) {
        if (user == dialog.getUser1()) {
            this.id = dialog.getUser2().getId();
        } else {
            this.id = dialog.getUser1().getId();
        }
        this.isDialog = true;
        this.dialog = new CustomDialog(dialog, user);
    }

    public ChatOutput(ChatUser chatUser, long members) {
        this.id = chatUser.getChat().getId();
        this.isDialog = false;
        this.chat = new CustomChat(chatUser, members);
    }

    @Data
    private static class CustomDialog {
        private long userId;
        public CustomDialog(Dialog dialog, User user) {
            this.userId = dialog.getUser1().equals(user) ? dialog.getUser2().getId() : dialog.getUser1().getId();
        }
    }

    @Data
    private static class CustomChat {
        private String name;
        private String urlPhoto;
        private ChatRole role;
        private LocalDateTime creationTime;
        private boolean isPrivate;
        private long members;

        public CustomChat(ChatUser chatUser, long members) {
            this.name = chatUser.getChat().getName();
            this.urlPhoto = chatUser.getChat().getUrlPhoto();
            this.creationTime = chatUser.getChat().getCreationTime();
            this.isPrivate = chatUser.getChat().isPrivate();
            this.role = chatUser.getRole();
            this.members = members;
        }
    }
}
