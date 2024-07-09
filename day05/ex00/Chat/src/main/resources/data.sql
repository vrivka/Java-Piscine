INSERT INTO chat.users (login, password)
VALUES ('vrivka', 'qwerty');
INSERT INTO chat.users (login, password)
VALUES ('vivka', 'asdfg');
INSERT INTO chat.users (login, password)
VALUES ('viva', 'vivat');
INSERT INTO chat.users (login, password)
VALUES ('via', 'rio');
INSERT INTO chat.users (login, password)
VALUES ('vika', 'gia');

INSERT INTO chat.rooms (ownerid, name)
VALUES ((SELECT userId FROM chat.users AS ownerId WHERE login = 'vrivka'), 'WoW');
INSERT INTO chat.rooms (ownerid, name)
VALUES ((SELECT userId FROM chat.users AS ownerId WHERE login = 'vivka'), 'CS:GO');
INSERT INTO chat.rooms (ownerid, name)
VALUES ((SELECT userId FROM chat.users AS ownerId WHERE login = 'vrivka'), 'WoW:classic');
INSERT INTO chat.rooms (ownerid, name)
VALUES ((SELECT userId FROM chat.users AS ownerId WHERE login = 'viva'), 'Genshin Impact');
INSERT INTO chat.rooms (ownerid, name)
VALUES ((SELECT userId FROM chat.users AS ownerId WHERE login = 'vivka'), 'DOTA 2');

INSERT INTO chat.massages (authorid, chatroomid, text)
VALUES ((SELECT userId FROM chat.users AS authorId WHERE login = 'vrivka')
       , (SELECT roomId FROM chat.rooms AS chatroomId WHERE "name" = 'WoW')
       , 'Hello Everyone!');
INSERT INTO chat.massages (authorid, chatroomid, text)
VALUES ((SELECT userId FROM chat.users AS authorId WHERE login = 'vika')
       , (SELECT roomId FROM chat.rooms AS chatroomId WHERE "name" = 'WoW')
       , 'Hi');
INSERT INTO chat.massages (authorid, chatroomid, text)
VALUES ((SELECT userId FROM chat.users AS authorId WHERE login = 'vivka')
       , (SELECT roomId FROM chat.rooms AS chatroomId WHERE "name" = 'CS:GO')
       , 'Where is everyone');
INSERT INTO chat.massages (authorid, chatroomid, text)
VALUES ((SELECT userId FROM chat.users AS authorId WHERE login = 'via')
       , (SELECT roomId FROM chat.rooms AS chatroomId WHERE "name" = 'CS:GO')
       , 'In WoW chat!');
INSERT INTO chat.massages (authorid, chatroomid, text)
VALUES ((SELECT userId FROM chat.users AS authorId WHERE login = 'vivka')
       , (SELECT roomId FROM chat.rooms AS chatroomId WHERE "name" = 'WoW:classic')
       , 'Aghh... They in another WoW chat!');
