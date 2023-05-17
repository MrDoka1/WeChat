package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krizhanovsky.WeChat.models.MessageInChat;

public interface MessageInChatRepository extends JpaRepository<MessageInChat, Long> {

}
