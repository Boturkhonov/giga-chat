package ru.koda.gigachat.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.koda.gigachat.entity.Avatar;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.AvatarRepository;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.service.AvatarService;
import ru.koda.gigachat.utils.FileIOHelper;

import java.io.IOException;
import java.util.UUID;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Value("${ru.koda.giga-chat.avatar-folder}")
    private String avatarFolder;

    @Value("${ru.koda.giga-chat.default-user-avatar-id}")
    private String defaultUserAvatarId;

    private final AvatarRepository avatarRepository;

    private final UserRepository userRepository;

    public AvatarServiceImpl(final AvatarRepository avatarRepository, UserRepository userRepository) {
        this.avatarRepository = avatarRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Avatar getById(final String id) {
        return avatarRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Avatar.class.getName()));
    }

    @Override
    public Avatar saveUserAvatar(final User user, final MultipartFile image) throws IOException {
        final String path = FileIOHelper.saveUploadedFile(image, avatarFolder);
        final Avatar savedAvatar = createAndSaveAvatar(path);
        if (user.getAvatar() != null && !user.getAvatar().getId().equals(defaultUserAvatarId)) {
            FileIOHelper.deleteFile(user.getAvatar().getPath(), avatarFolder);
        }
        user.setAvatar(savedAvatar);
        userRepository.save(user);
        return savedAvatar;
    }

    private Avatar createAndSaveAvatar(final String path) {
        final Avatar avatar = new Avatar();
        avatar.setId(UUID.randomUUID().toString());
        avatar.setPath(path);
        return avatarRepository.save(avatar);
    }
}
