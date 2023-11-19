package ru.krizhanovsky.WeChat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.repos.UserAuthRepository;

import java.security.Principal;

@Controller
public class LoginController {
    private UserAuthRepository userAuthRepository;

    @GetMapping("/login")
    public String login(Principal principal, @RequestParam(required = false) String logout) {
        if(principal != null && logout == null) {
            return "redirect:/profile";
        }
        return "login";
    }
}
