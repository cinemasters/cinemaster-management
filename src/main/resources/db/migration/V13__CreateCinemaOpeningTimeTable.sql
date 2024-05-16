CREATE TABLE IF NOT EXISTS CinemaOpeningTime
(
    id           int         not null generated always as identity primary key,
    cinema_id    int         not null,
    week_day     varchar(16) not null,
    opening_time time        null,
    closing_time time        null,
    is_closed    bit         not null,
    constraint fk_opening_cinema foreign key (cinema_id) references Cinema (id)
)