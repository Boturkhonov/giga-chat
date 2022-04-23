package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.koda.gigachat.entity.MessageAttachment;

@Repository
public interface MessageAttachmentRepository extends JpaRepository<MessageAttachment, String> {
}
