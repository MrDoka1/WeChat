package ru.krizhanovsky.WeChat.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.krizhanovsky.WeChat.models.UserAuth;

import java.util.Optional;

public interface UsersAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByEmail(String email);
    Optional<UserAuth> findById(Long id);

}
