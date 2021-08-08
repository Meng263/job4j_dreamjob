CREATE TABLE post
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE candidate
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     TEXT unique,
    email    TEXT unique,
    password TEXT
);
