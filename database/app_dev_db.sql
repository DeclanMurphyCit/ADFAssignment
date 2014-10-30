-- MySQL Script generated by MySQL Workbench
-- Sun 26 Oct 2014 22:52:09 GMT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema app_dev_assignment1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema app_dev_assignment1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `app_dev_assignment1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `app_dev_assignment1` ;

-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`program` (
  `id_program` INT NOT NULL,
  `program_name` VARCHAR(30) NULL,
  `year_number` MEDIUMINT(9) NULL,
  `program_code` VARCHAR(10) NULL,
  PRIMARY KEY (`id_program`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`lecturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`lecturer` (
  `id_lecturer` INT NOT NULL,
  `lecurer_room` VARCHAR(5) NULL,
  `lecturer_email` VARCHAR(45) NOT NULL,
  `lecturer_name` VARCHAR(40) NULL,
  `lecturer_phonenumber` VARCHAR(15) NULL,
  PRIMARY KEY (`id_lecturer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`student` (
  `id_student` INT NOT NULL,
  `student_first_name` VARCHAR(20) NULL,
  `student_second_name` VARCHAR(30) NULL,
  `student_number` VARCHAR(10) NULL,
  `student_address_line1` VARCHAR(30) NULL,
  `student_address_line2` VARCHAR(30) NULL,
  `student_email` VARCHAR(45) NULL,
  `student_phone` VARCHAR(15) NULL,
  PRIMARY KEY (`id_student`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`module` (
  `id_module` INT NOT NULL,
  `code` VARCHAR(9) NULL,
  `crn` VARCHAR(6) NULL,
  `name` VARCHAR(50) NULL,
  `semester` INT NULL,
  PRIMARY KEY (`id_module`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`student_enrolls_for`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`student_enrolls_for` (
  `dModule` INT NOT NULL,
  `idStudent` INT NOT NULL,
  `enroment_date` DATE NULL,
  PRIMARY KEY (`dModule`, `idStudent`),
  INDEX `fk_module_has_student_student1_idx` (`idStudent` ASC),
  INDEX `fk_module_has_student_module_idx` (`dModule` ASC),
  CONSTRAINT `fk_module_has_student_module`
    FOREIGN KEY (`dModule`)
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
-- Table `app_dev_assignment1`.`program_coordinator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`program_coordinator` (
  `id_program_coordinator` INT NOT NULL,
  `id_program` INT NOT NULL,
  `lecturer_id_lecturer` INT NOT NULL,
  PRIMARY KEY (`id_program_coordinator`, `lecturer_id_lecturer`),
  INDEX `fk_program_coordinator_program1_idx` (`id_program` ASC),
  INDEX `fk_program_coordinator_lecturer1_idx` (`lecturer_id_lecturer` ASC),
  CONSTRAINT `fk_program_coordinator_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_program_coordinator_lecturer1`
    FOREIGN KEY (`lecturer_id_lecturer`)
    REFERENCES `app_dev_assignment1`.`lecturer` (`id_lecturer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`defferal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`defferal` (
  `id_defferal` INT NOT NULL,
  `defferal_date` DATE NOT NULL,
  `id_program` INT NOT NULL,
  `id_student` INT NOT NULL,
  PRIMARY KEY (`id_defferal`, `id_program`, `id_student`),
  INDEX `fk_defferal_program1_idx` (`id_program` ASC),
  INDEX `fk_defferal_student1_idx` (`id_student` ASC),
  CONSTRAINT `fk_defferal_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_defferal_student1`
    FOREIGN KEY (`id_student`)
    REFERENCES `app_dev_assignment1`.`student` (`id_student`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`program_has_semseters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`program_has_semseters` (
  `id_program` INT NOT NULL,
  `module_id_module` INT NOT NULL,
  `semseter` VARCHAR(45) NULL,
  PRIMARY KEY (`id_program`, `module_id_module`),
  INDEX `fk_program_has_module_module1_idx` (`module_id_module` ASC),
  INDEX `fk_program_has_module_program1_idx` (`id_program` ASC),
  CONSTRAINT `fk_program_has_module_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_program_has_module_module1`
    FOREIGN KEY (`module_id_module`)
    REFERENCES `app_dev_assignment1`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`deffered_modules`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`deffered_modules` (
  `id_deffered_modules` INT NOT NULL,
  `id_defferal` INT NOT NULL,
  `module_id_module` INT NOT NULL,
  PRIMARY KEY (`id_deffered_modules`, `id_defferal`, `module_id_module`),
  INDEX `fk_defferal_has_module_module1_idx` (`module_id_module` ASC),
  INDEX `fk_defferal_has_module_defferal1_idx` (`id_deffered_modules` ASC, `id_defferal` ASC),
  CONSTRAINT `fk_defferal_has_module_defferal1`
    FOREIGN KEY (`id_deffered_modules` , `id_defferal`)
    REFERENCES `app_dev_assignment1`.`defferal` (`id_defferal` , `id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_defferal_has_module_module1`
    FOREIGN KEY (`module_id_module`)
    REFERENCES `app_dev_assignment1`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- MySQL Script generated by MySQL Workbench
-- 10/28/14 19:10:12
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
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
  `lecturer_first_name` VARCHAR(20) NULL,
  `lecturer_second_name` VARCHAR(30) NULL,
  `lecurer_room` VARCHAR(5) NULL DEFAULT NULL,
  `lecturer_email` VARCHAR(45) NOT NULL,
  `lecturer_phonenumber` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`id_lecturer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`student` (
  `id_student` INT NOT NULL,
  `student_first_name` VARCHAR(20) NULL DEFAULT NULL,
  `student_second_name` VARCHAR(30) NULL DEFAULT NULL,
  `student_number` VARCHAR(10) NULL DEFAULT NULL,
  `student_address_line1` VARCHAR(30) NULL DEFAULT NULL,
  `student_address_line2` VARCHAR(30) NULL DEFAULT NULL,
  `student_email` VARCHAR(45) NULL DEFAULT NULL,
  `student_phone` VARCHAR(15) NULL DEFAULT NULL,
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
  `dModule` INT NOT NULL,
  `idStudent` INT NOT NULL,
  `enroment_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`dModule`, `idStudent`),
  INDEX `fk_module_has_student_student1_idx` (`idStudent` ASC),
  INDEX `fk_module_has_student_module_idx` (`dModule` ASC),
  CONSTRAINT `fk_module_has_student_module`
    FOREIGN KEY (`dModule`)
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
-- Table `app_dev_assignment1`.`program_coordinator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`program_coordinator` (
  `id_program_coordinator` INT NOT NULL,
  `id_program` INT NOT NULL,
  `lecturer_id_lecturer` INT NOT NULL,
  PRIMARY KEY (`id_program_coordinator`, `lecturer_id_lecturer`),
  INDEX `fk_program_coordinator_program1_idx` (`id_program` ASC),
  INDEX `fk_program_coordinator_lecturer1_idx` (`lecturer_id_lecturer` ASC),
  CONSTRAINT `fk_program_coordinator_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_program_coordinator_lecturer1`
    FOREIGN KEY (`lecturer_id_lecturer`)
    REFERENCES `app_dev_assignment1`.`lecturer` (`id_lecturer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`defferal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`defferal` (
  `id_defferal` INT NOT NULL,
  `defferal_date` DATE NOT NULL,
  `id_program` INT NOT NULL,
  `id_student` INT NOT NULL,
  PRIMARY KEY (`id_defferal`, `id_program`, `id_student`),
  INDEX `fk_defferal_program1_idx` (`id_program` ASC),
  INDEX `fk_defferal_student1_idx` (`id_student` ASC),
  CONSTRAINT `fk_defferal_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_defferal_student1`
    FOREIGN KEY (`id_student`)
    REFERENCES `app_dev_assignment1`.`student` (`id_student`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`program_has_semseters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`program_has_semseters` (
  `id_program` INT NOT NULL,
  `module_id_module` INT NOT NULL,
  `semseter` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_program`, `module_id_module`),
  INDEX `fk_program_has_module_module1_idx` (`module_id_module` ASC),
  INDEX `fk_program_has_module_program1_idx` (`id_program` ASC),
  CONSTRAINT `fk_program_has_module_program1`
    FOREIGN KEY (`id_program`)
    REFERENCES `app_dev_assignment1`.`program` (`id_program`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_program_has_module_module1`
    FOREIGN KEY (`module_id_module`)
    REFERENCES `app_dev_assignment1`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_dev_assignment1`.`deffered_modules`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_dev_assignment1`.`deffered_modules` (
  `id_deffered_modules` INT NOT NULL,
  `id_defferal` INT NOT NULL,
  `id_module` INT NOT NULL,
  PRIMARY KEY (`id_deffered_modules`, `id_defferal`, `id_module`),
  INDEX `fk_defferal_has_module_module1_idx` (`id_module` ASC),
  INDEX `fk_defferal_has_module_defferal1_idx` (`id_deffered_modules` ASC, `id_defferal` ASC),
  CONSTRAINT `fk_defferal_has_module_defferal1`
    FOREIGN KEY (`id_deffered_modules` , `id_defferal`)
    REFERENCES `app_dev_assignment1`.`defferal` (`id_defferal` , `id_program`)
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
