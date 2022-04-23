package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koda.gigachat.entity.Channel;
import ru.koda.gigachat.entity.ChannelUser;
import ru.koda.gigachat.entity.User;

public interface ChannelUserRepository extends JpaRepository<ChannelUser, String> {

    void deleteByChannelAndUser(final Channel channel, final User user);
}
