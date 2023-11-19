package ru.krizhanovsky.WeChat.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.UserRepository;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CheckControllers {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/check/authorization")
    public Map<String, Object> checkAuthorization(Principal principal, HttpServletResponse httpServletResponse) {
        HashMap<String, Object> hashMap = new HashMap<>();

        if (principal != null) {
            hashMap.put("authorization", true);
            User user = userService.getUser(principal.getName());
            userRepository.updateUserLastOnline(LocalDateTime.now(), user.getId());
            hashMap.put("id", user.getId());
        } else {
            hashMap.put("authorization", false);
        }

        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        return hashMap;
    }
}
