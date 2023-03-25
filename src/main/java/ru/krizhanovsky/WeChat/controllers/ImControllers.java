package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;

@Controller
public class ImControllers {

    @Autowired
    UsersAuthRepository usersAuthRepository;
    FindUser findUser = new FindUser();

    @GetMapping("/im")
    public String im(Model model, Principal principal, @RequestParam(value = "sel", required = false) String sel) {
        if (sel != null && !sel.isEmpty()) {
            System.out.println(sel.substring(1));
            if (sel.charAt(0) == 'c') {
                sel = sel.substring(1);
                System.out.println(sel);
            }
        }

        System.out.println(findUser.getUser(usersAuthRepository, principal.getName()).getFirstName());
        return "im";
    }

}
