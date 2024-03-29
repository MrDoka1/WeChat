package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
