CREATE TABLE IF NOT EXISTS MovieScreeningType
(
    id                int not null generated always as identity primary key,
    movie_id          int not null,
    screening_type_id int not null,
    constraint fk_mst_movie foreign key (movie_id) references Movie (id),
    constraint fk_mst_scr foreign key (screening_type_id) references ScreeningType (id)
)