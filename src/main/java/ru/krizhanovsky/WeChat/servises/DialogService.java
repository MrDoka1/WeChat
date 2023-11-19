package ru.krizhanovsky.WeChat.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.Dialog;
import ru.krizhanovsky.WeChat.models.User;
import ru.krizhanovsky.WeChat.repos.DialogRepository;

@Service
@RequiredArgsConstructor
public class DialogService {
    private final DialogRepository dialogRepository;

    public Dialog getDialog(long id)  {
        return dialogRepository.findById(id).orElseThrow();
    }

    public Dialog getDialog(User user1, User user2) {
        Dialog dialog = dialogRepository.findByUser1AndUser2(user1, user2);
        if (dialog == null) {
            return dialogRepository.findByUser1AndUser2(user2, user1);
        }
        return dialog;
    }

}
