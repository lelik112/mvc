
INSERT INTO role (id, name) VALUES (1, 'ROLE_RESGISTERED_USER');
INSERT INTO role (id, name) VALUES (2, 'ROLE_BOOKING_MANAGER');

INSERT INTO user (id, name, password) VALUES (1, 'alex', '$2a$10$IG2E9DUTGVp1wLxAB1lU8ukoRFgMeSYE2DNBKeiv9upmDY8Kj/eJe');
INSERT INTO user (id, name, password) VALUES (2, 'admin', '$2a$10$IG2E9DUTGVp1wLxAB1lU8ukoRFgMeSYE2DNBKeiv9upmDY8Kj/eJe');

INSERT INTO user_role (role_id, user_id) VALUES (1, 1);
INSERT INTO user_role (role_id, user_id) VALUES (1, 2);
INSERT INTO user_role (role_id, user_id) VALUES (2, 2);

