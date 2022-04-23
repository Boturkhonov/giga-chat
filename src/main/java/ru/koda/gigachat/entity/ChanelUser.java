package ru.koda.gigachat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Сущность связи Пользователя с Каналом.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "user_to_chanel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChanelUser extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "chanel_id", nullable = false)
    private Chanel chanel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
