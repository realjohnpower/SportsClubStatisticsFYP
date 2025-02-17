-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 11, 2025 at 01:22 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

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
DROP DATABASE IF EXISTS sport_club_db

CREATE DATABASE sport_club_db

USE sport_club_db
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
(1, 'dinner dance', 0x44696e6e65722044616e636520617420436173746c65204f616b7320486f74656c206f6e207468652032326e64204f66204175677573742061742032303235, '2025-08-22 20:30:00', 'Castle Oaks Hotel');

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
(1, 1, 1, 1);

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
(1, 7, 120, 1, 40, 120, '2025-02-05'),
(2, 2, 120, 120, 25, 0.008333333333333333, '2025-02-05'),
(3, 2, 100, 190, 56, 0.002770083102493075, '2025-02-05'),
(4, 7, 80, 1.75, 40, 26.122448979591837, '2025-02-05'),
(5, 7, 72, 1.8, 23, 22.22222222222222, '2025-02-05');

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
(2, 'hurling team ', 3),
(3, 'football team', 3);

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
(1, 'hurling match', 'match vs Kilmacud Crokes', '2025-12-22 19:00:00', 'Dublin', 2);

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
(1, 1, 2, 1);

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
(2, 2, 2),
(4, 3, 7),
(5, 2, 7);

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
(9, 1, 2, 23, 24, 25, 200),
(10, 1, 7, 45, 56, 78, 300);

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
(2, 'Jack', 'Keogh', 'JKeogh@gmail.com', '$2a$10$AYGP05q8LfSSNFd8R5wYjuDcSFUepyHjmwNG1YKQGy00qD9qZgOly', 'Male', '1967-12-23', '2025-01-10'),
(3, 'Mary ', 'Smith', 'MSmith@gmail.com', '$2a$10$7ifz7OItSdyP3m.wK9uRfe9ffMXHYN0NvykMTSFczs59z3TZLL0Eu', 'Female', '1983-07-22', '2025-01-10'),
(5, 'James', 'Harold', 'JHarold@gmail.com', '$2a$10$vpPYgqwiWYB/0Ls27Ribe.0CyqLs4I8fDYFSfRS6KHxOEKUM.Deba', 'Male', '1967-12-23', '2025-01-10'),
(6, 'John', 'Smith', 'johnsmith456@gmail.com', '$2a$10$fcmfROdHd6NlXG5Pmc7So.I6d/opQcWHN/.Zdgbf5yX0LFrbQeqoW', 'Male', '1947-12-22', '2025-01-10'),
(7, 'Jake', 'O\'Brien', 'JOB@gmail.com', '$2a$10$Jlj6i4leJXSvlTjQ/CPp7.chg.EYxuOlPOmJ.XzpgyoIXht7iFkY6', 'Male', '2005-12-12', '2025-01-10');

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
(2, 2, 3),
(3, 3, 2),
(4, 5, 4),
(5, 6, 4),
(6, 7, 3);

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
  MODIFY `club_event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `club_event_attendance`
--
ALTER TABLE `club_event_attendance`
  MODIFY `club_event_attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `player_physical_stats`
--
ALTER TABLE `player_physical_stats`
  MODIFY `player_physical_stats_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `team_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `team_events`
--
ALTER TABLE `team_events`
  MODIFY `team_event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `team_event_attendance`
--
ALTER TABLE `team_event_attendance`
  MODIFY `team_event_attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `team_members`
--
ALTER TABLE `team_members`
  MODIFY `team_member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `team_session_stats`
--
ALTER TABLE `team_session_stats`
  MODIFY `team_session_stats_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
