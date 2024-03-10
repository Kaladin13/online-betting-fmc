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