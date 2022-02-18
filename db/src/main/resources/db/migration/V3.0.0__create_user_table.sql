create table users
(
  id bigserial constraint users_pk primary key,
  name VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL
);

create table authorities (
  id bigserial constraint authorities_pk primary key,
  user_id bigint references users(id),
  authority VARCHAR(100)
);

create unique index users_id_uindex on users (id);
create unique index authorities_id_uindex on authorities (id);
