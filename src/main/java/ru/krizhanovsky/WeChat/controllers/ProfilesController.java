package ru.krizhanovsky.WeChat.controllers;

import org.springframework.web.bind.annotation.RestController;

/*@Controller
public class ProfilesController {

    @Autowired
    private UsersAuthRepository usersAuthRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    // *** Перенаправляет на свою страницу ***
    @GetMapping("/profile")
    public String myProfile(Principal principal) {
        long id = userService.getUser(principal.getName()).getId();
        return "redirect:/profile/" + id;
    }

    @GetMapping("/profile/{nick}")
    public String profile(Model model, Principal principal, @PathVariable(value = "nick") String nick) {
        // *** Конструкция для всех страниц ***
        User user = userService.getUser(principal.getName());
        model.addAttribute("userId", user.getId());
        // *** *** *** *** *** *** *** *** ***

        User userProfile = userService.getUser(Long.valueOf(nick));
        if (userProfile != null) {
            //Написать вывод ошибки
            return "redirect:/profile";
        }

        model.addAttribute("id", userProfile.getId());
        model.addAttribute("firstname", userProfile.getFirstName());
        model.addAttribute("lastname", userProfile.getLastName());
        model.addAttribute("birthdate", userProfile.getBirthDate());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("age", userProfile.getAge());
        model.addAttribute("online", true);
        if (!userProfile.getId().equals(user.getId())) {
            model.addAttribute("online", userProfile.isOnline());
            model.addAttribute("sendMessage", true);
            model.addAttribute("friend", findFriend.getString(userProfile.getId(), user.getId()));
        }
        return "profile";
    }
}*/

@RestController
public class ProfilesController {

}