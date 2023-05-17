package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.objects.AnswerClientMessages;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;

@Controller
public class ClientMessageController {
    @Autowired
    private UsersAuthRepository usersAuthRepository;
    @Autowired
    private FindUser findUser;
    @MessageMapping("/queue")
    public AnswerClientMessages answerClientMessages(Principal principal) {
        return new AnswerClientMessages();
    }
}
