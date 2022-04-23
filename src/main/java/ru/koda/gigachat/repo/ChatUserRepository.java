package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser, String> {
}
