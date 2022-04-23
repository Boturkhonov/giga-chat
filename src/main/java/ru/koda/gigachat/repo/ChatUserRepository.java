package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.koda.gigachat.entity.ChatUser;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, String> {
}
