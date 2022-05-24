package ru.koda.gigachat.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.koda.gigachat.entity.Avatar;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.service.AvatarService;
import ru.koda.gigachat.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("api/avatars")
public class AvatarController {

    private final AvatarService avatarService;

    private final UserService userService;

    public AvatarController(final AvatarService avatarService, final UserService userService) {
        this.avatarService = avatarService;
        this.userService = userService;
    }

    @GetMapping("{id}")
    public Avatar getAvatar(@PathVariable final String id) {
        return avatarService.getById(id);
    }

    @PostMapping(value = "/user", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Avatar updateUserAvatar(@RequestPart("image") final MultipartFile image,
            @ApiIgnore final Principal principal) throws IOException {
        final User user = userService.getByLogin(principal.getName());
        return avatarService.saveUserAvatar(user, image);
    }

}
