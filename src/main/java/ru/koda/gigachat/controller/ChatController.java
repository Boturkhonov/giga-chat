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
import ru.koda.gigachat.entity.Message;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.service.ChatService;
import ru.koda.gigachat.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/chats")
public class ChatController {

    private final UserService userService;

    private final ChatService chatService;

    public ChatController(final UserService userService, final ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping
    public Set<Chat> getUserChats(@ApiIgnore final Principal principal) {
        return userService.getChats(userService.getByLogin(principal.getName()));
    }

    @GetMapping("/private")
    public Set<Chat> getUserPrivateChats(@ApiIgnore final Principal principal) {
        return chatService.getPrivateChats(userService.getByLogin(principal.getName()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Chat> getChat(@PathVariable final String id, @ApiIgnore final Principal principal) {
        final Chat chat = chatService.getById(id);
        return chatService.isAccessibleByUser(chat, principal.getName())
                ? ResponseEntity.ok(chat)
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}/messages")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable final String id,
            @ApiIgnore final Principal principal) {
        final Chat chat = chatService.getById(id);
        if (chatService.isAccessibleByUser(chat, principal.getName())) {
            chat.getMessages().sort(Comparator.comparing(AbstractEntity::getCreationTime));
            return ResponseEntity.ok(chat.getMessages());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}/users")
    public ResponseEntity<Set<User>> getChatUsers(@PathVariable final String id, @ApiIgnore final Principal principal) {
        final Chat chat = chatService.getById(id);
        return chatService.isAccessibleByUser(chat, principal.getName())
                ? ResponseEntity.ok(chatService.getUsers(chat))
                : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public Chat createChat(@RequestBody final Chat chat, @ApiIgnore final Principal principal) {
        return chatService.saveChat(chat, principal.getName());
    }

    @PatchMapping("{id}")
    public ResponseEntity<Chat> updateChat(@PathVariable final String id, @ApiIgnore final Principal principal) {
        final User user = userService.getByLogin(principal.getName());
        final Chat chat = chatService.getById(id);
        final Boolean updated = chatService.updateChat(chat, user);
        return updated ? ResponseEntity.ok(chat) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Chat> deleteChat(@PathVariable String id, @ApiIgnore final Principal principal) {
        final User user = userService.getByLogin(principal.getName());
        final Chat chat = chatService.getById(id);
        final Boolean deleted = chatService.deleteChat(chat, user);
        return deleted ? ResponseEntity.ok(chat) : ResponseEntity.badRequest().build();
    }
}
