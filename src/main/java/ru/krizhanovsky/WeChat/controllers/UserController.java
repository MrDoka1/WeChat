package ru.krizhanovsky.WeChat.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.classes.UserOutput;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.servises.FriendService;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FriendService friendService;

    @GetMapping("/user")
    public HashMap<Long, UserOutput> user(Principal principal, @RequestParam(value = "id") Long id, HttpServletResponse httpServletResponse) {
        HashMap<Long, UserOutput> hashMap = new HashMap<>();

        User requester = userService.getUser(principal.getName());
        User user = userService.getUser(id);

        if (user != null) {
            hashMap.put(id, new UserOutput(user, friendService.getFriend(requester, user),
                    friendService.getFriendsIds(user), friendService.getSubscribers(user)));
        } else {
            hashMap.put(id, null);
        }

        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        return hashMap;
    }

    @GetMapping("/users")
    public HashMap<Long, UserOutput> users(Principal principal, @RequestParam(value = "ids") List<Long> idList, HttpServletResponse httpServletResponse) {
        HashMap<Long, UserOutput> hashMap = new HashMap<>();
        User requester = userService.getUser(principal.getName());

        for (long id : idList) {
            User user = userService.getUser(id);

            if (user != null) {
                hashMap.put(id, new UserOutput(user, friendService.getFriend(requester, user),
                        friendService.getFriendsIds(user), friendService.getSubscribers(user)));
            } else {
                hashMap.put(id, null);
            }
        }

        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        return hashMap;
    }

    @GetMapping("/users/online")
    public HashMap<Long, LocalDateTime> usersOnline(HttpServletResponse httpServletResponse,@RequestParam List<Long> ids) {
        List<LocalDateTime> listOnline = userService.getUsersOnline(ids);
        HashMap<Long, LocalDateTime> hashMap = new HashMap<>();
        for (int i=0; i<ids.size(); i++) {
            hashMap.put(ids.get(i), listOnline.get(i));
        }

        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        return hashMap;
    }
}
