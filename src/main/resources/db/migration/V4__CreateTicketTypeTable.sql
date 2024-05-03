CREATE TABLE IF NOT EXISTS TicketType
(
    id          int            not null generated always as identity primary key,
    name        varchar(64)    not null,
    description varchar(255)   null,
    price       decimal(18, 2) not null
)