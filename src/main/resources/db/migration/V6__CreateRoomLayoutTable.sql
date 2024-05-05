CREATE TABLE IF NOT EXISTS RoomLayout
(
    id         int         not null generated always as identity primary key,
    name       varchar(64) not null,
    seat_count int         not null,
    row_count  int         not null,
    col_count  int         not null
)