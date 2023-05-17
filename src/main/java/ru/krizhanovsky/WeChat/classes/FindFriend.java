package ru.krizhanovsky.WeChat.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.UserFriend;
import ru.krizhanovsky.WeChat.repos.UserFriendRepository;

import java.util.List;

@Service
public class FindFriend {
    @Autowired
    private UserFriendRepository userFriendRepository;

    public FindFriend() {
    }

    public String getString(long userId, long friendId) {
        List<UserFriend> friendList = userFriendRepository.findByUserIdAndFriendId(userId, friendId);
        List<UserFriend> friendList2 = userFriendRepository.findByUserIdAndFriendId(friendId, userId);

        // ** Друзья
        if (!friendList.isEmpty() && !friendList2.isEmpty()) {
            return "false";
        }
        // ** Не друзья
        if (friendList.isEmpty() && friendList2.isEmpty()) {
            return "Добавить в друзья";
        }
        // ** На user подписан
        if (friendList.isEmpty()) {
            return "Ответить на заявку";
        }
        // ** user подписан
        return "Отменить заявку";
    }
}
