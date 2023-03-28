package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.objects.Greeting;
import ru.krizhanovsky.WeChat.objects.HelloMessage;
import ru.krizhanovsky.WeChat.objects.UserStatusAnswer;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class GreetingController {
    @Autowired
    private UsersAuthRepository usersAuthRepository;

    FindUser findUser = new FindUser();

    @MessageMapping("/u/{uId}")
    @SendTo("/topic/u/{uId}")
    public UserStatusAnswer greeting1(HelloMessage message, Principal principal) throws Exception {
        UserAuth userAuth = findUser.getUser(usersAuthRepository, principal.getName());
        usersAuthRepository.updateUsersAuthLastOnline(LocalDateTime.now(), userAuth.getId());

        return new UserStatusAnswer(new String[]{"2"});
    }



    @MessageMapping("/{id}")
    @SendTo("/topic/{id}")
    public Greeting greeting(HelloMessage message, Principal principal) throws Exception {

        UserAuth userAuth = findUser.getUser(usersAuthRepository, principal.getName());

        Thread.sleep(500); // simulated delay
        return new Greeting(userAuth.getFirstName() + ": " + HtmlUtils.htmlEscape(message.getName()));
    }

}
