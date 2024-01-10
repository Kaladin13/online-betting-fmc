--liquibase formatted sql

--changeset rusilee:create_procedures runInTransaction:false runOnChange:true

create index if not exists ind_match_status on match (status);

create index if not exists ind_tournament on tournament (discipline_id, ended_at);