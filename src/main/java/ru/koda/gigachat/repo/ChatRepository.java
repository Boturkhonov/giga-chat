package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, String> {
}
