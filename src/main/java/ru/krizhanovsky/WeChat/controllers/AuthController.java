package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.classes.Password;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;


@Controller
public class AuthController {
    @Autowired
    private UsersAuthRepository usersAuthRepository;

    @GetMapping("/auth")
    public String auth(Principal principal) {
        if(principal != null) {
            return "redirect:/profile";
        }
        return "auth";
    }

    @PostMapping("/auth")
    public String auth2(Model model, @RequestParam(required=false) String email, @RequestParam(required=false) String password,
                       @RequestParam(required=false) String copypassword, @RequestParam(required=false) String firstname, @RequestParam(required=false) String lastname,
                        @RequestParam String birthdate) {

        if (password != null && copypassword != null) {
            if (password.equals(copypassword) && !email.isEmpty()) {
                // Вторая страница регистрации
                if (firstname != null && lastname != null && birthdate != null && !firstname.isEmpty() && !lastname.isEmpty() && !birthdate.isEmpty()) {
                    Password password1 = new Password();
                    UserAuth userAuth = new UserAuth(email, password1.encodePassword(password), firstname, lastname, birthdate);
                    usersAuthRepository.save(userAuth);
                    return "redirect:/login";

                }
            }
        }
        return "redirect:/auth?error";
    }
}
