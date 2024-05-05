CREATE TABLE IF NOT EXISTS LayoutSeat
(
    id        int not null generated always as identity primary key,
    row_num   int not null,
    col_num   int not null,
    layout_id int not null,
    type_id   int null,
    is_hidden bit not null,
    constraint fk_layoutseat_layout foreign key (layout_id) references RoomLayout (id),
    constraint fk_layoutseat_type foreign key (type_id) references SeatType (id)
)