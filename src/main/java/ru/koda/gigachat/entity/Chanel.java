package ru.koda.gigachat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Сущность Канала.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "chanel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chanel extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "avatar_path")
    private String avatarPath;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "about")
    private String about;

    @Column(name = "link", nullable = false)
    private String link;

}
