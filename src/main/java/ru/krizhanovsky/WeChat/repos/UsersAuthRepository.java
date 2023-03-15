package ru.krizhanovsky.WeChat.repos;


import org.springframework.data.repository.CrudRepository;
import ru.krizhanovsky.WeChat.models.UserAuth;

import java.util.List;

public interface UsersAuthRepository extends CrudRepository<UserAuth, Long> {
    List<UserAuth> findByPhonenumber(String phonenumber);

}
