CREATE TABLE IF NOT EXISTS TicketPerk
(
    id                int            not null generated always as identity primary key,
    name              varchar(64)    not null,
    description       varchar(255)   null,
    type              varchar(16)    not null,
    charge            decimal(18, 2) not null,
    screening_type_id int            null,
    seat_type_id      int            null,
    constraint fk_seat_type foreign key (seat_type_id) references SeatType (id),
    constraint fk_screening_type foreign key (screening_type_id) references ScreeningType (id)
)