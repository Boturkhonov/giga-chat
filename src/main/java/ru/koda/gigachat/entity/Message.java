package ru.koda.gigachat.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Сущность Сообщения.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Message extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Chat chat;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Column(name = "text")
    private String text;
}
