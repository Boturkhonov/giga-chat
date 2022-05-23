package ru.koda.gigachat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.Avatar;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.service.AvatarService;
import ru.koda.gigachat.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("api/avatars")
public class AvatarController {

    private final AvatarService avatarService;

    private final UserService userService;

    private final UserRepository userRepository;

    public AvatarController(final AvatarService avatarService,
            final UserService userService,
            final UserRepository userRepository) {
        this.avatarService = avatarService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public Avatar getAvatar(@PathVariable final String id) {
        return avatarService.getById(id);
    }

    @PostMapping("/user")
    public Avatar updateUserAvatar(@RequestBody final Avatar avatar, @ApiIgnore final Principal principal) {
        final User user = userService.getByLogin(principal.getName());
        final Avatar savedAvatar = avatarService.saveAvatar(avatar);
        user.setAvatar(savedAvatar);
        userRepository.save(user);
        return savedAvatar;
    }

}
