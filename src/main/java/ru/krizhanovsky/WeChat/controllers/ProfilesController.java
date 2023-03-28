package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.krizhanovsky.WeChat.classes.FindUser;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;
import ru.krizhanovsky.WeChat.repos.UsersRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
public class ProfilesController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersAuthRepository usersAuthRepository;

    FindUser findUser = new FindUser();

    // *** Перенаправляет на свою страницу ***
    @GetMapping("/profile")
    public String profil(Model model, Principal principal) {
        long id = findUser.getUser(usersAuthRepository, principal.getName()).getId();
        return "redirect:/profile/" + id;
    }

    @GetMapping("/profile/{nick}")
    public String profile(Model model, Principal principal, @PathVariable(value = "nick") String nick) {

        Optional<UserAuth> user = usersAuthRepository.findById(Long.valueOf(nick));
        UserAuth userAuth = findUser.getUser(usersAuthRepository, principal.getName());
        if (user.isEmpty()) {
            //Написать вывод ошибки
            return "redirect:/profile";
        }

        model.addAttribute("id", user.get().getId());
        model.addAttribute("firstname", user.get().getFirstName());
        model.addAttribute("lastname", user.get().getLastName());
        model.addAttribute("birthdate", user.get().getBirthdate());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("age", ChronoUnit.YEARS.between(LocalDate.parse(user.get().getBirthdate()), LocalDate.now()));
        model.addAttribute("online", true);
        if (!user.get().getId().equals(userAuth.getId())) {
            model.addAttribute("online", user.get().isOnline());
            model.addAttribute("sendMessage", true);
            model.addAttribute("no_friend", true);
        }


        return "profile";
    }


}
