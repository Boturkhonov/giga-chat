package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, String> {
}
