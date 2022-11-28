/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.6.7-MariaDB : Database - oopclubmanagement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oopclubmanagement`  DEFAULT CHARACTER SET utf8mb4;

USE `oopclubmanagement`;

/*Table structure for table `activities` */

DROP TABLE IF EXISTS `activities`;

CREATE TABLE `activities` (
  `aid` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL DEFAULT '', -- ----------默认值约束----------
  `location` VARCHAR(20) DEFAULT '',
  `cid` INT(11) NOT NULL,
  `timestart` DATETIME DEFAULT NULL,
  `timeend` DATETIME DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `cid` (`cid`),
  CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `clubs` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  KEY `fk_authorities_users` (`username`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `clubs` */

DROP TABLE IF EXISTS `clubs`;

CREATE TABLE `clubs` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `foundDate` DATE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `members` */

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `mid` INT(11) NOT NULL AUTO_INCREMENT,
  `sid` INT(20) NOT NULL,
  `cid` INT(11) NOT NULL,
  `position` VARCHAR(6) DEFAULT NULL,
  PRIMARY KEY (`mid`),
  KEY `cid` (`cid`),
  KEY `sid` (`sid`),
  CONSTRAINT `members_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `clubs` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/* Procedure structure for procedure `procDeleteClub` */

DROP PROCEDURE IF EXISTS  `procDeleteClub`;

DELIMITER $$

CREATE PROCEDURE `procDeleteClub`(cid INT)
BEGIN
	    DELETE FROM `activities` WHERE `activities`.cid = cid;
	    DELETE FROM `members` WHERE `members`.cid = cid;
	    DELETE FROM `clubs` WHERE id = cid;
	END $$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
