create table book_copies
(
    id      bigserial primary key,
    book_id bigint      not null references books (id),
    status  varchar(50) not null
);