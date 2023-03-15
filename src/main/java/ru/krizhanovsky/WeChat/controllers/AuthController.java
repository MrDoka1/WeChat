package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;


@Controller
public class AuthController {
    @Autowired
    private UsersAuthRepository usersAuthRepository;

    private String phonenumber;

    @GetMapping("/auth")
    public String auth(Model model) {
        return "auth";
    }

    @PostMapping("/auth")
        public String auth2(Model model, @RequestParam(required=false) String phonenumber, @RequestParam(required=false) String password,
                           @RequestParam(required=false) String copypassword, @RequestParam(required=false) String firstname, @RequestParam(required=false) String lastname,
                            @RequestParam(required=false) String birthdate,
                            @RequestParam(required=false) String nick) {

            // Первая страница регистрации
            if (password != null && copypassword != null) {
                if (password.equals(copypassword) && !phonenumber.isEmpty()) {
                    UserAuth usersAuth = new UserAuth(phonenumber, password, false);
                    usersAuthRepository.save(usersAuth);
                    this.phonenumber = phonenumber;
                    return "auth2";
                }
            }

            // Вторая страница регистрации
            if (firstname != null && lastname != null && birthdate != null && !firstname.isEmpty() && !lastname.isEmpty() && !birthdate.isEmpty()) {
                if (nick != null && !nick.isEmpty()) {
                    System.out.println(this.phonenumber);
                    User users = new User();
                }
            }
            return "redirect:/auth";
        }
}
