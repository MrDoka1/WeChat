package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.ChatUser;
import ru.krizhanovsky.WeChat.models.User;

import java.util.List;

@Repository
public interface ChatUserRepository extends CrudRepository<ChatUser, Long> {
    @Query("select count(c) from ChatUser c where c.chat = :chat")
    long getCountMembers(Chat chat);
    List<ChatUser> findChatUsersByUser(User user);
    List<ChatUser> findChatUsersByChat(Chat chat);
    @Query("select count(c) from ChatUser c where c.user = :user and c.chat = :chat")
    long userConsistsOfChat(User user, Chat chat);
}
