package ru.koda.gigachat.service;

import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.User;

/**
 * Сервис для работы с Чатами.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
public interface ChatService {

    Boolean isAccessibleByUser(final Chat chat, final String userLogin);

    Boolean isAccessibleByUser(final Chat chat, final User user);

    Chat getById(final String id);

    Chat saveChat(final Chat chat, final String creatorLogin);

    Boolean canSendMessage(final Chat chat, final User user);

    Boolean deleteChat(final Chat chat, final User user);

    Boolean canUpdate(final Chat chat, final User user);

    Boolean updateChat(final Chat chat, final User user);
}
