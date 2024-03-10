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

create index ind_tournament on tournament(discipline_id, ended_at);