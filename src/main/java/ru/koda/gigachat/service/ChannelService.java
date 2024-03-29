package ru.koda.gigachat.service;

import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.User;

import java.util.Set;

/**
 * Сервис для работы с Каналами.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
public interface ChannelService {

    Boolean isAccessibleByUser(final Channel channel, final String userLogin);

    Channel getById(final String id);

    Channel saveChannel(final Channel channel, final String userLogin);

    Set<User> getSubscribers(final Channel channel);
}
