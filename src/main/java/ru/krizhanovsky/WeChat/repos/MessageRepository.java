package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.Message;

import java.util.List;

@Repository
public interface MessageRepository extends ListCrudRepository<Message, Long> {
    List<Message> findByChat(Chat chat);
    List<Message> findByDialog(Dialog dialog);

    @Query("select m from Message m where m.chat = :chat order by m.id desc limit 1")
    Message findLastByChat(Chat chat);
    //Message findFirstByChatOrderByIdDesc(Chat chat);
    @Query("select m from Message m where m.dialog = :dialog order by m.id desc limit 1")
    Message findLastByDialog(Dialog dialog);

    List<Message> findFirst10ByIdBeforeAndChatOrderByIdDesc(Long id, Chat chat);
    List<Message> findFirst10ByChatOrderByIdDesc(Chat chat);
    List<Message> findFirst10ByIdBeforeAndDialogOrderByIdDesc(Long id, Dialog dialog);
    List<Message> findFirst10ByDialogOrderByIdDesc(Dialog dialog);


}
