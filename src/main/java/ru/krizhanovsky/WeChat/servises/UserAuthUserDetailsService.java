package ru.krizhanovsky.WeChat.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.krizhanovsky.WeChat.models.UserAuth;
import ru.krizhanovsky.WeChat.repos.UsersAuthRepository;

@Service
public class UserAuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersAuthRepository usersAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserAuth customer = usersAuthRepository.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(customer.getEmail()).password(customer.getPassword()).authorities("USER").build();
    }
}
