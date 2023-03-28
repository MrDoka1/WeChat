package ru.krizhanovsky.WeChat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;

@Controller
public class LoginController {
    private UsersAuthRepository usersAuthRepository;

    @GetMapping("/login")
    public String login(Principal principal) {
        if(principal != null) {
            return "redirect:/profile";
        }
        return "login";
    }
}
