package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krizhanovsky.WeChat.models.MessageInChat;

import java.util.List;

public interface MessageInChatRepository extends JpaRepository<MessageInChat, Long> {
    List<MessageInChat> findByChatId(long chatId);
}
