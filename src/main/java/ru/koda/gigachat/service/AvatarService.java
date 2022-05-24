package ru.koda.gigachat.service;

import org.springframework.web.multipart.MultipartFile;
import ru.koda.gigachat.entity.Avatar;
import ru.koda.gigachat.entity.User;

import java.io.IOException;

/**
 * Сервис для работы с Аватарами.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
public interface AvatarService {

    Avatar getById(final String id);

    Avatar saveUserAvatar(final User user, final MultipartFile image) throws IOException;

}
