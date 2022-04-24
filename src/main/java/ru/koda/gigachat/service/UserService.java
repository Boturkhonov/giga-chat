package ru.koda.gigachat.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.User;

import java.util.Set;

/**
 * Сервис для работы с Пользователями.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
public interface UserService {

    User getById(final String id);

    User getByLogin(final String login);

    User saveUser(final User user, final PasswordEncoder passwordEncoder);

    Set<Channel> getChannels(final User user);

    Set<Chat> getChats(final User user);

}
