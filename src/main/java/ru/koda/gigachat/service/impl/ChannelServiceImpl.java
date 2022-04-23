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

/**
 * todo kboturkhonov
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    private final UserService userService;

    private final ChannelRepository channelRepository;

    public ChannelServiceImpl(UserService userService, ChannelRepository channelRepository) {
        this.userService = userService;
        this.channelRepository = channelRepository;
    }

    @Override
    public Boolean isAccessibleByUser(final String channelId, final String userLogin) {
        final User user = userService.getByLogin(userLogin);
        return getById(channelId).getUsers().contains(user);
    }

    @Override
    public Channel getById(String id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Channel.class.getName()));
    }

    @Override
    public Channel saveChannel(final Channel channel, final String userLogin) {
        final Channel newChannel = new Channel();
        final User user = userService.getByLogin(userLogin);
        newChannel.setId(UUID.randomUUID().toString());
        newChannel.setName(channel.getName());
        newChannel.setAvatar(channel.getAvatar());
        newChannel.setOwner(user);
        newChannel.setAbout(channel.getAbout());
        newChannel.setLink(getHash(channel.getId()));

        return channelRepository.save(channel);
    }

}
