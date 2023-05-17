package ru.krizhanovsky.WeChat.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserAuth;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsersAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByEmail(String email);
    User findUserByEmail(String email);

    Optional<UserAuth> findById(Long id);

}
