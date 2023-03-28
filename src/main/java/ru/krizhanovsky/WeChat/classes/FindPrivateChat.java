package ru.krizhanovsky.WeChat.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.PrivateChats;
import ru.krizhanovsky.WeChat.repos.PrivateChatsRepository;

//@Service
public class FindPrivateChat {
//    @Autowired
//    private PrivateChatsRepository privateChatsRepository;

    public FindPrivateChat() {
    }

    public long getPrivateChatId(long userId1, long userId2, PrivateChatsRepository privateChatsRepository) {
        PrivateChats chat = privateChatsRepository.findByUserId1AndUserId2(userId1, userId2);
        if (chat == null) {
            chat = privateChatsRepository.findByUserId1AndUserId2(userId2, userId1);
            if (chat == null) {
                // Написать проверку и создание чата
                return 0;
            }
        }
        return chat.getId();
    }
}
