package ru.krizhanovsky.WeChat.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.ChatUser;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.ChatUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;


    public List<ChatUser> getChatUsers(User user) {
        return chatUserRepository.findChatUsersByUser(user);
    }
    public long getCountMembers(Chat chat) {
        return chatUserRepository.getCountMembers(chat);
    }
    public List<ChatUser> getChatUsers(Chat chat) {
        return chatUserRepository.findChatUsersByChat(chat);
    }
    public boolean userConsistsOfChat(User user, Chat chat) {
        return chatUserRepository.userConsistsOfChat(user, chat) == 1;
    }
}
