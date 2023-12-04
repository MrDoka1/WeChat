package ru.krizhanovsky.WeChat.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.classes.*;
import ru.krizhanovsky.WeChat.dto.ChatUsersDTOResponse;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.ChatUser;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.servises.ChatService;
import ru.krizhanovsky.WeChat.servises.ChatUserService;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final UserService userService;
    private final FindDialog findDialog;
    private final ChatService chatService;
    private final ChatUserService chatUserService;
    private final CreatorChat creatorChat;

    @GetMapping("/chats")
    public List<ChatOutput> chats(Principal principal, HttpServletResponse httpServletResponse) {
        User user = userService.getUser(principal.getName());
        List<Dialog> dialogList = findDialog.getAllDialogs(user);
        List<ChatUser> chatUserList = chatUserService.getChatUsers(user);

        List<ChatOutput> chatOutputList = new ArrayList<>();

        for (Dialog dialog : dialogList) {
            chatOutputList.add(new ChatOutput(dialog, user));
        }
        for (ChatUser chatUser : chatUserList) {
            long members = chatUserService.getCountMembers(chatUser.getChat());
            chatOutputList.add(new ChatOutput(chatUser , members));
        }


        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        return chatOutputList;
    }

    @PostMapping("/chat")
    public ChatOutput chat(Principal principal, HttpServletResponse httpServletResponse, @RequestParam(value = "ids") List<Long> idList,
                                          @RequestParam(value = "name") String name, @RequestParam(value = "isPrivate") boolean isPrivate,
                                          @RequestParam("url") String urlPhoto) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        User user = userService.getUser(principal.getName());

        if (name.replaceAll(" ", "").equals("")) {
            httpServletResponse.setStatus(400);
            return null;
        }

        Chat chat = new Chat(name, urlPhoto, user, LocalDateTime.now(), isPrivate);
        return creatorChat.create(chat, idList);
    }

    @GetMapping("/chat/users")
    public Object chatUsers(Principal principal, HttpServletResponse httpServletResponse, @RequestParam Long chatId) {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        User user = userService.getUser(principal.getName());
        Chat chat = chatService.getChat(chatId);
        // Если чат не существует
        if (chat == null) {
            httpServletResponse.setStatus(400);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("message", "chatId is invalid");
            return hashMap;
        }
        // Если пользователь не состоит в этом чате
        if (!chatUserService.userConsistsOfChat(user, chat)) {
            httpServletResponse.setStatus(403);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("message", "user not consists in chat");
            return hashMap;
        }
        List<ChatUser> list = chatUserService.getChatUsers(chat);
        List<ChatUsersDTOResponse> responseList = new ArrayList<>();
        for (ChatUser chatUser : list) {
            responseList.add(new ChatUsersDTOResponse(chatUser.getUser().getId(), chatUser.getRole()));
        }
        // return List<ChatUsersDTOResponse>
        return responseList;
    }

}