//package ru.krizhanovsky.WeChat.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.krizhanovsky.WeChat.classes.ChatOutput;
//import ru.krizhanovsky.WeChat.classes.FindDialog;
//import ru.krizhanovsky.WeChat.classes.userService;
//import ru.krizhanovsky.WeChat.models.Dialog;
//import ru.krizhanovsky.WeChat.models.Message;
//import ru.krizhanovsky.WeChat.models.User;
//import ru.krizhanovsky.WeChat.repos.DialogRepository;
//import ru.krizhanovsky.WeChat.repos.MessageRepository;
//import ru.krizhanovsky.WeChat.repos.UserAuthRepository;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class ImControllers {
//
//    @Autowired
//    UserAuthRepository userAuthRepository;
//    @Autowired
//    UserService userService;
//    @Autowired
//    FindDialog findDialog;
//    @Autowired
//    DialogRepository dialogRepository;
//    @Autowired
//    MessageRepository messageRepository;
//
//    @GetMapping("/im")
//    public String im(Model model, Principal principal, @RequestParam(value = "sel", required = false) String sel) {
//        // *** Конструкция для всех страниц ***
//        User user = userService.getUser(principal.getName());
//        model.addAttribute("userId", user.getId());
//        // *** *** *** *** *** *** *** *** ***
//
//        List<Dialog> dialogList = dialogRepository.findByUser1OrUser2(user, user);
//
//        // ** Вывод чатиков **
//        if (!dialogList.isEmpty()) {
//            List<ChatOutput> chatOutputList = new ArrayList<>();
//            for (Dialog dialog : dialogList) {
//                List<Message> list = messageRepository.findByChatId(dialog.getId());
//                //chatOutputList.add(new ChatOutput(dialog, user, list, sel != null ? Long.parseLong(sel) : 0));
//            }
//
//            // ** Лист для обновления статуса **
//            List<Long> idList = new ArrayList<>();
//            for (ChatOutput chat: chatOutputList) {
//                idList.add(chat.getId());
//            }
//            model.addAttribute("idList", idList);
//            // ** ** ** ** ** ** ** ** ** ** ** **
//
//            model.addAttribute("chats", chatOutputList);
//        }
//
//        if (sel != null && !sel.isEmpty()) {
//            // ** Проверка - является ли чат беседой **
//            if (sel.charAt(0) == 'c') {
//                sel = sel.substring(1);
//                System.out.println(sel);
//            } else {
//                // ** Получаем собеседника **
//                User userInterlocutorOptional = userService.getUser(Long.parseLong(sel));
//                model.addAttribute("interlocutor", userInterlocutorOptional);
//
//                long id = findDialog.getDialogId(user, userInterlocutorOptional);
//
//                // ** Вывод сообщений в чат **
//                List<Message> messages = messageRepository.findByChatId(id);
//                model.addAttribute("messages", messages);
//
//                if (id != 0) {
//                    model.addAttribute("id", id);
//                    return "imSel";
//                }
//            }
//        }
//
//        return "im";
//    }
//
//}
