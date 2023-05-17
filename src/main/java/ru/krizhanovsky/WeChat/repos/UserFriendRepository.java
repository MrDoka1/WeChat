package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.UserFriend;

import java.util.List;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend,Long> {
    List<UserFriend> findByUserId(Long userId);
    List<UserFriend> findByUserIdAndFriendId(Long userId, Long friendId);
}
