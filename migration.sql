CREATE DATABASE oopclubmanagement;
USE oopclubmanagement;

CREATE TABLE clubs(
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  foundDate DATE NOT NULL
  -- members member[]
);

# noinspection NonAsciiCharacters

CREATE TABLE `members`(
  `mid` INT PRIMARY KEY AUTO_INCREMENT,
  `sid` INT(20) NOT NULL,
  `cid` INT NOT NULL,
  `position` VARCHAR(6), -- nullable
  FOREIGN KEY(cid) REFERENCES clubs(id)
);

CREATE TABLE `activities` (
  aid INT PRIMARY KEY AUTO_INCREMENT,
  location VARCHAR(20) NOT NULL,
  cid INT NOT NULL,
  timestart DATETIME,
  timeend DATETIME,
  FOREIGN KEY(cid) REFERENCES clubs(id)
);

-- Spring Security
create table users
(
    username VARCHAR(50) not null primary key,
    password VARCHAR(500) not null,
    enabled boolean not null
);
create table authorities
(
    username VARCHAR(50) not null,
    authority VARCHAR(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

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