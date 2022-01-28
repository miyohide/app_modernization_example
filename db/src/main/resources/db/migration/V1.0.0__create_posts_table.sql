create table posts
(
  id    bigserial constraint posts_pk primary key,
  title varchar(255) not null,
  body  text         not null
);

create unique index posts_id_uindex on posts (id);
