create table members
(
    id            bigserial primary key,
    name          varchar(50) not null,
    email         varchar(50) not null,
    register_date date default current_date,
    status        varchar(50) not null
);
create table loans
(
    id           bigserial primary key,
    book_copy_id bigint references book_copies (id),
    member_id    bigint references members (id),
    start_date   date not null default current_date,
    due_date     date not null,
    return_date  date
);