set names utf8;


CREATE TABLE `TB_MEMBER` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `create_date` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
                             `update_date` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
                             `login_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `login_pwd` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `nick_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id1', 'pwd1', 'nickname1');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id2', 'pwd2', 'nickname2');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id3', 'pwd3', 'nickname3');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id4', 'pwd4', 'nickname4');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id5', 'pwd5', 'nickname5');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id6', 'pwd6', 'nickname6');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id7', 'pwd7', 'nickname7');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id8', 'pwd8', 'nickname8');
INSERT INTO TB_MEMBER (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id9', 'pwd9', 'nickname9');

