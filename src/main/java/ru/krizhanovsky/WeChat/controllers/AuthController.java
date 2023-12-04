package ru.krizhanovsky.WeChat.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.classes.SaveUser;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UserAuthRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserAuthRepository userAuthRepository;
    private final SaveUser saveUser;

    @GetMapping("/auth")
    public boolean authGet(Principal principal, @RequestParam(value = "code") String code, HttpServletResponse httpServletResponse) throws IllegalArgumentException {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            String login = new String(Base64.getDecoder().decode(code));
            // Не содержит пробелы, длина >8, пользователь не авторизован
            return !login.contains(" ") && login.length() > 8 && principal == null && userAuthRepository.findByEmail(login) == null;
        } catch (Exception e) {
            return false;
        }

    }

    @PostMapping("/auth")
    public HashMap<String, String> auth(Principal principal, @RequestParam(value = "login") String login, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        HashMap<String, String> hashMap = new HashMap<>();
        if (principal == null) {
            UserAuth userAuth = userAuthRepository.findByEmail(login);
            if (userAuth == null) {
                String code = Base64.getEncoder().encodeToString(login.getBytes());
                hashMap.put("success", "true");
                hashMap.put("code", code);
                return hashMap;
            }
        }
        hashMap.put("success", "false");
        return hashMap;
    }

    @PostMapping("/registration")
    public boolean registration(Principal principal, @RequestParam String login, @RequestParam String password,
                                @RequestParam String copyPassword, @RequestParam String firstname, @RequestParam String lastname,
                                @RequestParam String birthdate, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        if (principal == null && login.length() >= 8 && userAuthRepository.findByEmail(login) == null) {
            if (password.length() >= 8 && password.equals(copyPassword)) {
                if (!firstname.contains(" ") && firstname.length() >= 2) {
                    if (!lastname.contains(" ") && lastname.length() >= 2) {
                        if (LocalDate.parse(birthdate).isBefore(LocalDate.now())) {
                            saveUser.saveUser(firstname, lastname, birthdate, login, password);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");


        return null;
    }
}



/*@Controller
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
}*/