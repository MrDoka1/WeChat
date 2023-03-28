package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

@Controller
public class FriendsController {
    @Autowired
    UsersAuthRepository usersAuthRepository;

    @GetMapping("/friends")
    public String friends(Model model, @RequestParam(required = false) String act) {
        if (act != null && act.equals("find")) {
            model.addAttribute("users", usersAuthRepository.findAll());
        }

        return "friends";
    }
}
