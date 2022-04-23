package ru.koda.gigachat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZonedDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class GigaChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigaChatApplication.class, args);
    }

}
