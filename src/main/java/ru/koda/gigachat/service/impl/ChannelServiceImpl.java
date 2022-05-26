package ru.koda.gigachat.service.impl;

import static ru.koda.gigachat.utils.HashGenerator.getHash;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.ChannelUser;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.ChannelRepository;
import ru.koda.gigachat.service.ChannelService;
import ru.koda.gigachat.service.ChannelUserService;
import ru.koda.gigachat.service.UserService;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final UserService userService;

    private final ChannelRepository channelRepository;

    private final ChannelUserService channelUserService;

    public ChannelServiceImpl(UserService userService,
            ChannelRepository channelRepository,
            ChannelUserService channelUserService) {
        this.userService = userService;
        this.channelRepository = channelRepository;
        this.channelUserService = channelUserService;
    }

    @Override
    public Boolean isAccessibleByUser(final Channel channel, final String userLogin) {
        final User user = userService.getByLogin(userLogin);
        return getSubscribers(channel).contains(user);
    }

    @Override
    public Channel getById(final String id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Channel.class.getName()));
    }

    @Override
    public Channel saveChannel(final Channel channel, final String userLogin) {
        final User user = userService.getByLogin(userLogin);
        channel.setId(UUID.randomUUID().toString());
        channel.setOwner(user);
        channel.setLink(getHash(channel.getId()));
        final Channel save = channelRepository.save(channel);
        channelUserService.createChannelUser(save, user);
        return save;
    }

    @Override
    public Set<User> getSubscribers(final Channel channel) {
        return channel == null
                ? Collections.emptySet()
                : channel.getChannelUsers().stream().map(ChannelUser::getUser).collect(Collectors.toSet());
    }

}
