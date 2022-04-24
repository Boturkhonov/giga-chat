package ru.koda.gigachat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.Message;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.service.ChatService;
import ru.koda.gigachat.service.MessageService;
import ru.koda.gigachat.service.UserService;
import ru.koda.gigachat.utils.JwtUtil;

@RestController
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChatService chatService;

    private final UserService userService;

    private final MessageService messageService;

    public WebSocketController(final SimpMessagingTemplate simpMessagingTemplate,
            final ChatService chatService,
            final UserService userService,
            final MessageService messageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @MessageMapping("/chat/{id}")
    public void messageHandler(final Message message, @DestinationVariable final String id) {
        final Chat chat = chatService.getById(id);
        final User user = userService.getByLogin(JwtUtil.extractUsername(message.getSender().getToken()));
        message.setSender(user);
        message.setChat(chat);
        final Message saved = messageService.saveMessage(message);
        if (saved != null) {
            simpMessagingTemplate.convertAndSend("/topic/chat/" + id, saved);
        } else {
            throw new RuntimeException("User cannot send message to the chat");
        }
    }

}
