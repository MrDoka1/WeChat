package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.PrivateChat;

import java.awt.*;

@Repository
public interface PrivateChatsRepository extends JpaRepository<PrivateChat, Long> {
    PrivateChat findByUserId1AndUserId2(long userId1, long userId2);

    default List findByUserId1OrUserId2(long userId1, long userId2) {
        return null;
    }
}
