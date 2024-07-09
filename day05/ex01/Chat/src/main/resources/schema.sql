DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.users (
    userId SERIAL PRIMARY KEY,
    login VARCHAR(25) UNIQUE NOT NULL,
    password VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.rooms (
    roomId SERIAL PRIMARY KEY,
    ownerId INTEGER REFERENCES chat.users(userId),
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS chat.massages (
    massageId SERIAL PRIMARY KEY,
    authorId INTEGER REFERENCES chat.users(userId),
    chatRoomId INTEGER REFERENCES chat.rooms(roomId),
    text TEXT NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT clock_timestamp()
);
