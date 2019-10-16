CREATE DATABASE IF NOT EXISTS ataccama_scanner;
USE ataccama_scanner;
CREATE TABLE IF NOT EXISTS `ataccama_scanner.instance_info` (
  `id` bigint(20) NOT NULL,
  `data_base` varchar(255) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `instance_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
insert into ataccama_scanner.instance_info(id, data_base, host, instance_name, password, port, user) values (107, 'instance_info','mysqlataccama', 'tomassirio', 'root', 3306, 'root');
