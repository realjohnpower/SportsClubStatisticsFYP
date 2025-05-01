-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2025 at 01:12 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sport_club_db`
--
DROP DATABASE IF EXISTS sport_club_db;

CREATE DATABASE sport_club_db;

USE sport_club_db;

-- --------------------------------------------------------

--
-- Table structure for table `club_events`
--

CREATE TABLE `club_events` (
  `club_event_id` int(11) NOT NULL,
  `club_event_name` varchar(255) NOT NULL,
  `club_event_description` blob NOT NULL,
  `start_date` datetime NOT NULL,
  `location` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `club_events`
--

INSERT INTO `club_events` (`club_event_id`, `club_event_name`, `club_event_description`, `start_date`, `location`) VALUES
(1, 'dinner dance', 0x44696e6e65722044616e636520617420436173746c65204f616b7320486f74656c206f6e207468652032326e64204f66204175677573742061742032303235, '2025-08-22 20:30:00', 'Castle Oaks Hotel'),
(3, 'Junior B Hurling Match', 0x4a756e696f722042206875726c696e67204d61746368, '2025-05-20 15:00:00', 'Thurles');

-- --------------------------------------------------------

--
-- Table structure for table `club_event_attendance`
--

CREATE TABLE `club_event_attendance` (
  `club_event_attendance_id` int(11) NOT NULL,
  `club_event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `attendance` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `club_event_attendance`
--

INSERT INTO `club_event_attendance` (`club_event_attendance_id`, `club_event_id`, `user_id`, `attendance`) VALUES
(1, 1, 1, 1),
(5, 3, 1, 1),
(6, 1, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `player_physical_stats`
--

CREATE TABLE `player_physical_stats` (
  `player_physical_stats_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `weight_kg` double NOT NULL,
  `height_m` double NOT NULL,
  `fat_percentage` double NOT NULL,
  `bmi` double NOT NULL,
  `date_recorded` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `player_physical_stats`
--

INSERT INTO `player_physical_stats` (`player_physical_stats_id`, `user_id`, `weight_kg`, `height_m`, `fat_percentage`, `bmi`, `date_recorded`) VALUES
(17, 42, 93, 1.6, 20, 37.10937499999999, '2024-07-29'),
(18, 42, 95, 1.7, 23, 32.871972318339104, '2024-10-29'),
(19, 42, 100, 1.75, 20, 32.6530612244898, '2025-01-29');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'TRAINER'),
(3, 'PLAYER'),
(4, 'CLUB MEMBER');

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `team_id` int(11) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`team_id`, `team_name`, `user_id`) VALUES
(2, 'Junior B hurling team ', 3),
(4, 'Intermediate Hurling Team', 3),
(6, 'Senior Hurling Team', 3),
(7, 'Junior C Hurling Team', 3);

-- --------------------------------------------------------

--
-- Table structure for table `team_events`
--

CREATE TABLE `team_events` (
  `team_event_id` int(11) NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `event_description` varchar(255) NOT NULL,
  `start_date` datetime NOT NULL,
  `location` varchar(255) NOT NULL,
  `team_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `team_events`
--

INSERT INTO `team_events` (`team_event_id`, `event_name`, `event_description`, `start_date`, `location`, `team_id`) VALUES
(9, 'Gym Session', 'Gym session, bring runners', '2025-05-20 20:00:00', 'Club Gym', 6),
(10, 'Hurling training', 'Hurling Training for the final', '2025-05-20 19:00:00', 'Local Pitch', 2);

-- --------------------------------------------------------

--
-- Table structure for table `team_event_attendance`
--

CREATE TABLE `team_event_attendance` (
  `team_event_attendance_id` int(11) NOT NULL,
  `team_event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `attendance` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `team_event_attendance`
--

INSERT INTO `team_event_attendance` (`team_event_attendance_id`, `team_event_id`, `user_id`, `attendance`) VALUES
(4, 10, 42, 0),
(5, 9, 42, 1);

-- --------------------------------------------------------

--
-- Table structure for table `team_members`
--

CREATE TABLE `team_members` (
  `team_member_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `team_members`
--

INSERT INTO `team_members` (`team_member_id`, `team_id`, `user_id`) VALUES
(6, 4, 7),
(9, 4, 9),
(13, 2, 7),
(14, 6, 56),
(15, 6, 47),
(16, 6, 53),
(17, 6, 46),
(18, 6, 49),
(19, 6, 54),
(20, 6, 42),
(21, 6, 45),
(22, 6, 43),
(23, 6, 44),
(24, 6, 52),
(25, 6, 55),
(26, 6, 50),
(27, 6, 48),
(28, 6, 51),
(31, 2, 9),
(32, 2, 43),
(33, 2, 42),
(34, 2, 44),
(35, 7, 52),
(36, 7, 46),
(37, 7, 48),
(38, 7, 51),
(39, 7, 53),
(40, 7, 47),
(41, 7, 54),
(42, 7, 49),
(43, 7, 55);

-- --------------------------------------------------------

--
-- Table structure for table `team_session_stats`
--

CREATE TABLE `team_session_stats` (
  `team_session_stats_id` int(11) NOT NULL,
  `team_event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `resting_bpm` double NOT NULL,
  `avg_bpm` double NOT NULL,
  `max_bpm` double NOT NULL,
  `calories_burned` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `team_session_stats`
--

INSERT INTO `team_session_stats` (`team_session_stats_id`, `team_event_id`, `user_id`, `resting_bpm`, `avg_bpm`, `max_bpm`, `calories_burned`) VALUES
(44, 10, 7, 176, 174, 187, 205),
(45, 10, 9, 167, 186, 195, 267);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `date_registered` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `email`, `password`, `gender`, `date_of_birth`, `date_registered`) VALUES
(1, 'John', 'Power', 'john@gmail.com', '$2a$10$MSICZtwxD05ac.1nR.rs5eE51jg.SVZ1RUwwJ23tWpAXURxEHAqfu', 'Male', '2000-01-02', '2025-01-10'),
(3, 'Mary ', 'Smith', 'MSmith@gmail.com', '$2a$10$7ifz7OItSdyP3m.wK9uRfe9ffMXHYN0NvykMTSFczs59z3TZLL0Eu', 'Female', '1983-07-22', '2025-01-10'),
(5, 'James', 'Harold', 'JHarold@gmail.com', '$2a$10$vpPYgqwiWYB/0Ls27Ribe.0CyqLs4I8fDYFSfRS6KHxOEKUM.Deba', 'Male', '1967-12-23', '2025-01-10'),
(6, 'John', 'Smith', 'johnsmith456@gmail.com', '$2a$10$fcmfROdHd6NlXG5Pmc7So.I6d/opQcWHN/.Zdgbf5yX0LFrbQeqoW', 'Male', '1947-12-22', '2025-01-10'),
(7, 'Jake', 'O\'Brien', 'JOB@gmail.com', '$2a$10$Jlj6i4leJXSvlTjQ/CPp7.chg.EYxuOlPOmJ.XzpgyoIXht7iFkY6', 'Male', '2005-12-12', '2025-01-10'),
(8, 'John', 'Doe', 'JohnDoe@gmail.com', '$2a$10$YwnsOu/1dhuSjLRiUkHam.iQ2FZIpTutpiqHoyB.ZohJIwh5nX.Z.', 'Male', '2000-01-14', '2025-02-15'),
(9, 'Jason', 'Powell', 'JasonPowell@Gmail.com', '$2a$10$H9/JA/filDZe2Ay7ZSMb9.YQf7y9avD0QLvy0jxGiUykJWKWy9vee', 'Male', '2000-09-17', '2025-02-15'),
(11, 'Thomas', 'Bourke', 'TB@gmail.com', '$2a$10$ypDj2PbiaWVZ4.r9Ym3CxuupWT.D7CNOaIc8P6P2zoesN9aZM/X4K', 'Male', '1978-06-22', '2025-03-20'),
(42, 'Tony', 'Kelly', 'TK@gmail.com', '$2a$10$bjLDWPDDbabd0wPA9OZXfu63a9o3YtnciRdUhOyVjGKfATR58OYYm', 'Male', '1997-12-12', '2024-05-05'),
(43, 'Adam', 'Hogan', 'AH@gmail.com', '$2a$10$l3rBDvuHQwmh42WSwMDPOe.i1vJFz7xMbSt6CQCmE4wl7Ar9WV45a', 'Male', '2001-12-20', '2025-03-10'),
(44, 'Barry', 'Nash', 'BNash@gmail.com', '$2a$10$SgRQbwxIcxnUSxiSRkRWd.paXWnz8oH4y17j6bL7ZRc44pdggcaWq', 'Male', '1986-12-20', '2023-12-20'),
(45, 'TJ', 'Reid', 'TJ@gmail.com', '$2a$10$q3L2Yqo0TXD0ytZE0qOFnOKhigNesNoFzzk/v1i4Ak4R3XoDnrOhC', 'Male', '1985-12-20', '2025-04-10'),
(46, 'Peter', 'Duggan', 'PD@gmail.com', '$2a$10$iDfKTocR9aCqHvTTvrzXBu0hR4mMHjY2o8UG.avCq6zm5hMT52j3K', 'Male', '1999-12-20', '2025-05-05'),
(47, 'Ryan', 'Taylor', 'Rt@gmail.com', '$2a$10$dTfuFIql4gvZExAy9BgQsOEnTI1mJdr7ApHdUbq.3mDreIZFnPhWi', 'Male', '1990-12-20', '2024-12-28'),
(48, 'Aidan', 'McCarthy', 'AM@gmail.com', '$2a$10$mCB5fNLBd.YOILg2ll2GS.2fBOjCH5AHeYEj/9uPRecnSVDScPlna', 'Male', '1986-12-20', '2004-12-20'),
(49, 'David', 'Fitzgerald', 'DF@gmail.com', '$2a$10$GQH7LGXOKjThth9pmhs/jeSo/TB0W1sbW78nABvyiBzKrunlfeLxC', 'Male', '1998-12-20', '2024-12-20'),
(50, 'John ', 'Conlon', 'JConlon@gmail.com', '$2a$10$U0qpot//sh1o7f8nPzv6Hezdbk8gsphuFywJnNsrSzoYAe8XeVmnC', 'Male', '1985-12-20', '2023-12-20'),
(51, 'David', 'Reidy', 'DR@gmail.com', '$2a$10$wvfF22fbwKXxQuC7uvf7KedAgopwW0RI5u0MAFbvckpoBB8vn/jOS', 'Male', '1980-12-20', '2023-12-20'),
(52, 'Cian', 'Lynch', 'CL@gmail.com', '$2a$10$zfvR14Qo9eHPQ8selhWZrOlCFV4aFXgc1Aw2zMz4L5HMJ.ABcE7hW', 'Male', '2000-12-20', '2025-01-20'),
(53, 'Walter', 'Walsh', 'WW@gmail.com', '$2a$10$i69HVcSVYBH4XDeFQmFnaezENwx9yFCToMsDT.lvl39uJMwB3Pa3y', 'Male', '1990-12-20', '2024-12-20'),
(54, 'Mark', 'Rodgers', 'MR@gmail.com', '$2a$10$oCajCgBuncpbYRBoIY9VGOGug.9LtD3ybj28X6Qm6XU7YO8/0U5dC', 'Male', '1987-12-10', '2024-12-20'),
(55, 'Johnathan', 'Clancy', 'JClancy@gmail.com', '$2a$10$2DAWdh5SWpgdWfLwN6HQMeqZcynnBkUfL6BOTIQoFK2VMgsl.LbxO', 'Male', '1999-12-20', '2024-12-20'),
(56, 'Conor', 'Leen', 'CLeen@gmail.com', '$2a$10$KF6ZokAOFCvQ4uXvJiRAr.87caxp5eqAGFdokJ3AOJe.ya51qYLpi', 'Male', '1990-12-20', '2025-03-20'),
(57, 'Jason', 'Pollock', 'JasonPollock@gmail.com', '$2a$10$BjHZ2a/hSnft2.UzCRB.8unCW2QmecPsOnrbmqDyMYpQ732gYdS16', 'Male', '2000-12-22', '2025-04-29'),
(58, 'Shauna', 'Smith', 'ssmith@gmail.com', '$2a$10$u3tcG1p7Cf7uiCENTwdUr.WgRHrUXamq/3szdguernWdOgCUJG2fO', 'Female', '2000-12-10', '2025-05-05'),
(59, 'Mary', 'Power', 'MP@gmail.com', '$2a$10$6YSDU2BBoYCZjYt1EsV0w.P4Y2dTVvPMk/NG5sEiILMXuWMpBtLwe', 'Female', '1965-12-12', '2025-04-29'),
(60, 'Holly', 'Langford', 'Hlangford@gmail.com', '$2a$10$9//hKtErZPWTku5CkrAwVOlP/5.HJA0/Pbd5loatxbYMKj/yZkhr2', 'Female', '1934-03-22', '2025-04-29'),
(61, 'Molly', 'Burns', 'MollyBurns@gmail.com', '$2a$10$mquxjuyPLtXTsWC4tEwXiu5UVPfGV.8DCuCbR8tsS18.CSyO7cus.', 'Female', '2004-12-19', '2025-04-29'),
(62, 'Sophie', 'O\'Brien', 'SophieOB@gmail.com', '$2a$10$6WJP4ihx6c.yJaj5FHqKruIFCHO3Y6wxnNwuvsPfUMYRoR5hRIjyq', 'Female', '1967-02-12', '2025-04-29'),
(63, 'Sally', 'Smith', 'SallySmith@gmail.com', '$2a$10$odLpyEsEruoMGuhVMLivO.ev6FEtFfthctILUlZ99CHC7bwnWlkFW', 'Female', '1975-07-22', '2025-04-29'),
(64, 'Mary', 'Loughran', 'MLoughran@gmail.com', '$2a$10$N/Y1ppMYGOPigc.My6dGmOukSEU3QmC.Br9NGqomcnfhw97Ywva/a', 'Female', '1957-08-20', '2025-04-29'),
(65, 'Lucy', 'Walsh', 'LWalsh@gmail.com', '$2a$10$EUkoNjn9XOucGL2xE/wBO.IrHF/mPFefDdqfIGWdYn8t/eaSyJZPe', 'Female', '1986-08-20', '2025-04-29');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(3, 3, 2),
(4, 5, 4),
(5, 6, 4),
(6, 7, 3),
(7, 8, 4),
(8, 9, 3),
(9, 11, 2),
(10, 42, 3),
(11, 43, 3),
(12, 44, 3),
(13, 45, 3),
(14, 46, 3),
(15, 47, 3),
(16, 48, 3),
(17, 49, 3),
(18, 50, 3),
(19, 51, 3),
(20, 52, 3),
(21, 53, 3),
(22, 54, 3),
(23, 55, 3),
(24, 56, 3),
(25, 57, 3),
(26, 58, 4),
(27, 59, 4),
(28, 60, 4),
(29, 61, 4),
(30, 62, 4),
(31, 63, 4),
(32, 64, 2),
(33, 65, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `club_events`
--
ALTER TABLE `club_events`
  ADD PRIMARY KEY (`club_event_id`);

--
-- Indexes for table `club_event_attendance`
--
ALTER TABLE `club_event_attendance`
  ADD PRIMARY KEY (`club_event_attendance_id`),
  ADD KEY `fk_club_event_attendance_club_event_id` (`club_event_id`),
  ADD KEY `fk_clubEventAttendance_user_id` (`user_id`);

--
-- Indexes for table `player_physical_stats`
--
ALTER TABLE `player_physical_stats`
  ADD PRIMARY KEY (`player_physical_stats_id`),
  ADD KEY `fk_player_physical_stats_user_id` (`user_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`team_id`);

--
-- Indexes for table `team_events`
--
ALTER TABLE `team_events`
  ADD PRIMARY KEY (`team_event_id`),
  ADD KEY `fk_team_events_team_id` (`team_id`);

--
-- Indexes for table `team_event_attendance`
--
ALTER TABLE `team_event_attendance`
  ADD PRIMARY KEY (`team_event_attendance_id`),
  ADD KEY `fk_team_event_attendance_team_event_id` (`team_event_id`),
  ADD KEY `fk_team_event_attendance_user_id` (`user_id`);

--
-- Indexes for table `team_members`
--
ALTER TABLE `team_members`
  ADD PRIMARY KEY (`team_member_id`),
  ADD KEY `fk_team_member_team_id` (`team_id`),
  ADD KEY `fk_team_member_user_id` (`user_id`);

--
-- Indexes for table `team_session_stats`
--
ALTER TABLE `team_session_stats`
  ADD PRIMARY KEY (`team_session_stats_id`),
  ADD KEY `fk_team_session_stats_team_event_id` (`team_event_id`),
  ADD KEY `fk_team_session_stats_user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email_unique` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_role_id`),
  ADD KEY `fk_user_roles_user_id` (`user_id`),
  ADD KEY `fk_user_roles_role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `club_events`
--
ALTER TABLE `club_events`
  MODIFY `club_event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `club_event_attendance`
--
ALTER TABLE `club_event_attendance`
  MODIFY `club_event_attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `player_physical_stats`
--
ALTER TABLE `player_physical_stats`
  MODIFY `player_physical_stats_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `team_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `team_events`
--
ALTER TABLE `team_events`
  MODIFY `team_event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `team_event_attendance`
--
ALTER TABLE `team_event_attendance`
  MODIFY `team_event_attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `team_members`
--
ALTER TABLE `team_members`
  MODIFY `team_member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `team_session_stats`
--
ALTER TABLE `team_session_stats`
  MODIFY `team_session_stats_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `club_event_attendance`
--
ALTER TABLE `club_event_attendance`
  ADD CONSTRAINT `fk_clubEventAttendance_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_club_event_attendance_club_event_id` FOREIGN KEY (`club_event_id`) REFERENCES `club_events` (`club_event_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `player_physical_stats`
--
ALTER TABLE `player_physical_stats`
  ADD CONSTRAINT `fk_player_physical_stats_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `team_events`
--
ALTER TABLE `team_events`
  ADD CONSTRAINT `fk_team_events_team_id` FOREIGN KEY (`team_id`) REFERENCES `teams` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `team_event_attendance`
--
ALTER TABLE `team_event_attendance`
  ADD CONSTRAINT `fk_team_event_attendance_team_event_id` FOREIGN KEY (`team_event_id`) REFERENCES `team_events` (`team_event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_team_event_attendance_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `team_members`
--
ALTER TABLE `team_members`
  ADD CONSTRAINT `fk_team_member_team_id` FOREIGN KEY (`team_id`) REFERENCES `teams` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_team_member_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `team_session_stats`
--
ALTER TABLE `team_session_stats`
  ADD CONSTRAINT `fk_team_session_stats_team_event_id` FOREIGN KEY (`team_event_id`) REFERENCES `team_events` (`team_event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_team_session_stats_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
