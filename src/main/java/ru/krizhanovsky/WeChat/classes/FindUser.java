package ru.krizhanovsky.WeChat.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;
import ru.krizhanovsky.WeChat.repos.UsersRepository;

import java.util.Optional;

@Component
public class FindUser {
    @Autowired
    UsersAuthRepository usersAuthRepository;
    @Autowired
    UsersRepository usersRepository;

    public FindUser() {
    }

    public User getUser(String username) { //email
        return usersAuthRepository.findByEmail(username).getUser();
    }

    public Optional<User> getUser(Long id) {
        return usersRepository.findById(id);
    }
}
