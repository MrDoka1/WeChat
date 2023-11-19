package ru.krizhanovsky.WeChat.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.ChatUser;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.ChatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatUserService chatUserService;

    public Chat getChat(long id) {
        return chatRepository.findById(id).orElseThrow();
    }
    public List<Chat> getChats(User user) {
        List<ChatUser> chatUserList = chatUserService.getChatUsers(user);
        List<Chat> chatList = new ArrayList<>();
        for (ChatUser chatUser : chatUserList) {
            chatList.add(chatUser.getChat());
        }
        return chatList;
    }
}
