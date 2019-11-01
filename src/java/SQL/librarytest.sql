-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2018 at 08:37 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarytest`
--
drop database if exists librarytest;
create database if not exists librarytest;
-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `ISBN` int(13) NOT NULL,
  `title` varchar(128) NOT NULL,
  `bookLanguage` varchar(20) NOT NULL,
  `yearOfPublication` int(4) NOT NULL,
  `author` varchar(20) NOT NULL,
  `availableNoOfCopies` int(2) NOT NULL,
  `imageName` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ISBN`, `title`, `bookLanguage`, `yearOfPublication`, `author`, `availableNoOfCopies`, `imageName`) VALUES
(235461256, 'Mr. Magic', 'Lithuanian', 2018, 'A. Senkus', 52, 'mrMagic.jpg'),
(978031600, 'Consider Phlebas', 'english', 1986, 'Iain m.Banks', 15, 'ConsiderPhlebas.jpg'),
(978034581, '12 Rules for Life', 'english', 2018, 'Jordan B. Peterson', 27, '12RulesForLife.jpg'),
(978045145, 'Raft', 'english', 1900, 'Stephen Baxter', 26, 'raft.jpg'),
(978055358, 'A Dance with Dragons :A Song of Ice and Fire', 'english', 2013, 'George R. R. Martin', 29, 'songIceFire.jpg'),
(978055359, 'A Game of Thrones :A Song of Ice and Fire, Book 1', 'english', 2011, 'George R. R. Martin', 29, 'SongIceFireBook1.jpg'),
(978081251, 'The Eye of the World : The Wheel of Time, Book 1', 'english', 1990, 'Robert Jordan', 30, 'wheelOfTime.jpg'),
(978147321, 'Xeelee: Vengeance', 'english', 2018, 'Stephen Baxter', 35, 'xeelee.jpg'),
(978150117, 'It: A Novel', 'english', 2017, 'steven king', 26, 'it.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `loanID` int(11) NOT NULL,
  `bookTitle` varchar(50) NOT NULL,
  `username` varchar(12) NOT NULL,
  `issueDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `returnDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `loanStatus` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`loanID`, `bookTitle`, `username`, `issueDate`, `returnDate`, `loanStatus`) VALUES
(17, 'Mr. Magic', 'senkutis123', '2018-12-09 16:02:04', '2018-12-09 16:47:02', 1),
(18, 'A Dance with Dragons :A Song of Ice and Fire', 'testUser', '2018-12-09 16:09:03', '2018-12-09 16:10:09', 1),
(19, 'It: A Novel', 'testUser', '2018-12-09 16:09:06', NULL, 0),
(20, 'Mr. Magic', 'testUser', '2018-12-09 16:09:10', '2018-12-09 16:10:00', 1),
(21, 'Consider Phlebas', 'testUser', '2018-12-09 16:09:14', NULL, 0),
(22, '12 Rules for Life', 'testUser', '2018-12-09 16:09:19', '2018-12-09 16:09:44', 1),
(23, 'Raft', 'testUser', '2018-12-09 16:09:24', '2018-12-09 16:10:04', 1);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `reviewID` int(11) NOT NULL,
  `review` varchar(350) NOT NULL,
  `username` varchar(30) NOT NULL,
  `title` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`reviewID`, `review`, `username`, `title`) VALUES
(1, 'Best book', 'senkutis123', 'Mr. Magic');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `firstName`, `lastName`, `isAdmin`) VALUES
('ahmedkhan', '5f4dcc3b5aa765d61d8327deb882cf99', 'Ahmed', 'Khan', 0),
('senkutis123', '26b637ed41273425be243e8d42e5b461', 'audrius', 'senkus', 0),
('testUser', '81dc9bdb52d04dc20036dbd8313ed055', 'test', 'user', 0),
('Tom', '5f4dcc3b5aa765d61d8327deb882cf99', 'Tom', 'Hanratty', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`ISBN`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`loanID`),
  ADD KEY `username` (`username`),
  ADD KEY `bookTitle` (`bookTitle`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`reviewID`),
  ADD KEY `username` (`username`,`title`),
  ADD KEY `title` (`title`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `loanID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `reviewID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `loan_ibfk_2` FOREIGN KEY (`bookTitle`) REFERENCES `book` (`title`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`title`) REFERENCES `book` (`title`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
