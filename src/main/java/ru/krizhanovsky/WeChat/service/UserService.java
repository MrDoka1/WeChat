package ru.krizhanovsky.WeChat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsersAuthRepository usersAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String phonenumber) throws UsernameNotFoundException {
        return (UserDetails) usersAuthRepository.findByPhonenumber(phonenumber);
    }
}
