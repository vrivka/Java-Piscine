DROP SCHEMA IF EXISTS test CASCADE;

CREATE SCHEMA test;

CREATE TABLE IF NOT EXISTS test.product (
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE,
    price INTEGER NOT NULL
);
