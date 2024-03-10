drop table if exists "user" cascade;
drop table if exists "match" cascade;
drop table if exists "round" cascade;
drop table if exists "bid_event" cascade;
drop table if exists "bids" cascade;
drop table if exists "balance_tickets" cascade;
drop table if exists "discipline" cascade;
drop table if exists "tournament" cascade;
drop table if exists "user_bids" cascade;
drop table if exists "organisation" cascade;
drop table if exists "team" cascade;

drop trigger IF EXISTS trig_match_create on match;

drop trigger if exists trig_match_ending_update on match;

drop trigger if exists trig_bal_tickets_update on balance_tickets;

drop trigger if exists trig_bid_event_update on bid_event;

drop trigger if exists trig_user_bids_update on user_bids;

drop index ind_match_status;

drop index ind_tournament;
