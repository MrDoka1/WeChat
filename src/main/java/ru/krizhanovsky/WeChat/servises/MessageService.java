package ru.krizhanovsky.WeChat.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.classes.ChatMessageKafka;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.Message;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final UserService userService;
    private final DialogService dialogService;

    public Message getLastMessage(Chat chat) {
        return messageRepository.findLastByChat(chat);
    }
    public Message getLastMessage(Dialog dialog) {
        return messageRepository.findLastByDialog(dialog);
    }

    public Message addMessage(ChatMessageKafka chatMessageKafka) {
        User sender = userService.getUser(chatMessageKafka.getSenderId());
        if (chatMessageKafka.getChat().charAt(0) == 'c') {
            long chatId = Long.parseLong(chatMessageKafka.getChat().substring(1));
            Chat chat = chatService.getChat(chatId);
            Message message = new Message(chat, sender, chatMessageKafka.getText(), chatMessageKafka.getTimeStamp());
            messageRepository.save(message);
            return message;
        } else {
            User user = userService.getUser(Long.parseLong(chatMessageKafka.getChat()));
            Dialog dialog = dialogService.getDialog(sender, user);
            Message message = new Message(dialog, sender, chatMessageKafka.getText(), chatMessageKafka.getTimeStamp());
            messageRepository.save(message);
            return message;
        }
    }

}
