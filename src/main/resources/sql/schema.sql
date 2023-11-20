CREATE TABLE `board` (
                         `article_no` int NOT NULL AUTO_INCREMENT,
                         `user_id` varchar(16) DEFAULT NULL,
                         `subject` varchar(100) DEFAULT NULL,
                         `content` varchar(2000) DEFAULT NULL,
                         `hit` int DEFAULT '0',
                         `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`article_no`),
                         KEY `board_to_members_user_id_fk` (`user_id`),
                         CONSTRAINT `board_to_members_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=640 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dongcode` (
                            `dongCode` varchar(10) NOT NULL,
                            `sidoName` varchar(30) DEFAULT NULL,
                            `gugunName` varchar(30) DEFAULT NULL,
                            `dongName` varchar(30) DEFAULT NULL,
                            PRIMARY KEY (`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `file_info` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `room_id` bigint DEFAULT NULL,
                             `save_folder` varchar(45) DEFAULT NULL,
                             `original_file` varchar(50) DEFAULT NULL,
                             `save_file` varchar(50) DEFAULT NULL,
                             `article_no` int DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `file_info_to_rooms_id_fk_idx` (`room_id`),
                             KEY `file_info_to_board_artice_no_fk_idx` (`article_no`),
                             CONSTRAINT `file_info_to_board_artice_no_fk` FOREIGN KEY (`article_no`) REFERENCES `board` (`article_no`),
                             CONSTRAINT `file_info_to_rooms_id_fk` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `housedeal` (
                             `no` bigint NOT NULL,
                             `dealAmount` varchar(20) DEFAULT NULL,
                             `dealYear` int DEFAULT NULL,
                             `dealMonth` int DEFAULT NULL,
                             `dealDay` int DEFAULT NULL,
                             `area` varchar(20) DEFAULT NULL,
                             `floor` varchar(4) DEFAULT NULL,
                             `cancelDealType` varchar(1) DEFAULT NULL,
                             `aptCode` bigint DEFAULT NULL,
                             PRIMARY KEY (`no`),
                             KEY `housedeal_aptCode_houseinfo_aptCode_fk_idx` (`aptCode`),
                             CONSTRAINT `housedeal_aptCode_houseinfo_aptCode_fk` FOREIGN KEY (`aptCode`) REFERENCES `houseinfo` (`aptCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `houseinfo` (
                             `aptCode` bigint NOT NULL,
                             `buildYear` int DEFAULT NULL,
                             `roadName` varchar(40) DEFAULT NULL,
                             `roadNameBonbun` varchar(5) DEFAULT NULL,
                             `roadNameBubun` varchar(5) DEFAULT NULL,
                             `roadNameSeq` varchar(2) DEFAULT NULL,
                             `roadNameBasementCode` varchar(1) DEFAULT NULL,
                             `roadNameCode` varchar(7) DEFAULT NULL,
                             `dong` varchar(40) DEFAULT NULL,
                             `bonbun` varchar(4) DEFAULT NULL,
                             `bubun` varchar(4) DEFAULT NULL,
                             `sigunguCode` varchar(5) DEFAULT NULL,
                             `eubmyundongCode` varchar(5) DEFAULT NULL,
                             `dongCode` varchar(10) DEFAULT NULL,
                             `landCode` varchar(1) DEFAULT NULL,
                             `apartmentName` varchar(40) DEFAULT NULL,
                             `jibun` varchar(10) DEFAULT NULL,
                             `lng` varchar(30) DEFAULT NULL,
                             `lat` varchar(30) DEFAULT NULL,
                             PRIMARY KEY (`aptCode`),
                             UNIQUE KEY `UNIQUE` (`buildYear`,`apartmentName`,`jibun`,`sigunguCode`,`eubmyundongCode`) /*!80000 INVISIBLE */,
                             KEY `houseinfo_dongCode_dongcode_dongCode_fk_idx` (`dongCode`) /*!80000 INVISIBLE */,
                             CONSTRAINT `houseinfo_dongCode_dongcode_dongCode_fk` FOREIGN KEY (`dongCode`) REFERENCES `dongcode` (`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `members` (
                           `user_id` varchar(16) NOT NULL,
                           `user_name` varchar(20) NOT NULL,
                           `user_password` varchar(255) NOT NULL,
                           `email_id` varchar(20) DEFAULT NULL,
                           `email_domain` varchar(45) DEFAULT NULL,
                           `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `token` varchar(1000) DEFAULT NULL,
                           PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rooms` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `lat` varchar(255) NOT NULL,
                         `lag` varchar(255) NOT NULL,
                         `comment` varchar(1000) DEFAULT NULL,
                         `user_id` varchar(16) NOT NULL,
                         `subject` varchar(255) DEFAULT NULL,
                         `register_time` timestamp NULL DEFAULT NULL,
                         `start_date` date DEFAULT NULL,
                         `end_date` date DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `user_id` (`user_id`),
                         CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
