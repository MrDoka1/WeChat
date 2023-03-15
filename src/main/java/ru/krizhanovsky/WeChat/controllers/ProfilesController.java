package ru.krizhanovsky.WeChat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.UsersRepository;

import java.util.List;

@Controller
public class ProfilesController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/{nick}")
    public String profile(Model model, @PathVariable(value = "nick") String nick) {
        List<User> users = usersRepository.findByNick(nick);
        if (users.size() > 0) {
            for (User user : users) {
                model.addAttribute("firstname", user.getFirstName());
                model.addAttribute("lastname", user.getLastName());
                model.addAttribute("birthdate", user.getBirthDate());
            }
            return "profile";
        }
        return "auth"; // Убрать
    }


}
