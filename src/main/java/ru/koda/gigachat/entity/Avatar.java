package ru.koda.gigachat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущность Аватара.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "avatar")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Avatar extends AbstractEntity {

    @Column(name = "path", nullable = false)
    private String path;
}
