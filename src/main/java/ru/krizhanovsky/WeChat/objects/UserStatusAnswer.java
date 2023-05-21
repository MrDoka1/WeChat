package ru.krizhanovsky.WeChat.objects;

import lombok.Data;
import ru.krizhanovsky.WeChat.models.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserStatusAnswer {
    private List<User> userList;

    public UserStatusAnswer() {
        userList = new ArrayList<>();
    }

    public void add(User user) {
        userList.add(user);
    }
}
