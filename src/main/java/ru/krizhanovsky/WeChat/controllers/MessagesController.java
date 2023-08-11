package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.classes.FindPrivateChat;

@RestController
public class MessagesController {
    @Autowired
    FindPrivateChat findPrivateChat;


    @GetMapping("/messages/{chatId}")
    public Long messages(@PathVariable(value = "chatId") String chatId, @RequestParam(value = "id") String id) {
        Long chat = findPrivateChat.getPrivateChatId(Long.parseLong(chatId), Long.parseLong(id));

        return chat;
    }
}
