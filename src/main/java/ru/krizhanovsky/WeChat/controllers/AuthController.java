package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;
import java.util.Base64;


@Controller
public class AuthController {
    @Autowired
    private UsersAuthRepository usersAuthRepository;

    @GetMapping("/auth")
    public String auth(Model model, Principal principal, @RequestParam(value = "e",required = false) String emailCode) {
        // ** Если пользователь авторизован **
        if(principal != null) {
            return "redirect:/profile";
        }
        // ** Если первый раз зашёл на страницу регистрации **
        if (emailCode == null) {
            return "preAuth";
        }
        // ** Переход на вторую страницу **
        String email = new String(Base64.getDecoder().decode(emailCode));

        // ** Проверка существует ли пользователь **
        UserAuth userAuth = usersAuthRepository.findByEmail(email);
        if (userAuth != null) {
            // Написать ошибку о том, что пользователь существует
            return "preAuth";
        }

        model.addAttribute("email", email);
        model.addAttribute("inputEmail", email);


        return "auth_page1";
    }

    @PostMapping("/auth")
    public String auth1(@RequestParam String email) {
        // ** Перенаправляем с кодированным емайлом **
        String emailCode = Base64.getEncoder().encodeToString(email.getBytes());
        return "redirect:/auth?e=" + emailCode;
    }



    @PostMapping("/auth_next")
    public String auth2(@RequestParam String email, @RequestParam String password,
                       @RequestParam String copyPassword, @RequestParam String firstname, @RequestParam String lastname,
                        @RequestParam String birthdate) {

        if (password != null && copyPassword != null && firstname != null && lastname != null && birthdate != null && email != null) {
            System.out.println(email + " " + password + " " + copyPassword + " " + firstname + " " + lastname + " " + birthdate);
            return "redirect:/login";
        }

        return "redirect:/auth?error";
    }
}
