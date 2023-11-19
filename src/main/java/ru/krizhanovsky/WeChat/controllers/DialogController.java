package ru.krizhanovsky.WeChat.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.classes.FindDialog;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.security.Principal;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class DialogController {
    private final UserService userService;
    private final FindDialog findDialog;

    @GetMapping("/dialog")
    public HashMap<String, Object> dialog(Principal principal, HttpServletResponse httpServletResponse, @RequestParam(value = "id") Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        User user = userService.getUser(principal.getName());
        long DialogId = findDialog.getDialogId(user, userService.getUser(id));
        hashMap.put("id", DialogId);
        hashMap.put("userId", id);
        return hashMap;
    }
}
