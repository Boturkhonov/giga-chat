package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.Message;

public interface MessageRepository extends JpaRepository<Message, String> {
}
