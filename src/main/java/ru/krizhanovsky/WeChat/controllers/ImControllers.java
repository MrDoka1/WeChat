package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.classes.FindPrivateChat;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.PrivateChatsRepository;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;

@Controller
public class ImControllers {

    @Autowired
    UsersAuthRepository usersAuthRepository;
    FindUser findUser = new FindUser();
    @Autowired
    PrivateChatsRepository privateChatsRepository;
    FindPrivateChat findPrivateChat = new FindPrivateChat();

    @GetMapping("/im")
    public String im(Model model, Principal principal, @RequestParam(value = "sel", required = false) String sel) {
        UserAuth userAuth = findUser.getUser(usersAuthRepository, principal.getName());


        if (sel != null && !sel.isEmpty()) {
            // ** Проверка - является ли чат беседой **
            if (sel.charAt(0) == 'c') {
                sel = sel.substring(1);
                System.out.println(sel);
            } else {
                // ** Получаем собеседника **
                UserAuth userAuthInterlocutor = usersAuthRepository.findById(Long.parseLong(sel)).get();
                model.addAttribute("interlocutor", userAuthInterlocutor);

                long id = findPrivateChat.getPrivateChatId(userAuth.getId(), Long.parseLong(sel), privateChatsRepository);
                if (id != 0) {
                    model.addAttribute("id", id);
                    model.addAttribute("userId", userAuth.getId());
                    return "imSel";
                }
            }
        }

        return "im";
    }

}
