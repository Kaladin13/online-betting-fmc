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
)