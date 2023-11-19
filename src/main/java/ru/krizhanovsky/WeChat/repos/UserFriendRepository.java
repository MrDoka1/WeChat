package ru.krizhanovsky.WeChat.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserFriend;

import java.util.List;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend,Long> {
    List<UserFriend> findByUser(User user);
    UserFriend findByUserAndFriend(User user, User friend);
    void deleteUserFriendByUserAndFriend(User user, User friend);
    long countUserFriendsByUser(User user);
    long countUserFriendsByFriend(User user);
    @Query("select uf.friend.id from UserFriend uf where uf.user = :user and exists (select uf2.friend from UserFriend uf2 where uf2.user = uf.friend and uf2.friend = :user)")
    List<Long> getFriendsIds(User user);
    @Query("select uf.user.id from UserFriend uf where uf.friend = :user and not exists (select uf2.friend from UserFriend uf2 where uf2.user = :user and uf2.friend = uf.user)")
    List<Long> getSubscribersIds(User user);
}
