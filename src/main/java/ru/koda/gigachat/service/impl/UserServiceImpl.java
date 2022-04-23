package ru.koda.gigachat.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.service.AvatarService;
import ru.koda.gigachat.service.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Value("${ru.koda.giga-chat.default-avatar-id}")
    private String defaultAvatarId;

    private final UserRepository userRepository;

    private final AvatarService avatarService;

    public UserServiceImpl(UserRepository userRepository, AvatarService avatarService) {
        this.userRepository = userRepository;
        this.avatarService = avatarService;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
    }

    @Override
    public User saveUser(final User user, final PasswordEncoder passwordEncoder) {
        user.setId(UUID.randomUUID().toString());
        user.setLogin(user.getLogin().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getAvatar() == null) {
            user.setAvatar(avatarService.getById(defaultAvatarId));
        }
        return userRepository.save(user);
    }
}
