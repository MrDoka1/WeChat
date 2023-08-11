package ru.krizhanovsky.WeChat.classes;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;
import ru.krizhanovsky.WeChat.repos.UsersRepository;

@Service
public class SaveUser {
    @Autowired
    private UsersAuthRepository usersAuthRepository;
    @Autowired
    private UsersRepository usersRepository;

    public SaveUser() {
    }

    @Transactional
    public void saveUser(String nick, String firstName, String lastName, String birthDate,
                         String email, String password) {
        User user = new User();
    }
    @Transactional
    public void saveUser(String firstName, String lastName, String birthDate,
                         String email, String password) {
        User user = new User(firstName, lastName, birthDate, false);
        UserAuth userAuth = new UserAuth(email, password, user);
        save(user, userAuth);
    }

    // ** Сохранение в бд **
    private void save(User user, UserAuth userAuth) {
        usersAuthRepository.save(userAuth);
        usersRepository.save(user);
    }
}
