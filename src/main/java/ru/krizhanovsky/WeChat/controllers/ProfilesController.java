package ru.krizhanovsky.WeChat.controllers;

import org.hibernate.query.sqm.TemporalUnit;
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
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
public class ProfilesController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersAuthRepository usersAuthRepository;

    FindUser findUser = new FindUser();

    @GetMapping("/profile")
    public String profil(Model model, Principal principal) {
        long id = findUser.getUser(usersAuthRepository, principal.getName()).getId();
        return "redirect:/profile/" + id;
    }

    @GetMapping("/profile/{nick}")
    public String profile(Model model, Principal principal, @PathVariable(value = "nick") String nick) {
//        List<User> users = usersRepository.findByNick(nick);
//        if (users.size() > 0) {
//            for (User user : users) {
//                model.addAttribute("firstname", user.getFirstName());
//                model.addAttribute("lastname", user.getLastName());
//                model.addAttribute("birthdate", user.getBirthDate());
//            }
//            return "profile";
//        }

        Optional<UserAuth> user = usersAuthRepository.findById(Long.valueOf(nick));
        if (user.isEmpty()) {
            return "redirect:/profile";
        }

        model.addAttribute("firstname", user.get().getFirstName());
        model.addAttribute("lastname", user.get().getLastName());
        model.addAttribute("birthdate", user.get().getBithdate());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("age", ChronoUnit.YEARS.between(LocalDate.parse(user.get().getBithdate()), LocalDate.now()));
        model.addAttribute("friend", true);
        model.addAttribute("no_friend", true);


        return "profile";
    }


}
