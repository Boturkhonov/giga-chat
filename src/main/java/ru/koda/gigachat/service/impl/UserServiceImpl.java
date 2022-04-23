package ru.koda.gigachat.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.AvatarRepository;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Value("${ru.koda.giga-chat.default-avatar-id}")
    private String defaultAvatarId;

    private final UserRepository userRepository;

    private final AvatarRepository avatarRepository;

    public UserServiceImpl(UserRepository userRepository, AvatarRepository avatarRepository) {
        this.userRepository = userRepository;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
    }

    @Override
    public User saveUser(final User user, final PasswordEncoder passwordEncoder) {
        user.setLogin(user.getLogin().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getAvatar() == null) {
            user.setAvatar(avatarRepository.getById(defaultAvatarId));
        }
        return userRepository.save(user);
    }
}
