package ru.koda.gigachat.service.impl;

import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Message;
import ru.koda.gigachat.repo.MessageRepository;
import ru.koda.gigachat.service.ChatService;
import ru.koda.gigachat.service.MessageService;
import ru.koda.gigachat.service.UserService;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final ChatService chatService;

    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, ChatService chatService, UserService userService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
        this.userService = userService;
    }

    @Override
    public Message saveMessage(final Message message, final String senderLogin) {

        if (chatService.canSendMessage(message.getChat(), userService.getByLogin(senderLogin))) {
            return messageRepository.save(message);
        }
        return null;
    }
}
