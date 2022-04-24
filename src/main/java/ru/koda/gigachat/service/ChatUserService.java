package ru.koda.gigachat.service;

import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.ChatUser;
import ru.koda.gigachat.entity.User;

/**
 * Сервис для работы с Пользователями чата.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.24
 */
public interface ChatUserService {

    ChatUser createChatUser(final Chat chat, final User user);

}
