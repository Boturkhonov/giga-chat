package ru.koda.gigachat.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.ChannelUser;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.ChatUser;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.service.AvatarService;
import ru.koda.gigachat.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("${ru.koda.giga-chat.default-user-avatar-id}")
    private String defaultUserAvatarId;

    private final UserRepository userRepository;

    private final AvatarService avatarService;

    public UserServiceImpl(final UserRepository userRepository, final AvatarService avatarService) {
        this.userRepository = userRepository;
        this.avatarService = avatarService;
    }

    @Override
    public User getById(final String id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id));
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
        user.setAvatar(avatarService.getById(defaultUserAvatarId));

        return userRepository.save(user);
    }

    @Override
    public Set<Channel> getChannels(final User user) {
        return user.getChannelUsers().stream().map(ChannelUser::getChannel).collect(Collectors.toSet());
    }

    @Override
    public Set<Chat> getChats(final User user) {
        final Set<ChatUser> chatUsers = user.getChatUsers();
        final Set<Chat> chats = new HashSet<>();
        getChannels(user).forEach(channel -> chats.addAll(channel.getChats()));
        chats.addAll(chatUsers.stream().map(ChatUser::getChat).collect(Collectors.toSet()));
        return chats;
    }

}
