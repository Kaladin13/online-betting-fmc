create table "user"
(
    id          bigserial primary key,
    login       varchar(255) unique not null,
    password    varchar(255)        not null,
    referral_id bigint              not null references "user" (id),
    balance     numeric(14, 2)      not null,
    is_trusted  bool                not null,
    name        varchar(255),
    surname     varchar(255),
    email       varchar(255)        not null,
    passport    varchar(255)
);

create table balance_tickets
(
    id         bigserial primary key,
    type       varchar(255) not null,
    status     varchar(255) not null,
    user_id    bigint       not null references "user" (id),
    edited_at  timestamptz  not null default now(),
    created_at timestamptz  not null default now()
);

create table round
(
    id          bigserial primary key,
    round_order smallint     not null,
    status      varchar(255) not null
);

create table bid_event
(
    id         bigserial primary key,
    round_id   bigint references round (id),
    name       varchar(255) not null,
    status     varchar(255) not null,
    edited_at  timestamptz  not null default now(),
    created_at timestamptz  not null default now()
);

create table bids
(
    id       bigserial primary key,
    event_id bigint        not null references bid_event (id),
    name     varchar(255)  not null,
    rate     numeric(6, 3) not null,
    status   varchar(255)  not null
);

create table user_bids
(
    bid_id     bigint         not null references bids (id),
    user_id    bigint         not null references "user" (id),
    amount     numeric(14, 2) not null,
    edited_at  timestamptz    not null default now(),
    created_at timestamptz    not null default now(),
    fixed_rate numeric(6, 3)  not null,
    status     varchar(255)   not null,
    primary key (bid_id, user_id)
);

