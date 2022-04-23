package ru.koda.gigachat.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.ChatRepository;
import ru.koda.gigachat.service.ChatService;
import ru.koda.gigachat.service.UserService;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final UserService userService;

    public ChatServiceImpl(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Override
    public Boolean isAccessibleByUser(final Chat chat, final String userLogin) {
        final User user = userService.getByLogin(userLogin);
        return isAccessibleByUser(chat, user);
    }

    @Override
    public Boolean isAccessibleByUser(final Chat chat, final User user) {
        return user.getChats().contains(chat);
    }

    @Override
    public Chat getById(final String id) {
        return chatRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Chat.class.getName()));
    }

    @Override
    public Chat saveChat(final Chat chat, final String creatorLogin) {
        final User user = userService.getByLogin(creatorLogin);
        switch (chat.getChatType()) {
            case PUBLIC:
            case CHANNEL:
                chat.getUsers().addAll(chat.getChannel().getUsers());
                break;
        }
        chat.getUsers().add(user);
        return chatRepository.save(chat);
    }

    @Override
    public Boolean canSendMessage(final Chat chat, final User user) {
        switch (chat.getChatType()) {
            case PRIVATE:
            case PUBLIC:
                return isAccessibleByUser(chat, user);
            case CHANNEL:
                return chat.getChannel().getOwner().equals(user);
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
                return chat.getChannel().getOwner().equals(user);
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

}
