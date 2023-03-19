package ru.krizhanovsky.WeChat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.classes.Password;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.util.List;

@Controller
public class LoginController {
    private UsersAuthRepository usersAuthRepository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login1")
    public String postLogin(Model model, @RequestParam String phonenumber, @RequestParam String password) {
        System.out.println(phonenumber + password);
        UserAuth users = usersAuthRepository.findByPhonenumber(phonenumber);
        // Что-то странное

        return "redirect:/auth";
    }

}
