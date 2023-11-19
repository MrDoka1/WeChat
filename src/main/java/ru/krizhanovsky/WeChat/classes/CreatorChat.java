package ru.krizhanovsky.WeChat.classes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.ChatRole;
import ru.krizhanovsky.WeChat.models.ChatUser;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.ChatRepository;
import ru.krizhanovsky.WeChat.repos.ChatUserRepository;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreatorChat {
    private final ChatUserRepository chatUserRepository;
    private final ChatRepository chatRepository;
    private final UserService userService;

    public ChatOutput create(Chat chat, List<Long> idList) {
        chatRepository.save(chat);
        LocalDateTime time = LocalDateTime.now();

        ChatUser chatUser = new ChatUser(chat, chat.getCreator(), ChatRole.CREATOR, time);

        chatUserRepository.save(chatUser);
        if (!idList.isEmpty()) {
            List<ChatUser> list = new ArrayList<>();
            for (long id : idList) {
                User user = userService.getUser(id);
                if (user != null && !user.equals(chat.getCreator())) {
                    list.add(new ChatUser(chat, user, ChatRole.PARTICIPANT, time));
                }
            }
            chatUserRepository.saveAll(list);
            return new ChatOutput(chatUser, list.size() + 1);
        }

        return new ChatOutput(chatUser, 1);
    }
}

