package ru.koda.gigachat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.AbstractEntity;
import ru.koda.gigachat.entity.Chat;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.service.ChatService;
import ru.koda.gigachat.service.UserService;

import java.security.Principal;
import java.util.Comparator;
import java.util.Set;

@RestController
@RequestMapping("api/chats")
public class ChatController {

    private final UserService userService;

    private final ChatService chatService;

    public ChatController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping
    public Set<Chat> getChats(final Principal principal) {
        return userService.getByLogin(principal.getName()).getChats();
    }

    @GetMapping("{id}")
    public ResponseEntity<Chat> getChatMessages(final Principal principal, @PathVariable String id) {
        final Chat chat = chatService.getById(id);
        if (chatService.isAccessibleByUser(chat, principal.getName())) {
            chat.getMessages().sort(Comparator.comparing(AbstractEntity::getCreationTime));
            return ResponseEntity.ok(chat);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Chat createChat(final Principal principal, @RequestBody final Chat chat) {
        return chatService.saveChat(chat, principal.getName());
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateChat(final Principal principal, @PathVariable String id) {
        final User user = userService.getByLogin(principal.getName());
        final Chat chat = chatService.getById(id);
        final Boolean updated = chatService.updateChat(chat, user);
        return updated ? ResponseEntity.ok(chat) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteChat(final Principal principal, @PathVariable String id) {
        final User user = userService.getByLogin(principal.getName());
        final Chat chat = chatService.getById(id);
        final Boolean deleted = chatService.deleteChat(chat, user);
        return deleted ? ResponseEntity.ok(chat) : ResponseEntity.badRequest().build();
    }
}
