-- Аватар по умолчанию
insert into avatar(id, path, creation_time)
values ('id_User_Default_Avatar', 'gigachad.jpg', '2022-04-23 00:00:00');

insert into app_user (id, creation_time, login, password, name, avatar_id, about)
values ('6f6166d9-78bc-40ec-b71c-dfe040c657ee', '2022-04-23 00:00:00', 'user',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Камрон Ботурхонов',
        'id_User_Default_Avatar', 'Main Gigachad'),
       ('8e10825e-82a5-4641-88fc-0095827d3a50', '2022-04-23 00:00:00', 'user1',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Олег Лукаш',
        'id_User_Default_Avatar', 'Gigachad'),
       ('240a4774-e7cd-461d-8da6-5f6d00c5c323', '2022-04-23 00:00:00', 'user2',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Александр Никульшеев',
        'id_User_Default_Avatar', 'Gigachad'),
       ('9c62c5bc-be81-49d2-b108-8f8d6b0d350e', '2022-04-23 00:00:00', 'user3',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Денис Бронников',
        'id_User_Default_Avatar', 'Gigachad');

insert into chat (id, creation_time, name, chat_type, avatar_id, channel_id)
values ('00d17cad-1c0e-4ea4-b793-ad11d40a2149', '2022-04-23 00:00:00', null, 0, null, null),
       ('3a4fcd65-bad5-4151-be53-34eb5752ffbe', '2022-04-23 00:00:00', null, 0, null, null),
       ('2e370d81-aec9-4791-8e9f-2ea2bf76fcb3', '2022-04-23 00:00:00', null, 0, null, null);

insert into user_to_chat(id, chat_id, user_id, last_read_message_id, creation_time)
values ('ed3aef74-a0eb-45db-9f38-0527af577e76', '00d17cad-1c0e-4ea4-b793-ad11d40a2149',
        '6f6166d9-78bc-40ec-b71c-dfe040c657ee', null, '2022-04-23 00:00:00'),
       ('ba010653-4e1a-488f-8551-314fa6eafed1', '00d17cad-1c0e-4ea4-b793-ad11d40a2149',
        '8e10825e-82a5-4641-88fc-0095827d3a50', null, '2022-04-23 00:00:00'),

       ('4d2f118f-c14f-4a93-84a7-d280fcc76adc', '3a4fcd65-bad5-4151-be53-34eb5752ffbe',
        '240a4774-e7cd-461d-8da6-5f6d00c5c323', null, '2022-04-23 00:00:00'),
       ('b4cb7bd9-c227-4ab5-b5dc-14f9968e473c', '3a4fcd65-bad5-4151-be53-34eb5752ffbe',
        '9c62c5bc-be81-49d2-b108-8f8d6b0d350e', null, '2022-04-23 00:00:00'),

       ('b3b85695-82fd-4a98-8d35-9fafaa4d8dd4', '2e370d81-aec9-4791-8e9f-2ea2bf76fcb3',
        '6f6166d9-78bc-40ec-b71c-dfe040c657ee', null, '2022-04-23 00:00:00'),
       ('4064458e-1b16-4a13-b44c-adfd6b188fe8', '2e370d81-aec9-4791-8e9f-2ea2bf76fcb3',
        '9c62c5bc-be81-49d2-b108-8f8d6b0d350e', null, '2022-04-23 00:00:00');
