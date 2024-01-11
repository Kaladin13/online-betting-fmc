--liquibase formatted sql

--changeset rusilee:add_data runInTransaction:false runOnChange:true
TRUNCATE TABLE discipline restart identity cascade;

INSERT INTO discipline (name, logo_url, is_cybersport) VALUES ('Футбол', 'football.com', FALSE);
INSERT INTO discipline (name, logo_url, is_cybersport) VALUES ('Дота', 'steam.com', TRUE);

TRUNCATE TABLE tournament restart identity cascade;
INSERT INTO tournament (name, logo_url, discipline_id) VALUES ('Лига чемпионов', 'loosers.com', 1);
INSERT INTO tournament (name, logo_url, discipline_id) VALUES ('DreamLeague', 'DreamLeague.pro', 2);

TRUNCATE TABLE organisation restart identity cascade;
INSERT INTO organisation (name, logo_url, region) VALUES ('Дотеры', 'помойка.рф', '66');
INSERT INTO organisation (name, logo_url, region) VALUES ('Футболисты мира', 'kuku.com', '77');

TRUNCATE TABLE team restart identity cascade;
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (100, 'NXsLsrEV', 'oMJphfbb', 89);
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (100, 'NXsLsrEV', 'oMJphfbb', 89);
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (100, 'NXsLsrEV', 'oMJphfbb', 89);
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (100, 'NXsLsrEV', 'oMJphfbb', 89);

TRUNCATE TABLE match restart identity cascade;
INSERT INTO match (l_team_id, r_team_id, tournament_id) VALUES (96, 39, 36);
INSERT INTO match (l_team_id, r_team_id, tournament_id) VALUES (96, 39, 36);
INSERT INTO match (l_team_id, r_team_id, tournament_id) VALUES (96, 39, 36);