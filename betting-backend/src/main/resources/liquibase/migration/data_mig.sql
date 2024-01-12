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
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (1, 'Virstus.pro', 'virtus.com', 2);
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (1, 'BetBoom Team', 'BetBoom.ru', 2);
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (2, 'Манчестер', 'oMJphfbb', 1);
INSERT INTO team (org_id, roaster_name, roaster_logo_url, discipline_id) VALUES (2, 'Зенит', 'oMJphfbb', 1);

TRUNCATE TABLE match restart identity cascade;
INSERT INTO match (l_team_id, r_team_id, tournament_id, best_of, status) VALUES (1, 2, 2, 2, 'второй раунд');
INSERT INTO match (l_team_id, r_team_id, tournament_id, best_of, status) VALUES (3, 4, 1, 3, 'пенальти');

INSERT INTO match (l_team_id, r_team_id, tournament_id, best_of, status) VALUES (1, 2, 2, 2, 'второй раунд');
INSERT INTO match (l_team_id, r_team_id, tournament_id, best_of, status) VALUES (3, 4, 1, 3, 'пенальти');

INSERT INTO match (l_team_id, r_team_id, tournament_id, best_of, status) VALUES (1, 2, 2, 2, 'второй раунд');
INSERT INTO match (l_team_id, r_team_id, tournament_id, best_of, status) VALUES (3, 4, 1, 3, 'пенальти');

TRUNCATE TABLE round restart identity cascade;
INSERT INTO round (match_id, round_order, status) VALUES (1, 27, 'zFyocrMa');

TRUNCATE TABLE bid_event restart identity cascade;
INSERT INTO bid_event (round_id, match_id, name, status) VALUES (1, 1, 'PQCRXfbD', 'gEsXtyZx');
INSERT INTO bid_event (round_id, match_id, name, status) VALUES (1, 2, 'PQCRXfbD', 'gEsXtyZx');

TRUNCATE TABLE bids restart identity cascade;
INSERT INTO bids (event_id, name, rate, status) VALUES (1, 'OVhfwyYr', 55.318, 'CIWtGhGM');
INSERT INTO bids (event_id, name, rate, status) VALUES (2, 'OVhfwyYr', 55.318, 'CIWtGhGM');

TRUNCATE TABLE "user" restart identity cascade;
INSERT INTO "user" (login, password, balance, is_trusted, name, surname, email, passport)
VALUES ('ePibHKDi', 'qzmgkNXz', 43.12, False, 'tyUyuMfl', 'yRuBefYr', 'XhVBESLv', 'DcNdGDyS');

TRUNCATE TABLE "user_bids" restart identity cascade;