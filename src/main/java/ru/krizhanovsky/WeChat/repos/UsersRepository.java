package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.krizhanovsky.WeChat.models.User;

import java.time.LocalDateTime;
import java.util.List;
public interface UsersRepository extends CrudRepository<User, Long> {
    List<User> findByNick(String nick);

    @Transactional
    @Modifying
    @Query("update User u set u.lastOnline = :online where u.id = :id")
    void updateUserLastOnline(@Param("online") LocalDateTime online, @Param("id") Long id);
}
