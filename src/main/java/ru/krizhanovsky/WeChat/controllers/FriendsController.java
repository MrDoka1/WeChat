package ru.krizhanovsky.WeChat.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.classes.Friend;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.servises.FriendService;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendsController {
    private final FriendService friendService;
    private final UserService userService;

    @PostMapping("/friend")
    public Object friend(Principal principal, @RequestParam String action, @RequestParam Long id, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            User requester = userService.getUser(principal.getName());
            User user = userService.getUser(id);

            Friend friend = friendService.getFriend(requester, user);

            switch (action) {
                case "ADD" -> {
                    switch (friend) {
                        case NO_FRIENDS, HE_SUBSCRIBER -> {
                            return friendService.addAndGetFriend(requester, user);
                        }
                    }
                }
                case "REMOVE" -> {
                    switch (friend) {
                        case FRIENDS, I_SUBSCRIBER -> {
                            return friendService.removeAndGetFriend(requester, user);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @GetMapping("/friends")
    public List<Long> friends(Principal principal, @RequestParam(required = false) Long id, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        if (id == null) {
            User user = userService.getUser(principal.getName());
            return friendService.getFriendsIds(user);
        }
        User user = userService.getUser(id);
        return friendService.getFriendsIds(user);
    }

    @GetMapping("/friends/subscribers")
    public List<Long> subscribers(Principal principal, @RequestParam(required = false) Long id, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        if (id == null) {
            User user = userService.getUser(principal.getName());
            return friendService.getSubscribers(user);
        }
        User user = userService.getUser(id);
        return friendService.getSubscribers(user);
    }
}
