CREATE TABLE IF NOT EXISTS ScreeningType
(
    id          int          not null generated always as identity primary key,
    type        char(5)      not null,
    name        varchar(64)  not null,
    description varchar(255) null
)