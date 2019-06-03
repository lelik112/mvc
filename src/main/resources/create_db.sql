
CREATE TABLE `user`
(
    `id` SERIAL NOT NULL,
    `name` varchar(64) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `role`
(
    `id` int(11) PRIMARY KEY NOT NULL,
    `name` varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `user_role`
(
    `role_id` int(11) NOT NULL DEFAULT 1,
    `user_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `persistent_logins`
(
    `username` varchar(64) NOT NULL,
    `series` varchar(64) PRIMARY KEY,
    `token` varchar(64) NOT NULL,
    `last_used` TIMESTAMP NOT NULL
);
