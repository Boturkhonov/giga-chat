package ru.koda.gigachat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Сущность Пользователя.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Column(name = "about")
    private String about;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @ManyToMany(mappedBy = "users")
    private Set<Channel> channels;

    @Transient
    private String token;

}
