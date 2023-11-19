package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.krizhanovsky.WeChat.classes.FindDialog;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.objects.AnswerMessage;
import ru.krizhanovsky.WeChat.objects.UserStatusAnswer;
import ru.krizhanovsky.WeChat.objects.UserStatusMessage;
import ru.krizhanovsky.WeChat.repos.MessageRepository;
import ru.krizhanovsky.WeChat.repos.UserRepository;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private FindDialog findDialog;

    @MessageMapping("/u/{uId}")
    @SendTo("/topic/u/{uId}")
    public UserStatusAnswer greeting1(UserStatusMessage message, Principal principal) throws Exception {
        User user = userService.getUser(principal.getName());
        userRepository.updateUserLastOnline(LocalDateTime.now(), user.getId());
        List<Long> listOnline = message.getArrayListUsers();
        UserStatusAnswer userStatusAnswer = new UserStatusAnswer();

        for (long id: listOnline) {
            User user1 = userRepository.findById(id).get();
            userStatusAnswer.add(user1);
        }

//        Long chatIdMessages = message.getGetMaessage();
//        if (chatIdMessages != null) {
//            long chatId = findDialog.getDialogId(user, chatIdMessages);
//            List<Message> messages = messageRepository.findByChatId(chatId);
//            userStatusAnswer.setMessages(messages);
//        }

        return userStatusAnswer;
    }



    @MessageMapping("/{id}")
    @SendTo("/topic/{id}")
    public AnswerMessage greeting(ru.krizhanovsky.WeChat.objects.Message message, Principal principal) throws Exception {
        User user = userService.getUser(principal.getName());

        LocalDateTime time = LocalDateTime.now();
//        MessageInChat messageInChat = new MessageInChat(message.getChatId(), user.getId(), message.getContent(), time);
//        messageInChatRepository.save(messageInChat);

        return new AnswerMessage(HtmlUtils.htmlEscape(message.getContent()), time, user.getId());
    }

}
