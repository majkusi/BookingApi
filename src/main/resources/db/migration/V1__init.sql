CREATE TABLE books
(
    id        BIGSERIAL primary key,
    title     varchar(255) not null,
    pages     int          not null,
    author    varchar(255) not null,
    publisher varchar(255) not null

);