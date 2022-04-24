package ru.koda.gigachat.service;

import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.ChannelUser;
import ru.koda.gigachat.entity.User;

public interface ChannelUserService {

    ChannelUser createChannelUser(final Channel channel, final User user);
}
