package ru.koda.gigachat.service;

import ru.koda.gigachat.entity.Message;

/**
 * Сервис для работы с Сообщениями.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
public interface MessageService {

    Message saveMessage(final Message message, final String senderLogin);

}
