create table users
(
  id bigserial constraint users_pk primary key,
  name VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  authority VARCHAR(100)
);

create unique index users_id_uindex on users (id);
