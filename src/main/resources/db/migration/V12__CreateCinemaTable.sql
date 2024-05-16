CREATE TABLE IF NOT EXISTS Cinema
(
    id           int           not null generated always as identity primary key,
    name         varchar(255)  not null,
    description  varchar(2048) null,
    city         varchar(255)  not null,
    street       varchar(255)  not null,
    postal_code  varchar(64)   null,
    email        varchar(255)  null,
    phone_number varchar(64)   null
)