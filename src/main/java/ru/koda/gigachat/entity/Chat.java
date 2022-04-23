package ru.koda.gigachat.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Сущность Чата.
 *
 * @author Kamron Boturkhonov
 * @since 2022.04.23
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "chat")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Chat extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "chat_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ChatType chatType;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToMany(mappedBy = "chats")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();
}
