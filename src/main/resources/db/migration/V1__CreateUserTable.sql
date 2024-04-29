CREATE TABLE IF NOT EXISTS UserEntity (
    id          int             not null    generated always as identity primary key,
    name        varchar(64)     not null,
    surname     varchar(64)     not null,
    username    varchar(64)     not null,
    password    varchar(128)    not null
)