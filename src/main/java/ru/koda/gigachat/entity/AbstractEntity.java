package ru.koda.gigachat.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Абстрактный класс сущности, содержащий базовые поля.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(nullable = false, updatable = false, name = "creation_time")
    @CreatedDate
    private LocalDateTime creationTime;
}
