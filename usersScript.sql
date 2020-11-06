-- -----------------------------------------------------
-- Schema datingapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `datingapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `datingapp` ;

-- -----------------------------------------------------
-- Table `datingapp`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datingapp`.`users` (
  `userid` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NULL DEFAULT 'datinguser',
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)


-- -----------------------------------------------------
-- Table `datingapp`.`datingusers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datingapp`.`datingusers` (
  `userid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `birthdate` DATE NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `gender` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE,
  CONSTRAINT `userid`
    FOREIGN KEY (`userid`)
    REFERENCES `datingapp`.`users` (`userid`))

    AUTO_INCREMENT = 2
