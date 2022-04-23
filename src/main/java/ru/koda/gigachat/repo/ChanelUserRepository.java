package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.koda.gigachat.entity.ChanelUser;

@Repository
public interface ChanelUserRepository extends JpaRepository<ChanelUser, String> {
}
