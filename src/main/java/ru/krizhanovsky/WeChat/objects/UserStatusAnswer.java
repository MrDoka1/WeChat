package ru.krizhanovsky.WeChat.objects;

import java.util.ArrayList;

public class UserStatusAnswer {
    private String[] ListUserStatus;

    public UserStatusAnswer() {
    }

    public UserStatusAnswer(String[] listUserStatus) {
        ListUserStatus = listUserStatus;
    }

    public String[] getListUserStatus() {
        return ListUserStatus;
    }
}
