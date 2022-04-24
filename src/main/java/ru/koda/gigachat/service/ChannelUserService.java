package ru.koda.gigachat.service;

import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.ChannelUser;
import ru.koda.gigachat.entity.User;

/**
 * Сервис для работы с Пользователями канала.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.24
 */
public interface ChannelUserService {

    ChannelUser createChannelUser(final Channel channel, final User user);
}
