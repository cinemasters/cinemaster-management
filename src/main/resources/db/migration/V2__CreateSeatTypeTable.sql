CREATE TABLE IF NOT EXISTS SeatType
(
    id          int          not null generated always as identity primary key,
    code        varchar(1)   not null,
    name        varchar(64)  not null,
    description varchar(255) null
)