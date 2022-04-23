package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.koda.gigachat.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {
}
