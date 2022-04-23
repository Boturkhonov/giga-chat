package ru.koda.gigachat.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Column(name = "about")
    private String about;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = Boolean.TRUE;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Channel> channels = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "user_to_chat", joinColumns = { @JoinColumn(name = "chat_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<Chat> chats = new HashSet<>();

    @Transient
    private String token;
}
