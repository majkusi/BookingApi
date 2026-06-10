CREATE TABLE book_copies
(
    id      BIGSERIAL primary key,
    book_id BIGINT      not null REFERENCES books (id),
    status  varchar(50) not null
);