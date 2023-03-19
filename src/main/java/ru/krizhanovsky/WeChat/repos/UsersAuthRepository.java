package ru.krizhanovsky.WeChat.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.krizhanovsky.WeChat.models.UserAuth;

import java.util.List;

public interface UsersAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByPhonenumber(String phonenumber);

}
