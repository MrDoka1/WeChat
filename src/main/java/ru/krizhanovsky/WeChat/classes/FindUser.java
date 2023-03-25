package ru.krizhanovsky.WeChat.classes;

import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

public class FindUser {

    public FindUser() {
    }

    public UserAuth getUser(UsersAuthRepository usersAuthRepository, String username) { //email
        return usersAuthRepository.findByEmail(username);
    }
}
