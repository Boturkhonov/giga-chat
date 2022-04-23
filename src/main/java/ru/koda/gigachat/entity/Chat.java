package ru.koda.gigachat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Сущность Чата.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chat extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "chat_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ChatType chatType;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "chanel_id", nullable = false)
    private String chanelId;
}
