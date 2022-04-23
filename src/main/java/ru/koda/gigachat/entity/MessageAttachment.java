package ru.koda.gigachat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Сущность Приложения к сообщению.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@Entity
@Table(name = "message_attachment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageAttachment extends AbstractEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(name = "attachment_path", nullable = false)
    private String attachmentPath;

}
