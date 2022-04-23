package ru.koda.gigachat.service;

import ru.koda.gigachat.entity.Avatar;

/**
 * Сервис для работы с Аватарами.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
public interface AvatarService {

    Avatar getById(final String id);

}
