package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.ChannelUser;

public interface ChannelUserRepository extends JpaRepository<ChannelUser, String> {
}
