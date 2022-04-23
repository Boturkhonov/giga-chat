package ru.koda.gigachat.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.koda.gigachat.entity.User;

/**
 * Сервис для работы с Пользователями.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
public interface UserService {

    User getByLogin(final String login);

    User saveUser(final User user, final PasswordEncoder passwordEncoder);
}
