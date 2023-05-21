package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.PrivateChat;

import java.util.List;


@Repository
public interface PrivateChatsRepository extends JpaRepository<PrivateChat, Long> {
    @Query("select p from PrivateChat p where p.userId1.id = ?1 and p.userId2.id = ?2")
    PrivateChat findByUserId1AndUserId2(long userId1, long userId2);

    @Query("select p from PrivateChat p where p.userId1.id = ?1 or p.userId2.id = ?2")
    List<PrivateChat> findByUserId1OrUserId2(long userId1, long userId2);

}
