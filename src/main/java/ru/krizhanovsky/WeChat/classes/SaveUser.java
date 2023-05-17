package ru.krizhanovsky.WeChat.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;
import ru.krizhanovsky.WeChat.repos.UsersRepository;

@Service
public class SaveUser {
    @Autowired
    private UsersAuthRepository usersAuthRepository;
    @Autowired
    private UsersRepository usersRepository;


}
