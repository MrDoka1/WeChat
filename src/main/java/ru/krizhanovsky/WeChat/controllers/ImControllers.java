package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.classes.ChatOutput;
import ru.krizhanovsky.WeChat.classes.FindPrivateChat;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.MessageInChat;
import ru.krizhanovsky.WeChat.models.PrivateChat;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.MessageInChatRepository;
import ru.krizhanovsky.WeChat.repos.PrivateChatsRepository;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ImControllers {

    @Autowired
    UsersAuthRepository usersAuthRepository;
    @Autowired
    FindUser findUser;
    @Autowired
    FindPrivateChat findPrivateChat;
    @Autowired
    PrivateChatsRepository privateChatsRepository;
    @Autowired
    MessageInChatRepository messageInChatRepository;

    @GetMapping("/im")
    public String im(Model model, Principal principal, @RequestParam(value = "sel", required = false) String sel) {
        // *** Конструкция для всех страниц ***
        User user = findUser.getUser(principal.getName());
        model.addAttribute("userId", user.getId());
        // *** *** *** *** *** *** *** *** ***

        List<PrivateChat> privateChatList = privateChatsRepository.findByUserId1OrUserId2(user.getId(), user.getId());

        // ** Вывод чатиков **
        if (!privateChatList.isEmpty()) {
            List<ChatOutput> chatOutputList = new ArrayList<>();
            for (PrivateChat privateChat: privateChatList) {
                List<MessageInChat> list = messageInChatRepository.findByChatId(privateChat.getId());
                chatOutputList.add(new ChatOutput(privateChat, user, list, Long.parseLong(sel)));
            }

            // ** Лист для обновления статуса **
            List<Long> idList = new ArrayList<>();
            for (ChatOutput chat: chatOutputList) {
                idList.add(chat.getId());
            }
            model.addAttribute("idList", idList);
            // ** ** ** ** ** ** ** ** ** ** ** **

            model.addAttribute("chats", chatOutputList);
        }

        if (sel != null && !sel.isEmpty()) {
            // ** Проверка - является ли чат беседой **
            if (sel.charAt(0) == 'c') {
                sel = sel.substring(1);
                System.out.println(sel);
            } else {
                // ** Получаем собеседника **
                Optional<User> userInterlocutorOptional = findUser.getUser(Long.parseLong(sel));
                model.addAttribute("interlocutor", userInterlocutorOptional.get());

                long id = findPrivateChat.getPrivateChatId(user.getId(), Long.parseLong(sel));

                // ** Вывод сообщений в чат **
                List<MessageInChat> messages = messageInChatRepository.findByChatId(id);
                model.addAttribute("messages", messages);

                if (id != 0) {
                    model.addAttribute("id", id);
                    return "imSel";
                }
            }
        }

        return "im";
    }

}
