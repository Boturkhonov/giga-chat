package ru.koda.gigachat.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.service.UserService;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userService.getByLogin(username);
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                Collections.emptyList());
    }
}