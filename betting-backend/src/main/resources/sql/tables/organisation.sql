create table organisation
(
    id       bigserial primary key,
    name     varchar(255) not null,
    logo_url varchar(255),
    region   varchar(50)
);