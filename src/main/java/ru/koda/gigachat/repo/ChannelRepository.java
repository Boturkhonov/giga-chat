package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.Channel;

/**
 * Репозиторий для работы с Каналами.
 */
public interface ChannelRepository extends JpaRepository<Channel, String> {
}
