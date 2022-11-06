CREATE DATABASE oopclubmanagement;
USE oopclubmanagement;

CREATE TABLE clubs(
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL
  -- members member[]
);

# noinspection NonAsciiCharacters

CREATE TABLE `members`(
  `mid` INT PRIMARY KEY AUTO_INCREMENT,
  `sid` INT(20) NOT NULL,
  `cid` INT NOT NULL,
  `职位` VARCHAR(6), -- nullable
  FOREIGN KEY(cid) REFERENCES clubs(id)
);

CREATE TABLE `activities` (
  aid INT PRIMARY KEY AUTO_INCREMENT,
  location VARCHAR(20) NOT NULL,
  cid INT NOT NULL,
  timestart DATETIME,
  timeend DATETIME,
  FOREIGN KEY(cid) REFERENCES clubs(id)
)

DELIMITER $$
CREATE OR REPLACE TRIGGER `TriggerDeleteClub` BEFORE DELETE
ON clubs FOR EACH ROW
BEGIN
  DELETE FROM `activities` WHERE `activities`.cid = old.id;
  DELETE FROM `members` WHERE `members`.cid = old.id;
  # delete from `clubs` where id = cid;
END
$$
DELIMITER ;