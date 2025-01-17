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
    after insert on "match"
    for each row
    execute procedure create_rounds();