package ru.krizhanovsky.WeChat.classes;

import lombok.Data;
import ru.krizhanovsky.WeChat.models.MessageInChat;
import ru.krizhanovsky.WeChat.models.PrivateChat;
import ru.krizhanovsky.WeChat.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatOutput {
    private Long id;
    private String name;
    private String lastMessage;
    private String lastMessageTime;
    private String online;
    private String url;
    private String active;

    public ChatOutput() {
    }

    public ChatOutput(PrivateChat privateChat, User user, List<MessageInChat> messageInChatList, Long activeId) {
        User user1 = privateChat.getUserId1();
        User user2 = privateChat.getUserId2();
        if (!user1.getId().equals(user.getId())) {
            this.id = user1.getId();
            this.name = user1.getFirstName() + " " + user1.getLastName();
            this.online = "";
            this.active = "";
            if (user1.getId().equals(activeId)) {
                this.active = " --active";
            }
            if (user1.isOnline()) {
                this.online = "--online";
            }
        } else {
            this.id = user2.getId();
            this.name = user2.getFirstName() + " " + user2.getLastName();
            this.online = "";
            this.active = "";
            if (user2.getId().equals(activeId)) {
                this.active = " --active";
            }
            if (user2.isOnline()) {
                this.online = "--online";
            }
        }

        if (messageInChatList.isEmpty()) {
            this.lastMessage = "";
            this.lastMessageTime = "";
        } else {
            this.lastMessage = messageInChatList.get(messageInChatList.size() - 1).getText();
            this.lastMessageTime = LocalDateTimeToTime.timeString(messageInChatList.get(messageInChatList.size() - 1).getTime());
        }
        // Исправить на ник
        this.url = user.getId().toString();
    }


}
