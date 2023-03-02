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

create table roles
(
    id        integer,
    role_name varchar(40),
    comment   varchar(255)
);

create table users_roles
(
    id       serial not null,
    users_id bigint,
    roles_id integer
);