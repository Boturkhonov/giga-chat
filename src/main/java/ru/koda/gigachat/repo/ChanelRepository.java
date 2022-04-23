package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.koda.gigachat.entity.Chanel;

@Repository
public interface ChanelRepository extends JpaRepository<Chanel, String> {
}
