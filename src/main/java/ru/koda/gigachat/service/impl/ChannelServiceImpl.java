package ru.koda.gigachat.service.impl;

import static ru.koda.gigachat.utils.HashGenerator.getHash;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.ChannelRepository;
import ru.koda.gigachat.service.ChannelService;
import ru.koda.gigachat.service.UserService;

import java.util.UUID;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final UserService userService;

    private final ChannelRepository channelRepository;

    public ChannelServiceImpl(UserService userService, ChannelRepository channelRepository) {
        this.userService = userService;
        this.channelRepository = channelRepository;
    }

    @Override
    public Boolean isAccessibleByUser(final Channel channel, final String userLogin) {
        final User user = userService.getByLogin(userLogin);
        return channel.getUsers().contains(user);
    }

    @Override
    public Channel getById(String id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Channel.class.getName()));
    }

    @Override
    public Channel saveChannel(final Channel channel, final String userLogin) {
        final User user = userService.getByLogin(userLogin);
        channel.setId(UUID.randomUUID().toString());
        channel.setOwner(user);
        channel.setLink(getHash(channel.getId()));
        return channelRepository.save(channel);
    }

}
