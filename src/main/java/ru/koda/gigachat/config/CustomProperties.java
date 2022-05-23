package ru.koda.gigachat.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ru.koda.giga-chat")
public class CustomProperties {

    private String uploadDirectory;

    private String avatarFolder;

    private String attachmentFolder;

    private String defaultUserAvatarId;

}