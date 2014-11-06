-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema app_dev_assignment1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema app_dev_assignment1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `app_dev_assignment1` DEFAULT CHARACTER SET utf8 ;
USE `app_dev_assignment1` ;

-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`program` (
  `id_program` INT NOT NULL,
  `program_name` VARCHAR(30) NULL DEFAULT NULL,
  `year_number` MEDIUMINT(9) NULL DEFAULT NULL,
  `program_code` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id_program`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`lecturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`lecturer` (
  `id_lecturer` INT NOT NULL,
  `roomNumber` VARCHAR(5) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(40) NULL DEFAULT NULL,
  `lastName` VARCHAR(40) NULL DEFAULT NULL,
  `phoneNumber` VARCHAR(15) NULL DEFAULT NULL,
  `idManagedProgram` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_lecturer`),
  INDEX `fk_lecturer_is_programManager` (`idManagedProgram` ASC),
  CONSTRAINT `fk_lecturer_is_programManager`
    FOREIGN KEY (`idManagedProgram`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`student` (
  `id_student` INT NOT NULL,
  `firstName` VARCHAR(20) NULL DEFAULT NULL,
  `lastName` VARCHAR(30) NULL DEFAULT NULL,
  `studentNumber` VARCHAR(10) NULL DEFAULT NULL,
  `addressLine1` VARCHAR(30) NULL DEFAULT NULL,
  `addressLine2` VARCHAR(30) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phoneNumber` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`id_student`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`module` (
  `id_module` INT NOT NULL,
  `code` VARCHAR(9) NULL DEFAULT NULL,
  `crn` VARCHAR(6) NULL DEFAULT NULL,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `semester` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_module`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`student_enrolls_for`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`student_enrolls_for` (
  `idModule` INT NOT NULL,
  `idStudent` INT NOT NULL,
  `enroment_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idModule`, `idStudent`),
  INDEX `fk_module_has_student_student1_idx` (`idStudent` ASC),
  INDEX `fk_module_has_student_module_idx` (`idModule` ASC),
  CONSTRAINT `fk_module_has_student_module`
    FOREIGN KEY (`idModule`)
    REFERENCES `app_dev_assignment1`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_module_has_student_student1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `app_dev_assignment1`.`student` (`id_student`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`lecturer_teaches_module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`lecturer_teaches_module` (
  `idLecturer` INT NOT NULL,
  `idModule` INT NOT NULL,
  PRIMARY KEY (`idLecturer`, `idModule`),
  INDEX `fk_lecturer_has_module_module1_idx` (`idModule` ASC),
  INDEX `fk_lecturer_has_module_lecturer1_idx` (`idLecturer` ASC),
  CONSTRAINT `fk_lecturer_has_module_lecturer1`
    FOREIGN KEY (`idLecturer`)
    REFERENCES `app_dev_assignment1`.`lecturer` (`id_lecturer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lecturer_has_module_module1`
    FOREIGN KEY (`idModule`)
    REFERENCES `app_dev_assignment1`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`defferal_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`defferal_status` (
  `id_deferral_status` INT NOT NULL,
  `defferal_status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_deferral_status`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`deferral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`deferral` (
  `id_deferral` INT NOT NULL,
  `deferral_date` DATE NOT NULL,
  `id_program` INT NOT NULL,
  `id_student` INT NOT NULL,
  `program_deferred` TINYINT(1) NULL,
  `defferal_status_id_deferral_status` INT NOT NULL,
  PRIMARY KEY (`id_deferral`, `id_program`, `id_student`),
  INDEX `fk_defferal_program1_idx` (`id_program` ASC),
  INDEX `fk_defferal_student1_idx` (`id_student` ASC),
  INDEX `fk_deferral_defferal_status1_idx` (`defferal_status_id_deferral_status` ASC),
  CONSTRAINT `fk_defferal_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_defferal_student1`
    FOREIGN KEY (`id_student`)
    REFERENCES `app_dev_assignment1`.`student` (`id_student`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_deferral_defferal_status1`
    FOREIGN KEY (`defferal_status_id_deferral_status`)
    REFERENCES `app_dev_assignment1`.`defferal_status` (`id_deferral_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`program_has_semseters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`program_has_semseters` (
  `id_program` INT NOT NULL,
  `semseter` INT NULL DEFAULT NULL,
  `id_module` INT NOT NULL,
  PRIMARY KEY (`id_program`),
  INDEX `fk_program_has_module_program1_idx` (`id_program` ASC),
  INDEX `fk_program_has_semseters_module1_idx` (`id_module` ASC),
  CONSTRAINT `fk_program_has_module_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_program_has_semseters_module1`
    FOREIGN KEY (`id_module`)
    REFERENCES `app_dev_assignment1`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`deferred_modules`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`deferred_modules` (
  `id_defferal` INT NOT NULL,
  `id_module` INT NOT NULL,
  PRIMARY KEY (`id_defferal`, `id_module`),
  INDEX `fk_defferal_has_module_module1_idx` (`id_module` ASC),
  INDEX `fk_defferal_has_module_defferal1_idx` (`id_defferal` ASC),
  CONSTRAINT `fk_defferal_has_module_defferal1`
    FOREIGN KEY (`id_defferal`)
    REFERENCES `app_dev_assignment1`.`deferral` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_defferal_has_module_module1`
    FOREIGN KEY (`id_module`)
    REFERENCES `app_dev_assignment1`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
