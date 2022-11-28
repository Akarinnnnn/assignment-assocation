-- 记录clubs、members、activities表的删除操作，用于日后审核是否存在误操作
CREATE TABLE IF NOT EXISTS `deletelog` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tablename VARCHAR(15) NOT NULL,
    itemid INT UNSIGNED NOT NULL,
    timepoint DATETIME NOT NULL,
    CONSTRAINT chkTlbName CHECK (tablename IN ('clubs','activities','members'))
) DEFAULT CHARSET=utf8mb4;
/*
ALTER TABLE `deletelog`
    ADD CONSTRAINT chkTlbName CHECK (tablename IN ('clubs','activities','members'));
ALTER TABLE `oopclubmanagement`.`deletelog` 
    ADD COLUMN `itemId` INT UNSIGNED NOT NULL AFTER `tablename`;
*/
CREATE OR REPLACE VIEW `oopclubmanagement`.`clubsDeleteLog` 
AS (SELECT * FROM deletelog WHERE `tablename` = 'clubs');

CREATE OR REPLACE VIEW `oopclubmanagement`.`membersDeleteLog` 
AS (SELECT * FROM deletelog WHERE `tablename` = 'members');

CREATE OR REPLACE VIEW `oopclubmanagement`.`activitiesDeleteLog` 
AS (SELECT * FROM deletelog WHERE `tablename` = 'activities');

-- ------触发器------ --

-- 在删除社团时，往删除日志表插入一行记录
DROP TRIGGER IF EXISTS onDeleteClub;
DELIMITER $$

CREATE
    TRIGGER `oopclubmanagement`.`onDeleteClub` AFTER DELETE
    ON `oopclubmanagement`.`clubs`
    FOR EACH ROW BEGIN
        INSERT INTO `deletelog`(tablename, timepoint, itemid) VALUES ('clubs', NOW(), old.id);
    END$$
DELIMITER ;

-- 在删除社团成员记录时，往删除日志表插入一行记录
DROP TRIGGER IF EXISTS onDeleteMember;
DELIMITER $$    
CREATE TRIGGER `oopclubmanagement`.`onDeleteMember` AFTER DELETE
    ON `oopclubmanagement`.`members` 
    FOR EACH ROW BEGIN
        INSERT INTO `deletelog`(tablename, timepoint, itemid) VALUES ('members', NOW(), old.mid);
    END$$
DELIMITER ;

-- 在删除社团举办的活动信息时，往删除日志表插入一行记录
DROP TRIGGER IF EXISTS onDeleteActivity;
DELIMITER $$    
CREATE TRIGGER `oopclubmanagement`.`onDeleteActivity` AFTER DELETE
    ON `oopclubmanagement`.`activities` 
    FOR EACH ROW BEGIN
        INSERT INTO `deletelog`(tablename, timepoint, itemId) VALUES ('activities', NOW(), old.aid);
    END$$
DELIMITER ;

-- ------存储过程------ --

-- 删除社团记录
DROP PROCEDURE IF EXISTS  `procDeleteClub`;

DELIMITER $$
CREATE PROCEDURE `procDeleteClub`(cid INT)
BEGIN
	    DELETE FROM `activities` WHERE `activities`.cid = cid;
	    DELETE FROM `members` WHERE `members`.cid = cid;
	    DELETE FROM `clubs` WHERE id = cid;
	END $$
DELIMITER ;

-- 删除一个前台用户
DROP PROCEDURE IF EXISTS  `procDeleteUser`;
DELIMITER $$
CREATE PROCEDURE `procDeleteUser`(username VARCHAR(50))
BEGIN
    DELETE FROM `authorities` WHERE `authorities`.`username` = `username`;
    DELETE FROM `users` WHERE `users`.`username` = username;
END $$
DELIMITER ;

-- 新建一个前台用户
DROP PROCEDURE IF EXISTS  `procCreateUser`;
DELIMITER $$
CREATE PROCEDURE `procCreateUser`(username VARCHAR(50), `password` VARCHAR(500))
BEGIN
    INSERT INTO `users` VALUES (`username`, `password`, 1);
    INSERT INTO `authorities` VALUES (`username`, 'ROLE_user');
END $$
DELIMITER ;

