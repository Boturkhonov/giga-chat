insert into avatar(id, path, creation_time)
values ('9bad808c-e444-471a-a21f-89e76d085146', 'default.png', '2022-04-23 00:00:00');

insert into app_user (id, creation_time, login, password, name, avatar_id, about)
values ('6f6166d9-78bc-40ec-b71c-dfe040c657ee', '2022-04-23 00:00:00', 'user',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Ryan Gosling',
        null, 'Main Gigachad'),
       ('8e10825e-82a5-4641-88fc-0095827d3a50', '2022-04-23 00:00:00', 'user1',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Иванов Иван',
        null, 'About'),
       ('240a4774-e7cd-461d-8da6-5f6d00c5c323', '2022-04-23 00:00:00', 'user2',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Петров Петр',
        null, 'About');

insert into chat (id, creation_time, name, chat_type, avatar_id, channel_id)
values ('00d17cad-1c0e-4ea4-b793-ad11d40a2149', '2022-04-23 00:00:00', null, 0, null, null);

insert into user_to_chat(id, chat_id, user_id, last_read_message_id, creation_time)
values ('ed3aef74-a0eb-45db-9f38-0527af577e76', '00d17cad-1c0e-4ea4-b793-ad11d40a2149',
        '6f6166d9-78bc-40ec-b71c-dfe040c657ee', null, '2022-04-23 00:00:00'),
       ('ba010653-4e1a-488f-8551-314fa6eafed1', '00d17cad-1c0e-4ea4-b793-ad11d40a2149',
        '8e10825e-82a5-4641-88fc-0095827d3a50', null, '2022-04-23 00:00:00');
