CREATE DATABASE IF NOT EXISTS `todo` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `todo`;

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

INSERT INTO `task` VALUES (1,'aaa',1),(2,'bbb',2),(3,'ccc',0),(4,'ddd',1),(5,'eee',2),(6,'fff',0),(7,'ggg',1),(8,'hhh',2),(9,'jjj',0),(10,'kkk',1),(11,'lll',2),(12,'mmm',0),(13,'nnn',1),(14,'ooo',2),(15,'ppp',0);