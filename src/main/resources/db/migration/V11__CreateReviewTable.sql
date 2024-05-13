CREATE TABLE IF NOT EXISTS Review
(
    id               int           not null generated always as identity primary key,
    rating           decimal(2, 1) not null,
    user_comment     varchar(2048) null,
    publication_date datetime      not null,
    movie_id         int           not null,
    client_id        int           null,
    is_hidden        bit           not null,
    constraint fk_review_movie foreign key (movie_id) references Movie (id),
    constraint fk_review_client foreign key (client_id) references Client (id)
)