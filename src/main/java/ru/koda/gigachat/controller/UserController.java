package ru.koda.gigachat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.service.UserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        final Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody final User user, final Principal principal) {
        final User updater = userService.getByLogin(principal.getName());

        return user.getId().equals(updater.getId())
                ? ResponseEntity.ok(userRepository.save(user))
                : ResponseEntity.badRequest().build();
    }
}
