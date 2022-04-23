package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.MessageAttachment;

public interface MessageAttachmentRepository extends JpaRepository<MessageAttachment, String> {
}
