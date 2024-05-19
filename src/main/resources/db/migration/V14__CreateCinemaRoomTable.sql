CREATE TABLE IF NOT EXISTS CinemaRoom
(
    id        int         not null generated always as identity primary key,
    cinema_id int         not null,
    layout_id int         not null,
    name      varchar(64) not null
)