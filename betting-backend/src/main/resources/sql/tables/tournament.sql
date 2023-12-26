create table tournament
(
    id            bigserial primary key,
    name          varchar(255) not null,
    logo_url      varchar(255) not null,
    discipline_id bigint references discipline (id) not null,
    started_at    timestamptz,
    ended_at      timestamptz
);