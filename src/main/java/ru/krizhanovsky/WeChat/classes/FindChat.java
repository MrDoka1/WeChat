package ru.krizhanovsky.WeChat.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.ChatUser;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.ChatRepository;
import ru.krizhanovsky.WeChat.repos.ChatUserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindChat {
    @Autowired
    ChatUserRepository chatUserRepository;
    @Autowired
    ChatRepository chatRepository;

    public List<ChatUser> getChatUsers(User user) {
        return chatUserRepository.findChatUsersByUser(user);
    }

    public Chat getChat(long id) {
        return chatRepository.findById(id).orElseThrow();
    }

    public List<Chat> getChats(User user) {
        List<ChatUser> chatUserList = chatUserRepository.findChatUsersByUser(user);
        List<Chat> chatList = new ArrayList<>();
        for (ChatUser chatUser : chatUserList) {
            chatList.add(chatUser.getChat());
        }
        return chatList;
    }

    public long getCountMembers(Chat chat) {
        return chatUserRepository.getCountMembers(chat);
    }
}
