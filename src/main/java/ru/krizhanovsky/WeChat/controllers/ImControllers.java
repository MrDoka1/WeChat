package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.classes.FindPrivateChat;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.PrivateChat;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.PrivateChatsRepository;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;
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

    @GetMapping("/im")
    public String im(Model model, Principal principal, @RequestParam(value = "sel", required = false) String sel) {
        // *** Конструкция для всех страниц ***
        User user = findUser.getUser(principal.getName());
        model.addAttribute("userId", user.getId());
        // *** *** *** *** *** *** *** *** ***


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
                if (id != 0) {
                    model.addAttribute("id", id);
                    return "imSel";
                }
            }
        }

        List<PrivateChat> privateChatList = (List<PrivateChat>) privateChatsRepository.findByUserId1OrUserId2(user.getId(), user.getId());
        if (privateChatList != null) {
            model.addAttribute("privateChatList", privateChatList);
        }

        return "im";
    }

}
