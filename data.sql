/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.6.7-MariaDB : Database - oopclubmanagement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oopclubmanagement` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `oopclubmanagement`;

/*Data for the table `activities` */

insert  into `activities`(`aid`,`name`,`location`,`cid`,`timestart`,`timeend`) values 
(1,'活动11','201',1,'2022-11-16 19:31:00','2022-11-16 19:40:00'),
(2,'新干事培训','403',2,'2022-11-24 09:34:25','2022-11-30 09:34:29'),
(5,'新人培训','605',1,'2022-11-17 20:50:00','2022-11-16 21:10:00'),
(2234,'新部员培训','401',1,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2235,'新部员培训','402',2,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2236,'新部员培训','403',3,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2237,'新部员培训','404',4,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2238,'新部员培训','405',5,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2239,'新部员培训','406',6,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2240,'新部员培训','407',7,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2241,'新部员培训','501',8,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2242,'新部员培训','502',9,'2022-11-23 14:18:19','2022-11-23 16:18:19'),
(2243,'新部员培训','503',10,'2022-11-23 14:18:19','2022-11-23 16:18:19');

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values 
('user','ROLE_user'),
('lisi','ROLE_user'),
('zhangsan','ROLE_user'),
('admin22','ROLE_admin'),
('admin33','ROLE_admin'),
('liu6','ROLE_user'),
('wangwu','ROLE_user'),
('zhang4','ROLE_user'),
('user3','ROLE_user'),
('s1mple','ROLE_user'),
('zhang3','ROLE_user'),
('li4','ROLE_user'),
('wang5','ROLE_user'),
('22','ROLE_user'),
('33','ROLE_user');

/*Data for the table `clubs` */

insert  into `clubs`(`id`,`name`,`foundDate`) values 
(1,'1','2022-11-08'),
(2,'第二个社团','2022-11-10'),
(3,'tx123','2022-11-09'),
(4,'计算机应用协会','2022-11-07'),
(5,'爱乐团','2022-11-09'),
(6,'社团6','2022-11-24'),
(7,'社团7','2022-11-24'),
(8,'社团8','2022-11-24'),
(9,'社团9','2022-11-24'),
(10,'社团10','2022-11-25');

/*Data for the table `deletelog` */

insert  into `deletelog`(`id`,`tablename`,`timepoint`,`itemId`) values 
(1,'activities','2022-11-23 14:31:41',2233),
(2,'activities','2022-11-23 14:33:54',2233),
(3,'activities','2022-11-23 14:33:55',2233),
(4,'activities','2022-11-23 14:33:55',2233),
(5,'activities','2022-11-23 14:33:56',2233),
(6,'members','2022-11-23 14:34:01',2233),
(7,'members','2022-11-23 14:34:02',2233),
(8,'members','2022-11-23 14:34:02',2233),
(9,'members','2022-11-23 14:34:05',2233),
(10,'clubs','2022-11-23 14:35:54',2233),
(11,'clubs','2022-11-23 14:36:27',2),
(12,'clubs','2022-11-23 14:36:27',3),
(13,'clubs','2022-11-23 14:36:27',4),
(14,'clubs','2022-11-23 14:36:27',5);

/*Data for the table `members` */

insert  into `members`(`mid`,`sid`,`cid`,`position`) values 
(1,0,1,'副部长'),
(2,1,2,'高级干事'),
(3,2,2,'部长'),
(4,4,3,'部长'),
(5,3,4,'部长'),
(10,3,2,'干事'),
(11,5,5,'部长'),
(12,6,2,'干事'),
(13,20200101,3,'干事'),
(14,20210201,4,'副部长');

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values 
('admin22','987654721.33',0),
('admin33','987654721.33',0),
('lisi','123456',1),
('liu6','654321',1),
('user','$2a$10$32kp183UrlaXQKMTOxGh3OlJs2tNqv75SKiDwUrzBHlCnEso4QuXS',1),
('user1','987654721.33',1),
('user2','123456',1),
('wangwu','123456',1),
('zhang4','654321',1),
('zhangsan','123456',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;