package ru.koda.gigachat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.ChannelRepository;
import ru.koda.gigachat.repo.ChannelUserRepository;
import ru.koda.gigachat.service.ChannelService;
import ru.koda.gigachat.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("api/channels")
public class ChannelController {

    /**
     * Сервис для работы с Пользователями.
     */
    private final UserService userService;

    /**
     * Сервис для работы с Каналами.
     */
    private final ChannelService channelService;

    /**
     * Репозиторий для работы с Пользователями с Канала.
     */
    private final ChannelUserRepository channelUserRepository;

    /**
     * Репозиторий для работы с Каналами.
     */
    private final ChannelRepository channelRepository;

    public ChannelController(final UserService userService,
            final ChannelService channelService,
            final ChannelUserRepository channelUserRepository,
            final ChannelRepository channelRepository) {
        this.userService = userService;
        this.channelService = channelService;
        this.channelUserRepository = channelUserRepository;
        this.channelRepository = channelRepository;
    }

    @GetMapping
    public Set<Channel> getUserChannels(@ApiIgnore final Principal principal) {
        return userService.getChannels(userService.getByLogin(principal.getName()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Channel> getChannel(@PathVariable final String id, @ApiIgnore final Principal principal) {
        final Channel channel = channelService.getById(id);
        return channelService.isAccessibleByUser(channel, principal.getName())
                ? ResponseEntity.ok(channel)
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}/users")
    public ResponseEntity<Set<User>> getChannelUsers(@PathVariable final String id,
            @ApiIgnore final Principal principal) {
        final Channel channel = channelService.getById(id);
        final Set<User> subscribers = channelService.getSubscribers(channel);
        return channelService.isAccessibleByUser(channel, principal.getName())
                ? ResponseEntity.ok(subscribers)
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}/chats")
    public ResponseEntity<Set<Chat>> getChannelChats(@PathVariable final String id,
            @ApiIgnore final Principal principal) {
        final Channel channel = channelService.getById(id);
        return channelService.isAccessibleByUser(channel, principal.getName())
                ? ResponseEntity.ok(channel.getChats())
                : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Channel createChannel(@RequestBody final Channel channel, @ApiIgnore final Principal principal) {
        return channelService.saveChannel(channel, principal.getName());
    }

    @GetMapping("{id}/leave")
    public ResponseEntity<HttpStatus> leaveChannel(@PathVariable final String id,
            @ApiIgnore final Principal principal) {
        final Channel channel = channelService.getById(id);
        final User user = userService.getByLogin(principal.getName());
        if (!channel.getOwner().getId().equals(user.getId())) {
            channelUserRepository.deleteByChannelAndUser(channel, user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping
    public ResponseEntity<Channel> updateChannel(@RequestBody final Channel channel,
            @ApiIgnore final Principal principal) {
        final User user = userService.getByLogin(principal.getName());
        final Channel old = channelService.getById(channel.getId());
        if (old.getOwner().equals(user)) {
            channelRepository.save(channel);
            return ResponseEntity.ok(channel);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable final String id, @ApiIgnore final Principal principal) {
        final User user = userService.getByLogin(principal.getName());
        final Channel channel = channelService.getById(id);

        if (channel.getOwner().equals(user)) {
            channelRepository.delete(channel);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
