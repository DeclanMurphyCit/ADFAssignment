-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2014 at 06:48 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `app_dev_assignment1`
--

-- --------------------------------------------------------

--
-- Table structure for table `deferral`
--

CREATE TABLE IF NOT EXISTS `deferral` (
`id_deferral` int(11) NOT NULL,
  `deferral_date` date NOT NULL,
  `id_program` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `program_deferred` tinyint(1) DEFAULT NULL,
  `id_deferral_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `deferred_modules`
--

CREATE TABLE IF NOT EXISTS `deferred_modules` (
`id_modules_deferred` int(11) NOT NULL,
  `id_module` int(11) NOT NULL,
  `id_deferral` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `defferal_status_types`
--

CREATE TABLE IF NOT EXISTS `defferal_status_types` (
  `id_deferral_status` int(11) NOT NULL,
  `defferal_status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE IF NOT EXISTS `lecturer` (
`id_lecturer` int(11) NOT NULL,
  `roomNumber` varchar(5) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `firstName` varchar(40) DEFAULT NULL,
  `lastName` varchar(40) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `idManagedProgram` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`id_lecturer`, `roomNumber`, `email`, `firstName`, `lastName`, `phoneNumber`, `idManagedProgram`) VALUES
(1, 'B180A', 'donna.oshea@cit.ie', 'Donna', 'OShea', '0123456789', NULL),
(2, 'B180A', 'ted.scully@cit.ie', 'Ted', 'Scully', '0123456789', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `lecturer_teaches_module`
--

CREATE TABLE IF NOT EXISTS `lecturer_teaches_module` (
  `idLecturer` int(11) NOT NULL,
  `idModule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lecturer_teaches_module`
--

INSERT INTO `lecturer_teaches_module` (`idLecturer`, `idModule`) VALUES
(2, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE IF NOT EXISTS `module` (
`id_module` int(11) NOT NULL,
  `code` varchar(9) DEFAULT NULL,
  `crn` varchar(6) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id_module`, `code`, `crn`, `name`, `semester`) VALUES
(1, NULL, NULL, NULL, NULL),
(2, NULL, NULL, NULL, NULL),
(3, NULL, NULL, NULL, NULL),
(4, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `program`
--

CREATE TABLE IF NOT EXISTS `program` (
`id_program` int(11) NOT NULL,
  `program_name` varchar(30) DEFAULT NULL,
  `year_number` mediumint(9) DEFAULT NULL,
  `program_code` varchar(10) DEFAULT NULL,
  `deferral_id_deferral` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=175 ;

--
-- Dumping data for table `program`
--

INSERT INTO `program` (`id_program`, `program_name`, `year_number`, `program_code`, `deferral_id_deferral`) VALUES
(174, 'DCOM4', NULL, 'DCOM4', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `program_has_semseters`
--

CREATE TABLE IF NOT EXISTS `program_has_semseters` (
  `id_program` int(11) NOT NULL,
  `semseter` int(11) DEFAULT NULL,
  `id_module` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
`id_student` int(11) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `studentNumber` varchar(10) DEFAULT NULL,
  `addressLine1` varchar(30) DEFAULT NULL,
  `addressLine2` varchar(30) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id_student`, `firstName`, `lastName`, `studentNumber`, `addressLine1`, `addressLine2`, `email`, `phoneNumber`) VALUES
(1, 'Simon', 'Casey', 'R00058441', 'Wilton', 'Cork', 'simon.casey@mycit.ie', '098765433'),
(2, 'Mary', 'Casey', 'R00058442', 'Bishopstown', 'Cork', 'mary.casey@mycit.ie', '5436356432');

-- --------------------------------------------------------

--
-- Table structure for table `student_enrolls_for`
--

CREATE TABLE IF NOT EXISTS `student_enrolls_for` (
  `idModule` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL,
  `enroment_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student_enrolls_for`
--

INSERT INTO `student_enrolls_for` (`idModule`, `idStudent`, `enroment_date`) VALUES
(3, 1, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deferral`
--
ALTER TABLE `deferral`
 ADD PRIMARY KEY (`id_deferral`), ADD KEY `fk_defferal_student1_idx` (`id_student`), ADD KEY `fk_deferral_defferal_status1_idx` (`id_deferral_status`);

--
-- Indexes for table `deferred_modules`
--
ALTER TABLE `deferred_modules`
 ADD PRIMARY KEY (`id_modules_deferred`), ADD KEY `fk_defferal_has_module_module1_idx` (`id_module`), ADD KEY `fk_deferred_modules_deferral1_idx` (`id_deferral`);

--
-- Indexes for table `defferal_status_types`
--
ALTER TABLE `defferal_status_types`
 ADD PRIMARY KEY (`id_deferral_status`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
 ADD PRIMARY KEY (`id_lecturer`), ADD KEY `fk_lecturer_is_programManager` (`idManagedProgram`);

--
-- Indexes for table `lecturer_teaches_module`
--
ALTER TABLE `lecturer_teaches_module`
 ADD PRIMARY KEY (`idLecturer`,`idModule`), ADD KEY `fk_lecturer_has_module_module1_idx` (`idModule`), ADD KEY `fk_lecturer_has_module_lecturer1_idx` (`idLecturer`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
 ADD PRIMARY KEY (`id_module`);

--
-- Indexes for table `program`
--
ALTER TABLE `program`
 ADD PRIMARY KEY (`id_program`), ADD KEY `fk_program_deferral1_idx` (`deferral_id_deferral`);

--
-- Indexes for table `program_has_semseters`
--
ALTER TABLE `program_has_semseters`
 ADD PRIMARY KEY (`id_program`), ADD KEY `fk_program_has_module_program1_idx` (`id_program`), ADD KEY `fk_program_has_semseters_module1_idx` (`id_module`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
 ADD PRIMARY KEY (`id_student`);

--
-- Indexes for table `student_enrolls_for`
--
ALTER TABLE `student_enrolls_for`
 ADD PRIMARY KEY (`idModule`,`idStudent`), ADD KEY `fk_module_has_student_student1_idx` (`idStudent`), ADD KEY `fk_module_has_student_module_idx` (`idModule`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `deferral`
--
ALTER TABLE `deferral`
MODIFY `id_deferral` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `deferred_modules`
--
ALTER TABLE `deferred_modules`
MODIFY `id_modules_deferred` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `lecturer`
--
ALTER TABLE `lecturer`
MODIFY `id_lecturer` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
MODIFY `id_module` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `program`
--
ALTER TABLE `program`
MODIFY `id_program` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=175;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
MODIFY `id_student` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `deferral`
--
ALTER TABLE `deferral`
ADD CONSTRAINT `fk_deferral_defferal_status2` FOREIGN KEY (`id_deferral_status`) REFERENCES `defferal_status_types` (`id_deferral_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_defferal_student2` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `deferred_modules`
--
ALTER TABLE `deferred_modules`
ADD CONSTRAINT `fk_deferred_modules_deferral4` FOREIGN KEY (`id_deferral`) REFERENCES `deferral` (`id_deferral`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_defferal_has_module_module2` FOREIGN KEY (`id_module`) REFERENCES `module` (`id_module`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `lecturer`
--
ALTER TABLE `lecturer`
ADD CONSTRAINT `fk_lecturer_is_programManager` FOREIGN KEY (`idManagedProgram`) REFERENCES `program` (`id_program`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `lecturer_teaches_module`
--
ALTER TABLE `lecturer_teaches_module`
ADD CONSTRAINT `fk_lecturer_has_module_lecturer1` FOREIGN KEY (`idLecturer`) REFERENCES `lecturer` (`id_lecturer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_lecturer_has_module_module1` FOREIGN KEY (`idModule`) REFERENCES `module` (`id_module`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `program`
--
ALTER TABLE `program`
ADD CONSTRAINT `fk_program_deferral1` FOREIGN KEY (`deferral_id_deferral`) REFERENCES `deferral` (`id_deferral`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `program_has_semseters`
--
ALTER TABLE `program_has_semseters`
ADD CONSTRAINT `fk_program_has_module_program1` FOREIGN KEY (`id_program`) REFERENCES `program` (`id_program`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_program_has_semseters_module1` FOREIGN KEY (`id_module`) REFERENCES `module` (`id_module`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
