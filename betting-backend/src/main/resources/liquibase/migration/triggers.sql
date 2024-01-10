--liquibase formatted sql

--changeset rusilee:create_procedures runInTransaction:false runOnChange:true

CREATE OR REPLACE FUNCTION
    create_rounds()
    RETURNS
        TRIGGER
    LANGUAGE plpgsql
AS '
BEGIN
    for counter in 1..new.best_of
        loop
            insert into round (match_id, round_order, status)
            values (NEW.id, counter, ''pending'');
        end loop;
    RETURN NEW;
END;';

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
AS '
BEGIN
    if new.status = ''ended'' then
        update round set status = ''ended'' where match_id = new.id;
    end if;
    RETURN NEW;
END;';

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
AS '
BEGIN
    NEW.edited_at := NOW();
    RETURN NEW;
END;';

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