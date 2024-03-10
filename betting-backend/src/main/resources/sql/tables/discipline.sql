create table discipline
(
    id            bigserial primary key,
    name          varchar(255) not null,
    logo_url      varchar(255),
    is_cybersport boolean
);