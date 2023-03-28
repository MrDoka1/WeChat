package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krizhanovsky.WeChat.models.PrivateChats;

public interface PrivateChatsRepository extends JpaRepository<PrivateChats, Long> {
    PrivateChats findByUserId1AndUserId2(long userId1, long userId2);
}
