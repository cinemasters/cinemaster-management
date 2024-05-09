CREATE TABLE IF NOT EXISTS Movie
(
    id                int           not null generated always as identity primary key,
    title             varchar(255)  not null,
    original_title    varchar(255)  null,
    original_language char(2)       null,
    description       varchar(2048) null,
    movie_cast        varchar(255)  null,
    director          varchar(255)  not null,
    production        varchar(64)   not null,
    genre             varchar(64)   not null,
    release_date      date          not null,
    length            int           not null,
    age_restriction   varchar(5)    not null,
    is_visible        bit           not null
)