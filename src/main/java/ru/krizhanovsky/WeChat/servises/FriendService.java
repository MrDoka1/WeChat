package ru.krizhanovsky.WeChat.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.krizhanovsky.WeChat.classes.Friend;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserFriend;
import ru.krizhanovsky.WeChat.repos.UserFriendRepository;
import ru.krizhanovsky.WeChat.repos.UserRepository;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final UserFriendRepository userFriendRepository;
    private final UserRepository userRepository;

    public Friend getFriend(User user, User friend) {
        UserFriend userFriend = userFriendRepository.findByUserAndFriend(user, friend);
        UserFriend userFriend2 = userFriendRepository.findByUserAndFriend(friend, user);

        // ** Друзья
        if (userFriend != null && userFriend2 != null) {
            return Friend.FRIENDS;
        }
        // ** Не друзья
        if (userFriend == null && userFriend2 == null) {
            return Friend.NO_FRIENDS;
        }
        // ** На user подписан
        if (userFriend == null) {
            return Friend.HE_SUBSCRIBER;
        }
        // ** user подписан
        return Friend.I_SUBSCRIBER;
    }

    @Transactional
    public Friend addAndGetFriend(User user, User friend) {
        userFriendRepository.save(new UserFriend(user, friend));
        return getFriend(user, friend);
    }

    @Transactional
    public Friend removeAndGetFriend(User user, User friend) {
        userFriendRepository.deleteUserFriendByUserAndFriend(user, friend);
        return getFriend(user, friend);
    }

    public List<Long> getFriendsIds(User user) {
        return userFriendRepository.getFriendsIds(user);
    }

    public List<Long> getSubscribers(User user) {
        return userFriendRepository.getSubscribersIds(user);
    }

    public HashSet<Long> getSearchUsersIds(User user) {
        HashSet<Long> search = userRepository.getSearchIdsUsers(user);
        this.getFriendsIds(user).forEach(search::remove);
        this.getSubscribers(user).forEach(search::remove);
        return search;
    }
}
