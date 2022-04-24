package ru.koda.gigachat.service.impl;

import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.ChatUser;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.ChatUserRepository;
import ru.koda.gigachat.service.ChatUserService;

import java.util.UUID;

@Service
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;

    public ChatUserServiceImpl(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @Override
    public ChatUser createChatUser(final Chat chat, final User user) {
        final ChatUser chatUser = new ChatUser();
        chatUser.setId(UUID.randomUUID().toString());
        chatUser.setChat(chat);
        chatUser.setUser(user);
        return chatUserRepository.save(chatUser);
    }
}
