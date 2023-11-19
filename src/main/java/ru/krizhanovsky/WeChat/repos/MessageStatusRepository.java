package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.repository.CrudRepository;
import ru.krizhanovsky.WeChat.models.MessageStatus;

public interface MessageStatusRepository extends CrudRepository<MessageStatus, Long> {
}
