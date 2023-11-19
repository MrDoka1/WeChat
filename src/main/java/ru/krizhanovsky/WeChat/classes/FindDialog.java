package ru.krizhanovsky.WeChat.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.DialogRepository;

import java.util.List;

@Service
public class FindDialog {
    @Autowired
    private DialogRepository dialogRepository;

    public Dialog getDialog(long id) {
        return dialogRepository.getReferenceById(id);
    }

    public long getDialogId(User user1, User user2) {
        Dialog dialog = dialogRepository.findByUser1AndUser2(user1, user2);
        if (dialog == null) {
            dialog = dialogRepository.findByUser1AndUser2(user2, user1);
            if (dialog == null) {
                dialog = new Dialog(user1, user2);
                dialogRepository.save(dialog);
            }
        }
        return dialog.getId();
    }

    public List<Dialog> getAllDialogs(User user) {
        return dialogRepository.findByUser1OrUser2(user, user);
    }
}
