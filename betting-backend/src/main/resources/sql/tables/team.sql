create table team
(
    id               bigserial primary key,
    org_id           bigint references organisation (id) not null,
    roaster_name     varchar(255),
    roaster_logo_url varchar(255),
    discipline_id    bigint references discipline (id) not null
);