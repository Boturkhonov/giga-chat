package ru.koda.gigachat.service.impl;

import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.ChannelUser;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.ChannelUserRepository;
import ru.koda.gigachat.service.ChannelUserService;

import java.util.UUID;

@Service
public class ChannelUserServiceImpl implements ChannelUserService {

    private final ChannelUserRepository channelUserRepository;

    public ChannelUserServiceImpl(ChannelUserRepository channelUserRepository) {
        this.channelUserRepository = channelUserRepository;
    }

    @Override
    public ChannelUser createChannelUser(final Channel channel, final User user) {
        final ChannelUser channelUser = new ChannelUser();
        channelUser.setId(UUID.randomUUID().toString());
        channelUser.setChannel(channel);
        channelUser.setUser(user);
        return channelUserRepository.save(channelUser);
    }
}
