package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.repository.CrudRepository;
import ru.krizhanovsky.WeChat.models.User;

import java.util.List;
public interface UsersRepository extends CrudRepository<User, Long> {
    List<User> findByNick(String nick);
    List<User> findByUserId(Long userId);
}
