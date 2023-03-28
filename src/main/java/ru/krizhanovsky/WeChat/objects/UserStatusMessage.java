package ru.krizhanovsky.WeChat.objects;

import java.util.ArrayList;

public class UserStatusMessage {
    private ArrayList<Long> arrayListUsers; // Для обновления статусов пользователей

    public UserStatusMessage() {
    }

    public UserStatusMessage(ArrayList<Long> arrayListUsers) {
        this.arrayListUsers = arrayListUsers;
    }

    public ArrayList<Long> getArrayListUsers() {
        return arrayListUsers;
    }

    public void setArrayListUsers(ArrayList<Long> arrayListUsers) {
        this.arrayListUsers = arrayListUsers;
    }
}
