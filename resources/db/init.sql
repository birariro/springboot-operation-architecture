set names utf8;


CREATE TABLE `tb_member` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `login_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `login_pwd` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `nick_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id1', 'pwd1', 'nickname1');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id2', 'pwd2', 'nickname2');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id3', 'pwd3', 'nickname3');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id4', 'pwd4', 'nickname4');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id5', 'pwd5', 'nickname5');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id6', 'pwd6', 'nickname6');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id7', 'pwd7', 'nickname7');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id8', 'pwd8', 'nickname8');
INSERT INTO tb_member (LOGIN_ID, LOGIN_PWD, NICK_NAME)  VALUES ('id9', 'pwd9', 'nickname9');



CREATE TABLE `tb_product` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `price` bigint(20) COLLATE utf8_bin NOT NULL DEFAULT 0 ,
                             `count` bigint(20) COLLATE utf8_bin NOT NULL DEFAULT 0 ,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO tb_product (name, price, count)  VALUES ('책상', 100000, 210);
INSERT INTO tb_product (name, price, count)  VALUES ('의자', 40000, 1000);
INSERT INTO tb_product (name, price, count)  VALUES ('모자', 35000, 540);
INSERT INTO tb_product (name, price, count)  VALUES ('핸드폰', 1750000, 330);



CREATE TABLE `tb_orders` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `member_id` bigint(20) COLLATE utf8_bin NOT NULL,
                              `product_id` bigint(20) COLLATE utf8_bin NOT NULL,
                              `payment_price` bigint(20) COLLATE utf8_bin NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;