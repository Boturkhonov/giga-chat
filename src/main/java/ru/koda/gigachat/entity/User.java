package ru.koda.gigachat.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущность Пользователя.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "app_user")
@Data
public class User extends AbstractEntity {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "about")
    private String about;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

}
