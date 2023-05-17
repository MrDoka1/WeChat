package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.UsersRepository;

import java.security.Principal;

@Controller
public class FriendsController {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    FindUser findUser;

    @GetMapping("/friends")
    public String friends(Principal principal, Model model, @RequestParam(required = false) String act) {
        // *** Конструкция для всех страниц ***
        User user = findUser.getUser(principal.getName());
        model.addAttribute("userId", user.getId());
        // *** *** *** *** *** *** *** *** ***


        if (act != null && act.equals("find")) {
            model.addAttribute("users", usersRepository.findAll());
        }

        return "friends";
    }
}
