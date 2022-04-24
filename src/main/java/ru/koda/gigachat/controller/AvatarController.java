package ru.koda.gigachat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.Avatar;

@RestController
@RequestMapping("api/avatars")
public class AvatarController {

    public ResponseEntity<Avatar> uploadAvatar() {
        return null;
    }

}
