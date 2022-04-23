package ru.koda.gigachat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.service.ChannelService;
import ru.koda.gigachat.service.UserService;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("api/channels")
public class ChannelController {

    private final UserService userService;

    private final ChannelService channelService;

    public ChannelController(final UserService userService, final ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @GetMapping
    public Set<Channel> getUserChannels(final Principal principal) {
        return userService.getByLogin(principal.getName()).getChannels();
    }

    @GetMapping("{id}")
    public Channel getChanel(final Principal principal, @PathVariable String id) {
        return channelService.isAccessibleByUser(id, principal.getName()) ? channelService.getById(id) : null;
    }

    @PostMapping
    public Channel createChannel(final Principal principal, @RequestBody final Channel channel) {
        return channelService.saveChannel(channel, principal.getName());
    }

}
