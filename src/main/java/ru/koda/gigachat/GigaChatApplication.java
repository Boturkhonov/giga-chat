package ru.koda.gigachat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class GigaChatApplication {

    public static void main(final String[] args) {
        SpringApplication.run(GigaChatApplication.class, args);
    }

}
