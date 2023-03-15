package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.repository.CrudRepository;
import ru.krizhanovsky.WeChat.models.Chats;

public interface ChatsRepository extends CrudRepository<Chats, Long> {
}
