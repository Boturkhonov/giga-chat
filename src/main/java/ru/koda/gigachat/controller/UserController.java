package ru.koda.gigachat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserController(final UserService userService,
            final UserRepository userRepository,
            final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable final String id) {
        final Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<User> getUserByLogin(@RequestParam("login") final String login) {
        return ResponseEntity.ok(userService.getByLogin(login));
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid final User user, @ApiIgnore final Principal principal) {
        final User updater = userService.getByLogin(principal.getName());

        return user.getId().equals(updater.getId())
                ? ResponseEntity.ok(userService.updateUser(user, updater, passwordEncoder))
                : ResponseEntity.badRequest().build();
    }
}
