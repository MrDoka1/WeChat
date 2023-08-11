package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.krizhanovsky.WeChat.classes.FindFriend;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
public class ProfilesController {

    @Autowired
    private UsersAuthRepository usersAuthRepository;
    @Autowired
    private FindUser findUser;
    @Autowired
    private FindFriend findFriend;

    // *** Перенаправляет на свою страницу ***
    @GetMapping("/profile")
    public String myProfile(Principal principal) {
        long id = findUser.getUser(principal.getName()).getId();
        return "redirect:/profile/" + id;
    }

    @GetMapping("/profile/{nick}")
    public String profile(Model model, Principal principal, @PathVariable(value = "nick") String nick) {
        // *** Конструкция для всех страниц ***
        User user = findUser.getUser(principal.getName());
        model.addAttribute("userId", user.getId());
        // *** *** *** *** *** *** *** *** ***

        Optional<User> userProfile = findUser.getUser(Long.valueOf(nick));
        if (userProfile.isEmpty()) {
            //Написать вывод ошибки
            return "redirect:/profile";
        }

        model.addAttribute("id", userProfile.get().getId());
        model.addAttribute("firstname", userProfile.get().getFirstName());
        model.addAttribute("lastname", userProfile.get().getLastName());
        model.addAttribute("birthdate", userProfile.get().getBirthDate());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("age", userProfile.get().getAge());
        model.addAttribute("online", true);
        if (!userProfile.get().getId().equals(user.getId())) {
            model.addAttribute("online", userProfile.get().isOnline());
            model.addAttribute("sendMessage", true);
            model.addAttribute("friend", findFriend.getString(userProfile.get().getId(), user.getId()));
        }


        return "profile";
    }


}
