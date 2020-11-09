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
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `birthdate` DATE NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `gender` VARCHAR(200) NULL DEFAULT NULL,
  `picture` INT NULL DEFAULT NULL,
  `role` VARCHAR(45) NULL DEFAULT 'datinguser',
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
-- -----------------------------------------------------
-- Table `datingapp`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `datingapp`.`messages` (
  `messageid` INT NOT NULL AUTO_INCREMENT,
  `userid` INT NOT NULL,
  `receiveid` INT NOT NULL,
  `message` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`messageid`),
  UNIQUE INDEX `messageid_UNIQUE` (`messageid` ASC) VISIBLE,
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE,
  INDEX `receiveid_idx` (`receiveid` ASC) VISIBLE,
  CONSTRAINT `receiveid`
    FOREIGN KEY (`receiveid`)
    REFERENCES `datingapp`.`users` (`userid`),
  CONSTRAINT `userid`
    FOREIGN KEY (`userid`)
    REFERENCES `datingapp`.`users` (`userid`))
