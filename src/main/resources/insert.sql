
INSERT INTO role (id, name) VALUES (1, 'ROLE_REGISTERED_USER');
INSERT INTO role (id, name) VALUES (2, 'ROLE_BOOKING_MANAGER');

INSERT INTO user (id, name, password, amount) VALUES (1, 'alex', '$2a$10$IG2E9DUTGVp1wLxAB1lU8ukoRFgMeSYE2DNBKeiv9upmDY8Kj/eJe', 669);
INSERT INTO user (id, name, password, amount) VALUES (2, 'admin', '$2a$10$IG2E9DUTGVp1wLxAB1lU8ukoRFgMeSYE2DNBKeiv9upmDY8Kj/eJe', 669);

INSERT INTO user_role (role_id, user_id) VALUES (1, 1);
INSERT INTO user_role (role_id, user_id) VALUES (1, 2);
INSERT INTO user_role (role_id, user_id) VALUES (2, 2);

INSERT INTO event (id, name, date) VALUES (1, 'Star Wars VIII', '2021-12-12');
INSERT INTO event (id, name, date) VALUES (2, 'Half marathon', '2026-09-09');

INSERT INTO ticket (price, event_id) VALUES (36, 1), (36, 1), (36, 1), (36, 1), (36, 1), (36, 1);
INSERT INTO ticket (price, event_id) VALUES (69, 1), (69, 1), (69, 1), (69, 1), (69, 1), (69, 1);
INSERT INTO ticket (price, event_id) VALUES (36, 2), (36, 2), (36, 2), (36, 2), (36, 2), (36, 2);
INSERT INTO ticket (price, event_id) VALUES (69, 2), (36, 2), (36, 2), (36, 2), (36, 2), (36, 2);

INSERT INTO bank (id, amount) VALUES (1, 9996);