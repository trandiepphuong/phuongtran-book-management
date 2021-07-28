-- drop table comment;
-- drop table book;
-- drop table "user";
-- drop table role;
create table role
(
    id   serial       not null primary key,
    name varchar(255) not null
);
create table "user"
(
    id         serial       not null primary key,
    email      varchar(255) not null,
    password   varchar(255) not null,
    first_name varchar(255),
    last_name  varchar(255),
    enabled    bit          not null,
    avatar     varchar(255) not null,
    role_id    int,
    FOREIGN KEY (role_id) REFERENCES role (id)
);
create table book
(
    id          serial       not null primary key,
    title       varchar(255) not null,
    author      varchar(255) not null,
    description varchar(255),
    created_at  date         not null,
    updated_at  date         not null,
    image       varchar(255),
    enabled     bit          not null,
    user_id     int          not null,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);
create table comment
(
    id         serial       not null,
    message    varchar(255) not null,
    user_id    int          not null,
    book_id    int          not null,
    created_at date         not null,
    updated_at date         not null,
    foreign key (user_id) references "user" (id),
    foreign key (book_id) references book (id)
)
