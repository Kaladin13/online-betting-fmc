create table "user"
(
    id          bigserial primary key,
    login       varchar(255) unique not null,
    password    varchar(255)        not null,
    referral_id bigint references "user" (id),
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

create table organisation
(
    id       bigserial primary key,
    name     varchar(255) not null,
    logo_url varchar(255),
    region   varchar(50)
);

create table discipline
(
    id            bigserial primary key,
    name          varchar(255) not null,
    logo_url      varchar(255),
    is_cybersport boolean
);

create table team
(
    id               bigserial primary key,
    org_id           bigint references organisation (id) not null,
    roaster_name     varchar(255),
    roaster_logo_url varchar(255),
    discipline_id    bigint references discipline (id)   not null
);

create table tournament
(
    id            bigserial primary key,
    name          varchar(255)                      not null,
    logo_url      varchar(255)                      not null,
    discipline_id bigint references discipline (id) not null,
    started_at    timestamptz,
    ended_at      timestamptz
);

create table match
(
    id            bigserial primary key,
    l_team_id     bigint references team (id)       not null,
    r_team_id     bigint references team (id)       not null,
    best_of       smallint,
    tournament_id bigint references tournament (id) not null,
    started_at    timestamptz,
    ended_at      timestamptz,
    status        varchar(255)
);


create table round
(
    id          bigserial primary key,
    match_id    bigint       not null references match (id),
    round_order smallint     not null,
    status      varchar(255) not null
);

create table bid_event
(
    id         bigserial primary key,
    round_id   bigint references round (id),
    match_id   bigint references match (id),
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

CREATE OR REPLACE PROCEDURE create_bid_event(
    match_id_val int
)
    language sql
begin
    atomic
    insert into bid_event (match_id, name, status)
    values (match_id_val, 'AUTO_MATCH_EVENT', 'ongoing');

    insert into bids (event_id, name, rate, status)
    values (lastval(), 'AUTO_MATCH_WIN', 1.5, 'ongoing');
end;

CREATE OR REPLACE FUNCTION
    create_rounds()
    RETURNS
        TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    for counter in 1..new.best_of
        loop
            insert into round (match_id, round_order, status)
            values (NEW.id, counter, 'pending');
        end loop;
    RETURN NEW;
END;
$$;

create or replace trigger trig_match_create
    after insert
    on "match"
    for each row
execute procedure create_rounds();

CREATE OR REPLACE FUNCTION
    bids_status_update()
    RETURNS
        TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    if new.status = 'ended' then
        update round set status = 'ended' where match_id = new.id;
    end if;
    RETURN NEW;
END;
$$;

create or replace trigger trig_match_ending_update
    after update
    on "match"
    for each row
execute procedure bids_status_update();

CREATE OR REPLACE FUNCTION
    update_edited_at()
    RETURNS
        TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    NEW.edited_at := NOW();
    RETURN NEW;
END;
$$;

create or replace trigger trig_bal_tickets_update
    before update
    on "balance_tickets"
    for each row
execute procedure update_edited_at();

create or replace trigger trig_bid_event_update
    before update
    on "bid_event"
    for each row
execute procedure update_edited_at();

create or replace trigger trig_user_bids_update
    before update
    on "user_bids"
    for each row
execute procedure update_edited_at();


explain analyse
select *
from match
where status = 'ongoing'
order by started_at DESC
limit 10;

create index ind_match_status on match (status);

explain analyse
select *
from tournament
where discipline_id = 1
  and ended_at < now()
limit 100;

create index ind_tournament on tournament (discipline_id, ended_at);