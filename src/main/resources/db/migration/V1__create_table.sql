create table users
(
    id        serial not null,
    uid       varchar(255),
    firstname varchar(255),
    lastname  varchar(255),
    email     varchar(255),
    password  varchar(255),
    active    boolean default false
);