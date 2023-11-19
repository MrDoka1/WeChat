package ru.krizhanovsky.WeChat.controllers;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krizhanovsky.WeChat.classes.ChatMessageKafka;
import ru.krizhanovsky.WeChat.classes.FindDialog;
import ru.krizhanovsky.WeChat.classes.FindMessage;
import ru.krizhanovsky.WeChat.classes.MessageOutput;
import ru.krizhanovsky.WeChat.config.KafkaConstants;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.Message;
import ru.krizhanovsky.WeChat.servises.ChatService;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class MessagesController {
    private final FindDialog findDialog;
    private final ChatService chatService;
    private final UserService userService;
    private final FindMessage findMessage;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    @GetMapping("/messages/last")
    public HashMap<String, MessageOutput> lastMessages(Principal principal, @RequestParam(value = "ids") String ids, HttpServletResponse httpServletResponse) {
        //User user = userService.getUser(principal.getName());
        HashMap<String, MessageOutput> hashMap = new HashMap<>();
        String[] idList = ids.split(",");

        for (String id : idList) {
            if (id.charAt(0) == 'c') {
                long chatId = Long.parseLong(id.substring(1));
                Chat chat = chatService.getChat(chatId);
                if (chat != null) {
                    Message message = findMessage.getLastMessage(chat);
                    if (message != null) {
                        hashMap.put(id, new MessageOutput(message));
                    } else {
                        hashMap.put(id, null);
                    }
                }
            } else {
                Dialog dialog = findDialog.getDialog(Long.parseLong(id));
                if (dialog != null) {
                    Message message = findMessage.getLastMessage(dialog);
                    if (message != null) {
                        hashMap.put(id, new MessageOutput(message));
                    } else {
                        hashMap.put(id, null);
                    }

                }
            }
        }

        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        return hashMap;
    }

    @GetMapping("/messages")
    public List<MessageOutput> messages(Principal principal, HttpServletResponse httpServletResponse,
                                                 @RequestParam String id , @RequestParam Long messageId) {
        List<Message> messages = findMessage.get10Messages(id, messageId);
        List<MessageOutput> messageOutputs = new ArrayList<>();

        for (Message message: messages) {
            messageOutputs.add(new MessageOutput(message));
        }
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        return messageOutputs;
    }

    @PostMapping(value = "/message")
    public ResponseEntity<String> addMessage(Principal principal, @RequestParam String chat, @RequestParam String text) {
        ChatMessageKafka messageKafka = new ChatMessageKafka(userService.getUser(principal.getName()).getId(),
                chat, text, LocalDateTime.now());
        try {
            //Sending the message to kafka topic queue
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, gson.toJson(messageKafka)).get();
            return new ResponseEntity<>("the message was successfully delivered", HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

/*    @GetMapping("/messages/{chatId}")
    public List<User> messages(Principal principal, @PathVariable(value = "chatId") String chatId, @RequestParam(value = "id") String id) {
        userService.getUser(principal.getName());
        Long chat = findDialog.getDialogId(Long.parseLong(chatId), Long.parseLong(id));

        List<User> users = new java.util.ArrayList<>(userRepository.findById(Long.parseLong(chatId)).stream().toList());
        users.add(userRepository.findById(Long.parseLong(id)).get());

        return users;
    }*/
}
