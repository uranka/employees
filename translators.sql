-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2017 at 01:13 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `translators`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE IF NOT EXISTS `employees` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `sex` char(1) NOT NULL,
  `degree` varchar(20) DEFAULT NULL,
  `picture` blob,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `first_name`, `last_name`, `sex`, `degree`, `picture`) VALUES
(1, 'Carlos', 'Santana', 'm', 'PhD', NULL),
(2, 'Carla', 'Bruni', 'f', 'PhD', NULL),
(3, 'Marconi', 'Mayer', 'm', NULL, NULL),
(4, 'Stella', 'Pioni', 'f', NULL, NULL),
(10, 'Marko', 'Markov', 'm', 'PhD', NULL),
(11, 'Jelena', 'Gavanski', 'f', 'Master', NULL),
(100, 'Trta', 'Mrta', 'm', 'Master', NULL),
(101, 'Trta', 'Trtic', 'm', 'Bachelor', NULL),
(200, 'Teodor', 'Ruzvelt', 'm', 'PhD', NULL),
(201, 'George', 'Bush', 'm', 'Bachelor', NULL),
(300, 'Angela', 'Merkel', 'f', 'PhD', NULL),
(302, 'James', 'Maxwell', 'm', 'PhD', NULL),
(400, 'Milan', 'Milanovic', 'm', 'Bachelor', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employees_languages`
--

CREATE TABLE IF NOT EXISTS `employees_languages` (
  `employee_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`,`language_id`),
  KEY `ele_fk2` (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees_languages`
--

INSERT INTO `employees_languages` (`employee_id`, `language_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(200, 1),
(201, 1),
(300, 1),
(302, 1),
(400, 1),
(1, 2),
(200, 2),
(300, 2),
(302, 2),
(2, 3),
(200, 3),
(201, 3),
(400, 3);

-- --------------------------------------------------------

--
-- Table structure for table `languages`
--

CREATE TABLE IF NOT EXISTS `languages` (
  `language_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `languages`
--

INSERT INTO `languages` (`language_id`, `name`) VALUES
(1, 'English'),
(2, 'German'),
(3, 'Italian');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employees_languages`
--
ALTER TABLE `employees_languages`
  ADD CONSTRAINT `ele_fk1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  ADD CONSTRAINT `ele_fk2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
