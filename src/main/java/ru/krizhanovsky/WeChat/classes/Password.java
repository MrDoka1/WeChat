package ru.krizhanovsky.WeChat.classes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Password {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    public boolean matchesPassword(String password, String codePassword) {
        return encoder.matches(password, codePassword);
    }
}
