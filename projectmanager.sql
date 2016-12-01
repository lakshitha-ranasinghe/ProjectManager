-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 01, 2016 at 08:53 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `projectmanager`
--
CREATE DATABASE IF NOT EXISTS `projectmanager` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `projectmanager`;

-- --------------------------------------------------------

--
-- Table structure for table `bug`
--

CREATE TABLE IF NOT EXISTS `bug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assignedDate` date NOT NULL,
  `closedDate` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `image` longblob,
  `releasedDate` date DEFAULT NULL,
  `resolvedDate` date DEFAULT NULL,
  `status` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `assignedTo_id` int(11) NOT NULL,
  `reportedBy_id` int(11) NOT NULL,
  `liveReleasedData` date DEFAULT NULL,
  `pageId` int(11) DEFAULT NULL,
  `page_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n326qj5yw4x96c9pah6dghu2o` (`assignedTo_id`),
  KEY `FK_o3dvk0y71p9vdphjeiyi67eke` (`reportedBy_id`),
  KEY `FK_6y70jxqn3aa9l19iryxapckxi` (`page_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=107 ;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Table structure for table `mainarea`
--

CREATE TABLE IF NOT EXISTS `mainarea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaName` varchar(255) DEFAULT NULL,
  `menuType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

-- --------------------------------------------------------

--
-- Table structure for table `page`
--

CREATE TABLE IF NOT EXISTS `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pageName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `stickynote`
--

CREATE TABLE IF NOT EXISTS `stickynote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `createdBy_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b7qe2ee6ssu2u6tmf7jp1ll05` (`createdBy_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=74 ;

-- --------------------------------------------------------

--
-- Table structure for table `systemrelease`
--

CREATE TABLE IF NOT EXISTS `systemrelease` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bugFixes` int(11) DEFAULT NULL,
  `dateOfRelease` date DEFAULT NULL,
  `tasks` int(11) DEFAULT NULL,
  `releaseType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actualResolvedDate` date DEFAULT NULL,
  `assignedDate` date NOT NULL,
  `description` varchar(255) NOT NULL,
  `expectedResolveData` date DEFAULT NULL,
  `releasedDate` date DEFAULT NULL,
  `status` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `assignedBy_id` int(11) NOT NULL,
  `assignedTo_id` int(11) NOT NULL,
  `liveReleasedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jvh1ymhokyr1hsreuq0nvh061` (`assignedBy_id`),
  KEY `FK_iq9sq5qglnl2xnphtx7svbk3g` (`assignedTo_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bug`
--
ALTER TABLE `bug`
  ADD CONSTRAINT `FK_6y70jxqn3aa9l19iryxapckxi` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`),
  ADD CONSTRAINT `FK_n326qj5yw4x96c9pah6dghu2o` FOREIGN KEY (`assignedTo_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FK_o3dvk0y71p9vdphjeiyi67eke` FOREIGN KEY (`reportedBy_id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `stickynote`
--
ALTER TABLE `stickynote`
  ADD CONSTRAINT `FK_b7qe2ee6ssu2u6tmf7jp1ll05` FOREIGN KEY (`createdBy_id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `FK_iq9sq5qglnl2xnphtx7svbk3g` FOREIGN KEY (`assignedTo_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FK_jvh1ymhokyr1hsreuq0nvh061` FOREIGN KEY (`assignedBy_id`) REFERENCES `employee` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
