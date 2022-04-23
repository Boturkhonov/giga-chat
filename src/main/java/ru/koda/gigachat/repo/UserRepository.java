package ru.koda.gigachat.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.koda.gigachat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
