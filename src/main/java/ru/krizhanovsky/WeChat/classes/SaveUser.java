package ru.krizhanovsky.WeChat.classes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UserAuthRepository;
import ru.krizhanovsky.WeChat.repos.UserRepository;

@Service
@RequiredArgsConstructor
public class SaveUser {
    private final UserAuthRepository userAuthRepository;
    private final UserRepository userRepository;
    private final Password encoder;

    @Transactional
    public void saveUser(String nick, String firstName, String lastName, String birthDate,
                         String email, String password) {
        // Не сделано
    }
    @Transactional
    public void saveUser(String firstName, String lastName, String birthDate,
                         String email, String password) {
        User user = new User(firstName, lastName, birthDate, false);
        UserAuth userAuth = new UserAuth(email, encoder.encodePassword(password), user);
        save(user, userAuth);
    }

    // ** Сохранение в бд **
    private void save(User user, UserAuth userAuth) {
        userAuthRepository.save(userAuth);
        userRepository.save(user);
    }
}
