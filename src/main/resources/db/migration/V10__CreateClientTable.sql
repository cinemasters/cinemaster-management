CREATE TABLE IF NOT EXISTS Client
(
    id                  int          not null generated always as identity primary key,
    name                varchar(64)  null,
    surname             varchar(64)  null,
    email               varchar(64)  not null,
    password_hash       varchar(128) not null,
    phone_number        varchar(16)  null,
    favourite_cinema_id int          null,
    is_offer_subscribed bit          not null,
    is_locked           bit          not null
)