package ru.krizhanovsky.WeChat.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.Chat;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.Message;
import ru.krizhanovsky.WeChat.repos.MessageRepository;

import java.util.List;

@Service
public class FindMessage {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    FindChat findChat;
    @Autowired
    FindDialog findDialog;

    public Message getLastMessage(Chat chat) {
        return messageRepository.findLastByChat(chat);
    }

    public Message getLastMessage(Dialog dialog) {
        return messageRepository.findLastByDialog(dialog);
    }

    public List<Message> getMessages(Chat chat) {
        return messageRepository.findByChat(chat);
    }

    public List<Message> getMessages(Dialog dialog) {
        return messageRepository.findByDialog(dialog);
    }

    public List<Message> get10Messages(String id, long messageId) {
        if (id.charAt(0) == 'c') {
            Chat chat = findChat.getChat(Long.parseLong(id.substring(1)));
            if (messageId == 0) {
                return messageRepository.findFirst10ByChatOrderByIdDesc(chat);
            }
            return messageRepository.findFirst10ByIdBeforeAndChatOrderByIdDesc(messageId, chat);
        } else {
            Dialog dialog = findDialog.getDialog(Long.parseLong(id));
            if (messageId == 0) {
                return messageRepository.findFirst10ByIdBeforeAndDialogOrderByIdDesc(messageId, dialog);
            }
            return messageRepository.findFirst10ByDialogOrderByIdDesc(dialog);
        }
    }
}
