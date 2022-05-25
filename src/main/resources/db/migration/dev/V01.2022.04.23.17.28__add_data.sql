-- Аватар по умолчанию
insert into avatar(id, path, creation_time)
values ('id_User_Default_Avatar', 'gigachad.jpg', '2022-04-23 00:00:00');

insert into app_user (id, creation_time, login, password, name, avatar_id, about)
values ('id_Test_User', '2022-04-23 00:00:00', 'user',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Камрон Ботурхонов',
        'id_User_Default_Avatar', 'Main Gigachad'),
       ('id_Test_User1', '2022-04-23 00:00:00', 'user1',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Ryan Gosling',
        'id_User_Default_Avatar', 'Actor'),
       ('id_Test_User2', '2022-04-23 00:00:00', 'user2',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Big Floppa',
        'id_User_Default_Avatar', 'Caracal'),
       ('id_Test_User3', '2022-04-23 00:00:00', 'user3',
        '$2a$10$OAEN/XKPdbeq09yGfJPxwu0tfdsVfiFeuf7BVFzHbjEMf7GIiUwGC', 'Giga Chad',
        'id_User_Default_Avatar', 'Sharp jaw');

insert into chat (id, creation_time, name, chat_type, avatar_id, channel_id)
values ('id_Test_Private_Chat1', '2022-04-23 00:00:00', null, 0, null, null),
       ('3a4fcd65-bad5-4151-be53-34eb5752ffbe', '2022-04-23 00:00:00', null, 0, null, null),
       ('id_Test_Private_Chat3', '2022-04-23 00:00:00', null, 0, null, null);

insert into user_to_chat(id, chat_id, user_id, last_read_message_id, creation_time)
values ('ed3aef74-a0eb-45db-9f38-0527af577e76', 'id_Test_Private_Chat1',
        'id_Test_User', null, '2022-04-23 00:00:00'),
       ('ba010653-4e1a-488f-8551-314fa6eafed1', 'id_Test_Private_Chat1',
        'id_Test_User1', null, '2022-04-23 00:00:00'),

       ('4d2f118f-c14f-4a93-84a7-d280fcc76adc', '3a4fcd65-bad5-4151-be53-34eb5752ffbe',
        'id_Test_User2', null, '2022-04-23 00:00:00'),
       ('b4cb7bd9-c227-4ab5-b5dc-14f9968e473c', '3a4fcd65-bad5-4151-be53-34eb5752ffbe',
        'id_Test_User3', null, '2022-04-23 00:00:00'),

       ('b3b85695-82fd-4a98-8d35-9fafaa4d8dd4', 'id_Test_Private_Chat3',
        'id_Test_User', null, '2022-04-23 00:00:00'),
       ('4064458e-1b16-4a13-b44c-adfd6b188fe8', 'id_Test_Private_Chat3',
        'id_Test_User3', null, '2022-04-23 00:00:00');


insert into channel(id, creation_time, name, avatar_id, owner_id, about, link)
values ('id_Test_Channel_1', '2022-05-24 22:23:08.415000', 'quos', 'id_User_Default_Avatar',
        'id_Test_User', 'Cumque iure a dolores magni.', '8cffe4abd621317f02edc602e377afa6');

insert into user_to_channel(id, channel_id, user_id, creation_time)
values ('3c673aaa-8493-46a3-b486-35d9a7c38fb9', 'id_Test_Channel_1', 'id_Test_User', '2022-05-24 22:23:08.415000');
