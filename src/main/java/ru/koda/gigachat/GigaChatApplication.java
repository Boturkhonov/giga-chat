package ru.koda.gigachat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GigaChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigaChatApplication.class, args);
    }

}
