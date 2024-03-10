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