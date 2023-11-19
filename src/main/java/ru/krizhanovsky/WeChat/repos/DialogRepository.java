package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.User;

import java.util.List;


@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    @Query("select p from Dialog p where p.user1 = ?1 and p.user2 = ?2")
    Dialog findByUser1AndUser2(User user1, User user2);

    @Query("select p from Dialog p where p.user1 = ?1 or p.user2 = ?2")
    List<Dialog> findByUser1OrUser2(User user1, User user2);

}
