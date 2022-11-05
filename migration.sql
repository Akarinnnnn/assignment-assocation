CREATE DATABASE oopclubmanagememt;
USE oopclubmanagememt;

CREATE TABLE clubs(
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL
  -- members member[]
);

CREATE TABLE students(
  id INT(20) PRIMARY KEY,
  `name` VARCHAR(5)
);

# noinspection NonAsciiCharacters

CREATE TABLE `members`(
  `mid` INT PRIMARY KEY,
  `sid` INT(20) NOT NULL,
  `cid` INT NOT NULL,
  `职位` VARCHAR(6), -- nullable
  FOREIGN KEY(cid) REFERENCES clubs(id),
  FOREIGN KEY(sid) REFERENCES students(id)
);
