package ru.krizhanovsky.WeChat.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.krizhanovsky.WeChat.models.UserAuth;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UsersAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByEmail(String email);
    Optional<UserAuth> findById(Long id);

    @Transactional
    @Modifying
    @Query("update UserAuth u set u.lastOnline = :online where u.id = :id")
    void updateUsersAuthLastOnline(@Param("online") LocalDateTime online, @Param("id") Long id);

}
