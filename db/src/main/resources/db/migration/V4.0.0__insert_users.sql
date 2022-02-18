-- 名前：hoge、パスワード：123456
insert into users(name, password) values('hoge', '$2a$10$KBVjUqJO8wdYklH9dV4RFOOMzd0rJhjEJtwJDeEep3GTAefMbCynO');
insert into authorities (user_id, authority) values ( ( select id from users where name = 'hoge'), 'ROLE_USER');
-- 名前：hoge2、パスワード：hogehoge
insert into users(name, password) values('hoge2', '$2a$10$zG75DibVEeDlOze0T/2G6Ok6e0yNBIx4aUW4M.s4dmpYwnQ4x2P0m');
insert into authorities (user_id, authority) values ( ( select id from users where name = 'hoge2'), 'ROLE_USER');
