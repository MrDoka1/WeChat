package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.MessageInChat;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.objects.*;
import ru.krizhanovsky.WeChat.repos.MessageInChatRepository;
import ru.krizhanovsky.WeChat.repos.UsersRepository;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class GreetingController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private FindUser findUser;
    @Autowired
    private MessageInChatRepository messageInChatRepository;

    @MessageMapping("/u/{uId}")
    @SendTo("/topic/u/{uId}")
    public UserStatusAnswer greeting1(UserStatusMessage message, Principal principal) throws Exception {
        User user = findUser.getUser(principal.getName());
        usersRepository.updateUserLastOnline(LocalDateTime.now(), user.getId());

        return new UserStatusAnswer(new String[]{"2"});
    }



    @MessageMapping("/{id}")
    @SendTo("/topic/{id}")
    public AnswerMessage greeting(Message message, Principal principal) throws Exception {
        User user = findUser.getUser(principal.getName());

        LocalDateTime time = LocalDateTime.now();
        MessageInChat messageInChat = new MessageInChat(message.getChatId(), user.getId(), message.getContent(), time);
        messageInChatRepository.save(messageInChat);
        System.out.println(messageInChat.getId());

        return new AnswerMessage(HtmlUtils.htmlEscape(message.getContent()), time, user.getId());
    }

}
