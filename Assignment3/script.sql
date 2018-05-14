-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema asgn3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema asgn3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `asgn3` DEFAULT CHARACTER SET latin1 ;
USE `asgn3` ;

-- -----------------------------------------------------
-- Table `asgn3`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asgn3`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `admin` BIT(1) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `asgn3`.`articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asgn3`.`articles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `abstracta` VARCHAR(255) NULL DEFAULT NULL,
  `body` VARCHAR(2000) NULL DEFAULT NULL,
  `image` VARCHAR(2000) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `author_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_994ti04cleavu1u78cg2tqiip` (`author_id` ASC),
  CONSTRAINT `FK_994ti04cleavu1u78cg2tqiip`
    FOREIGN KEY (`author_id`)
    REFERENCES `asgn3`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `asgn3`.`articles_articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asgn3`.`articles_articles` (
  `articles_id` INT(11) NOT NULL,
  `related_id` INT(11) NOT NULL,
  PRIMARY KEY (`articles_id`, `related_id`),
  INDEX `FK_dvpxqf5oeo4ls7g7pe3rbbw41` (`related_id` ASC),
  CONSTRAINT `FK_dvpxqf5oeo4ls7g7pe3rbbw41`
    FOREIGN KEY (`related_id`)
    REFERENCES `asgn3`.`articles` (`id`),
  CONSTRAINT `FK_fwd8kih7i9gh9s729npv0qx5c`
    FOREIGN KEY (`articles_id`)
    REFERENCES `asgn3`.`articles` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
