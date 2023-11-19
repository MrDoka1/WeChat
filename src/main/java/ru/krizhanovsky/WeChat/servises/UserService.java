package ru.krizhanovsky.WeChat.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.UserAuthRepository;
import ru.krizhanovsky.WeChat.repos.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;

    public List<LocalDateTime> getUsersOnline(List<Long> ids) {
        List<LocalDateTime> list = new ArrayList<>();
        for (long id : ids) {
            list.add(userRepository.findLastOnlineById(id));
        }
        return list;
    }

    public User getUser(String username) { //email
        return userAuthRepository.findByEmail(username).getUser();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
