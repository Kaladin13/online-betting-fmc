CREATE OR REPLACE FUNCTION
    update_edited_at()
RETURNS
    TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    NEW.edited_at := NOW();
    RETURN NEW;
END;
$$;

create or replace trigger trig_bal_tickets_update
    before update on "balance_tickets"
    for each row
    execute procedure update_edited_at();

create or replace trigger trig_bid_event_update
    before update on "bid_event"
    for each row
    execute procedure update_edited_at();

create or replace trigger trig_user_bids_update
    before update on "user_bids"
    for each row
    execute procedure update_edited_at();