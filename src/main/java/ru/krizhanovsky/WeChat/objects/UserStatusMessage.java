package ru.krizhanovsky.WeChat.objects;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserStatusMessage {
    private ArrayList<Long> arrayListUsers; // Для обновления статусов пользователей
    private Long getMaessage;

    public UserStatusMessage() {
    }

    public UserStatusMessage(ArrayList<Long> arrayListUsers) {
        this.arrayListUsers = arrayListUsers;
    }

}
