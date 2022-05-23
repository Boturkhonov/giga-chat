--
-- Структура таблиц
--
create table app_user
(
    id            varchar(255) not null,
    creation_time timestamp    not null,
    login         varchar(255) not null,
    password      varchar(255) not null,
    name          varchar(255) not null,
    avatar_id     varchar(255),
    about         varchar(255),
    is_public     boolean      not null default true
);

create table channel
(
    id            varchar(255) not null,
    creation_time timestamp    not null,
    name          varchar(255) not null,
    avatar_id     varchar(255),
    owner_id      varchar(255) not null,
    about         varchar(255),
    link          varchar(255) not null
);

create table user_to_channel
(
    id            varchar(255) not null,
    channel_id    varchar(255) not null,
    user_id       varchar(255) not null,
    creation_time timestamp    not null
);

create table chat
(
    id            varchar(255) not null,
    creation_time timestamp    not null,
    name          varchar(255),
    chat_type     integer      not null,
    avatar_id     varchar(255),
    channel_id    varchar(255)
);

create table user_to_chat
(
    id                   varchar(255) not null,
    chat_id              varchar(255) not null,
    user_id              varchar(255) not null,
    last_read_message_id varchar(255),
    creation_time        timestamp    not null
);

create table message
(
    id            varchar(255) not null,
    creation_time timestamp    not null,
    sender_id     varchar(255) not null,
    chat_id       varchar(255) not null,
    is_read       boolean      not null default false,
    text          text
);

create table message_attachment
(
    id              varchar(255) not null,
    creation_time   timestamp    not null,
    message_id      varchar(255) not null,
    attachment_path varchar(512)
);

create table avatar
(
    id            varchar(255) not null,
    path          varchar(255) not null,
    creation_time timestamp    not null
);

alter table only app_user
    add constraint app_user_pkey primary key (id);
alter table only app_user
    add constraint app_user_login_ukey unique (login);
alter table only channel
    add constraint channel_pkey primary key (id);
alter table only user_to_channel
    add constraint user_to_channel_pkey primary key (id);
alter table only user_to_channel
    add constraint user_to_channel_ukey unique (user_id, channel_id);
alter table only chat
    add constraint chat_pkey primary key (id);
alter table only user_to_chat
    add constraint user_to_chat_pkey primary key (id);
alter table only user_to_chat
    add constraint user_to_chat_ukey unique (user_id, chat_id);
alter table only message
    add constraint message_pkey primary key (id);
alter table only message_attachment
    add constraint message_attachment_pkey primary key (id);
alter table only avatar
    add constraint avatar_pkey primary key (id);


alter table only app_user
    add constraint app_user_avatar_id_fk foreign key (avatar_id) references avatar (id);
alter table only channel
    add constraint channel_owner_id_fk foreign key (owner_id) references app_user (id);
alter table only channel
    add constraint channel_avatar_id_fk foreign key (avatar_id) references avatar (id);
alter table only user_to_channel
    add constraint user_to_channel_user_id_fk foreign key (user_id) references app_user (id);
alter table only user_to_channel
    add constraint user_to_channel_channel_id_fk foreign key (channel_id) references channel (id);
alter table only chat
    add constraint chat_channel_id_fk foreign key (channel_id) references channel (id);
alter table only chat
    add constraint chat_avatar_id_fk foreign key (avatar_id) references avatar (id);
alter table only user_to_chat
    add constraint user_to_chat_chat_id_fk foreign key (chat_id) references chat (id);
alter table only user_to_chat
    add constraint user_to_chat_user_id_fk foreign key (user_id) references app_user (id);
alter table only user_to_chat
    add constraint user_to_chat_last_read_message_id_fk foreign key (last_read_message_id) references message (id);
alter table only message
    add constraint message_sender_id_fk foreign key (sender_id) references app_user (id);
alter table only message
    add constraint message_chat_id_fk foreign key (chat_id) references chat (id);
alter table only message_attachment
    add constraint message_attachment_message_id_fk foreign key (message_id) references message (id);