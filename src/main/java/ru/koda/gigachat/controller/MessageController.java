package ru.koda.gigachat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.Message;
import ru.koda.gigachat.service.MessageService;

import java.security.Principal;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public ResponseEntity<Message> sendMessage(@RequestBody final Message message, final Principal principal) {
        final Message savedMessage = messageService.saveMessage(message, principal.getName());
        return savedMessage == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(savedMessage);
    }

}
