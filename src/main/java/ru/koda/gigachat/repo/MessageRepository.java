package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.koda.gigachat.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}
