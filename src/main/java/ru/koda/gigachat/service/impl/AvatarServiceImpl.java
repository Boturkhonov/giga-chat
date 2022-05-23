package ru.koda.gigachat.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import ru.koda.gigachat.entity.Avatar;
import ru.koda.gigachat.repo.AvatarRepository;
import ru.koda.gigachat.service.AvatarService;

import java.util.UUID;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(final AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Override
    public Avatar getById(final String id) {
        return avatarRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Avatar.class.getName()));
    }

    @Override
    public Avatar saveAvatar(final Avatar avatar) {
        avatar.setId(UUID.randomUUID().toString());
        return avatarRepository.save(avatar);
    }
}
