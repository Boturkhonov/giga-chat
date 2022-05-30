package ru.koda.gigachat.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.ChatType;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.ChatRepository;
import ru.koda.gigachat.service.ChannelService;
import ru.koda.gigachat.service.ChatService;
import ru.koda.gigachat.service.ChatUserService;
import ru.koda.gigachat.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final UserService userService;

    private final ChannelService channelService;

    private final ChatUserService chatUserService;

    public ChatServiceImpl(ChatRepository chatRepository,
            UserService userService,
            ChannelService channelService,
            ChatUserService chatUserService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
        this.channelService = channelService;
        this.chatUserService = chatUserService;
    }

    @Override
    public Boolean isAccessibleByUser(final Chat chat, final String userLogin) {
        final User user = userService.getByLogin(userLogin);
        return isAccessibleByUser(chat, user);
    }

    @Override
    public Boolean isAccessibleByUser(final Chat chat, final User user) {
        return userService.getChats(user).contains(chat);
    }

    @Override
    public Chat getById(final String id) {
        return chatRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Chat.class.getName()));
    }

    @Override
    public Chat saveChat(final Chat chat, final String creatorLogin) {
        final User user = userService.getByLogin(creatorLogin);
        final Set<User> users = getUsers(chat);
        // case PUBLIC:
        // case CHANNEL:
        // final Channel channel = channelService.getById(chat.getChannel().getId());
        // users.addAll(channelService.getSubscribers(channel));
        // break;
        if (chat.getChatType() == ChatType.PRIVATE) {
            final User friend = users.stream()
                    .filter(u -> !u.getId().equals(user.getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Friend not found"));
            if (hasCommonPrivateChat(user, friend)) {
                throw new IllegalArgumentException("Private chat already exists");
            }
            users.add(user);
        }
        chat.setId(UUID.randomUUID().toString());
        final Chat save = chatRepository.save(chat);
        users.forEach(u -> chatUserService.createChatUser(save, u));
        return save;
    }

    @Override
    public Boolean canSendMessage(final Chat chat, final User user) {
        switch (chat.getChatType()) {
            case PRIVATE:
            case PUBLIC:
                return isAccessibleByUser(chat, user);
            case CHANNEL:
                return chat.getChannel().getOwner().getId().equals(user.getId());
        }
        return false;
    }

    @Override
    public Boolean canUpdate(final Chat chat, final User user) {
        switch (chat.getChatType()) {
            case PRIVATE:
                return isAccessibleByUser(chat, user);
            case PUBLIC:
            case CHANNEL:
                return chat.getChannel().getOwner().getId().equals(user.getId());
        }
        return false;
    }

    @Override
    public Boolean deleteChat(final Chat chat, final User user) {
        if (canUpdate(chat, user)) {
            chatRepository.delete(chat);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean updateChat(final Chat chat, final User user) {
        if (canUpdate(chat, user)) {
            chatRepository.save(chat);
            return true;
        }
        return false;
    }

    @Override
    public Set<User> getUsers(final Chat chat) {
        switch (chat.getChatType()) {
            case CHANNEL:
            case PUBLIC:
                return channelService.getSubscribers(chat.getChannel());
            default:
                return chat.getChatUsers()
                        .stream()
                        .map(chatUser -> userService.getById(chatUser.getUser().getId()))
                        .collect(Collectors.toSet());
        }
    }

    @Override
    public Set<Chat> getPrivateChats(final User user) {
        final Set<Chat> chats = userService.getChats(user)
                .stream()
                .filter(chat -> chat.getChatType() == ChatType.PRIVATE)
                .collect(Collectors.toSet());
        chats.forEach(chat -> {
            final Set<User> users = getUsers(chat);
            final Optional<User> optional = users.stream().filter(u -> !u.equals(user)).findFirst();
            if (optional.isPresent()) {
                final User friend = optional.get();
                chat.setName(friend.getName());
                chat.setAvatar(friend.getAvatar());
            }
        });
        return chats;
    }

    private boolean hasCommonPrivateChat(final User user, final User friend) {

        final Set<Chat> privateChats = getPrivateChats(user);
        final Set<User> users = new HashSet<>();
        privateChats.forEach(chat -> users.addAll(getUsers(chat)));

        return users.contains(friend);
    }

}
