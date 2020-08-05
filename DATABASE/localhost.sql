-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 05, 2020 at 11:20 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id12222101_project7`
--
CREATE DATABASE IF NOT EXISTS `id12222101_project7` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id12222101_project7`;

-- --------------------------------------------------------

--
-- Table structure for table `client_contact`
--

CREATE TABLE `client_contact` (
  `id` int(11) NOT NULL,
  `cid` varchar(20) NOT NULL,
  `contact1` varchar(20) NOT NULL,
  `contact2` varchar(20) NOT NULL,
  `contact3` varchar(50) NOT NULL,
  `contact4` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client_contact`
--

INSERT INTO `client_contact` (`id`, `cid`, `contact1`, `contact2`, `contact3`, `contact4`) VALUES
(25, '1', '7624069124', '7984356582', '', ''),
(26, 'test', '', '', '', ''),
(27, '7', '12345', '12345', '', ''),
(29, '', '', '', '', ''),
(30, '', '', '', '', ''),
(31, '', '', '', '', ''),
(32, '9', '76240', '69124', '', ''),
(33, '', '', '', '', ''),
(34, '', '', '', '', ''),
(35, '', '', '', '', ''),
(36, '', '', '', '', ''),
(37, '11', '0000', '0000', '', ''),
(38, '1', '123456', '6w6263838', '', ''),
(39, '13', '7624069124', '7624069124', '', ''),
(40, '13', '7624069124', '7624069124', '', ''),
(41, '', '', '', '', ''),
(42, '13', '1234567890', '1234567890', '', ''),
(43, '', '', '', '', ''),
(44, '11', '7624069124', '7624069124', '', ''),
(45, '14', '1231231239', '1234567891', '', ''),
(46, '', '', '', '', ''),
(47, '', '', '', '', ''),
(48, '15', '8160880525', '', '', ''),
(49, '16', '7624069124', '7624069124', '7624069124', '7624069124'),
(50, '17', '9408873955', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `client_data`
--

CREATE TABLE `client_data` (
  `id` int(11) NOT NULL,
  `cid` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `value` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `client_data`
--

INSERT INTO `client_data` (`id`, `cid`, `name`, `value`) VALUES
(1, '', '', 'true'),
(2, '7', 'view', 'true'),
(3, '9', 'view', 'false'),
(4, '1', 'view', 'false'),
(5, '11', 'view', 'true'),
(6, '12', 'view', 'false'),
(7, '13', 'view', 'true'),
(8, '14', 'view', 'true'),
(9, '15', 'view', 'true'),
(10, '16', 'view', 'true'),
(11, '17', 'view', 'true');

-- --------------------------------------------------------

--
-- Table structure for table `client_details`
--

CREATE TABLE `client_details` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobileno` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client_details`
--

INSERT INTO `client_details` (`id`, `name`, `email`, `mobileno`, `password`) VALUES
(7, 'test', 'test', 'test', 'test'),
(11, 'test2', 'test2@gmail.com', 'test2', 'testtest'),
(12, 'Nikunj Ramani', 'nikunj@gmail.com', 'nikunjramani', '7624069124'),
(13, 'Test', 'test@gmail.com', '7624069124', 'testtest'),
(14, 'abc', 'abc@gmail.com', '1234567890', '12345678'),
(15, 'abc', 'drashti3899@gmail.com', '1234567890', '12345678'),
(16, 'test2', 'test3@gmail.com', '7624069124', 'testtest'),
(17, 'Drashti', 'asd@gmail.com', '8128617405', '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `cid` varchar(11) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitute` varchar(100) NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `cid`, `latitude`, `longitute`, `datetime`) VALUES
(452, '13', '23.0278303', '72.4640926', '2020-04-23 04:55:44'),
(453, '13', '23.0278303', '72.4640926', '2020-04-23 04:56:01'),
(454, '13', '23.0278303', '72.4640926', '2020-04-23 04:56:04'),
(455, '13', '23.0278303', '72.4640926', '2020-04-23 04:56:34'),
(456, '13', '23.0278303', '72.4640926', '2020-04-23 04:56:52'),
(457, '13', '23.0278303', '72.4640926', '2020-04-23 04:56:53'),
(458, '13', '23.0278303', '72.4640926', '2020-04-23 04:57:15'),
(459, '13', '23.0278303', '72.4640926', '2020-04-23 04:57:23'),
(460, '13', '23.0278303', '72.4640926', '2020-04-23 04:57:42'),
(461, '13', '23.0278303', '72.4640926', '2020-04-23 04:57:43'),
(462, '13', '23.0278303', '72.4640926', '2020-04-23 04:58:04'),
(463, '13', '23.0278303', '72.4640926', '2020-04-23 05:01:08'),
(464, '13', '23.0278303', '72.4640926', '2020-04-23 05:01:08'),
(465, '13', '23.0278303', '72.4640926', '2020-04-23 05:15:29'),
(466, '13', '23.0278303', '72.4640926', '2020-04-23 05:15:29'),
(467, '13', '23.0278303', '72.4640926', '2020-04-23 05:18:23'),
(468, '13', '23.0278303', '72.4640926', '2020-04-23 05:18:24'),
(469, '', '', '', '2020-04-23 05:20:26'),
(470, '13', '23.0278303', '72.4640926', '2020-04-23 05:20:31'),
(471, '13', '23.0278303', '72.4640926', '2020-04-23 05:20:31'),
(472, '13', '23.0278303', '72.4640926', '2020-04-23 05:20:36'),
(473, '13', '23.0278303', '72.4640926', '2020-04-23 05:21:20'),
(474, '13', '23.0278303', '72.4640926', '2020-04-23 05:21:20'),
(475, '13', '23.0278303', '72.4640926', '2020-04-23 05:21:27'),
(476, '13', '23.0278303', '72.4640926', '2020-04-23 05:22:10'),
(477, '13', '23.0278303', '72.4640926', '2020-04-23 05:22:10'),
(478, '13', '23.0278303', '72.4640926', '2020-04-23 05:22:17'),
(479, '13', '23.0278303', '72.4640926', '2020-04-23 05:26:13'),
(480, '13', '23.0278303', '72.4640926', '2020-04-23 05:26:13'),
(481, '13', '23.0278303', '72.4640926', '2020-04-23 05:31:05'),
(482, '13', '23.0278303', '72.4640926', '2020-04-23 05:31:05'),
(483, '13', '23.0278303', '72.4640926', '2020-04-23 05:31:19'),
(484, '13', '23.0278303', '72.4640926', '2020-04-23 05:31:55'),
(485, '13', '23.0278303', '72.4640926', '2020-04-23 05:31:55'),
(486, '13', '23.0278303', '72.4640926', '2020-04-23 05:36:33'),
(487, '13', '23.0278303', '72.4640926', '2020-04-23 05:36:34'),
(488, '13', '23.0314505', '72.4614213', '2020-04-23 05:38:31'),
(489, '13', '23.0314505', '72.4614213', '2020-04-23 05:38:31'),
(490, '13', '23.0314505', '72.4614213', '2020-04-23 05:51:56'),
(491, '13', '23.0314505', '72.4614213', '2020-04-23 05:51:56'),
(492, '13', '23.0314486', '72.4614157', '2020-04-23 05:52:10'),
(493, '13', '23.0314437', '72.4614214', '2020-04-23 06:05:22'),
(494, '13', '23.0314437', '72.4614214', '2020-04-23 06:05:22'),
(495, '13', '23.0278303', '72.4640926', '2020-04-23 06:05:30'),
(496, '13', '23.0278303', '72.4640926', '2020-04-23 06:18:27'),
(497, '13', '23.0278303', '72.4640926', '2020-04-23 06:18:27'),
(498, '11', '23.0314501', '72.4614185', '2020-04-23 06:19:17'),
(499, '11', '23.0314501', '72.4614185', '2020-04-23 06:19:18'),
(500, '11', '23.0314508', '72.4614206', '2020-04-23 06:19:56'),
(501, '11', '23.0314501', '72.4614185', '2020-04-23 06:20:11'),
(502, '11', '23.0315358', '72.4618737', '2020-04-23 06:20:11'),
(503, '12', '23.0314484', '72.4614216', '2020-04-23 06:23:07'),
(504, '12', '23.0314484', '72.4614216', '2020-04-23 06:23:09'),
(505, '12', '23.0314484', '72.4614216', '2020-04-23 06:23:11'),
(506, '12', '23.0271948', '72.4648322', '2020-04-23 06:23:27'),
(507, '12', '23.0271948', '72.4648322', '2020-04-23 06:23:29'),
(508, '12', '23.0271948', '72.4648322', '2020-04-23 06:23:30'),
(509, '12', '23.0271948', '72.4648322', '2020-04-23 06:23:44'),
(510, '12', '23.0271948', '72.4648322', '2020-04-23 06:23:44'),
(511, '12', '23.0271948', '72.4648322', '2020-04-23 06:23:58'),
(512, '12', '23.0271948', '72.4648322', '2020-04-23 06:23:59'),
(513, '12', '23.0314458', '72.4614209', '2020-04-23 06:25:10'),
(514, '12', '23.0314458', '72.4614209', '2020-04-23 06:25:11'),
(515, '12', '23.0314458', '72.4614209', '2020-04-23 06:25:12'),
(516, '12', '23.0314458', '72.4614209', '2020-04-23 06:25:13'),
(517, '12', '23.0271948', '72.4648322', '2020-04-23 06:27:47'),
(518, '12', '23.0271948', '72.4648322', '2020-04-23 06:27:47'),
(519, '12', '23.0271948', '72.4648322', '2020-04-23 06:28:36'),
(520, '12', '23.0271948', '72.4648322', '2020-04-23 06:28:36'),
(521, '13', '23.0278303', '72.4640926', '2020-04-23 06:53:04'),
(522, '13', '23.0278303', '72.4640926', '2020-04-23 06:53:05'),
(523, '12', '23.0278303', '72.4640926', '2020-04-23 07:05:38'),
(524, '12', '23.0278303', '72.4640926', '2020-04-23 07:05:38'),
(525, '12', '23.0278303', '72.4640926', '2020-04-23 07:05:44'),
(526, '12', '23.0278303', '72.4640926', '2020-04-23 07:05:44'),
(527, '12', '23.0278303', '72.4640926', '2020-04-23 07:06:15'),
(528, '12', '23.0278303', '72.4640926', '2020-04-23 07:06:19'),
(529, '14', '23.0295941', '72.4644429', '2020-04-23 15:36:01'),
(530, '13', '23.0317883', '72.4640926', '2020-04-24 16:14:22'),
(531, '13', '23.0317883', '72.4640926', '2020-04-24 16:14:22'),
(532, '13', '23.0315358', '72.4618737', '2020-04-25 12:15:12'),
(533, '13', '23.0315358', '72.4618737', '2020-04-25 12:15:13'),
(534, '16', '23.0315358', '72.4618737', '2020-04-25 12:16:02'),
(535, '16', '23.0315358', '72.4618737', '2020-04-25 12:16:02'),
(536, '16', '23.0315358', '72.4618737', '2020-04-25 12:16:19'),
(537, '16', '23.0315358', '72.4618737', '2020-04-25 12:16:53'),
(538, '16', '23.0315358', '72.4618737', '2020-04-25 12:16:53'),
(539, '16', '23.0315358', '72.4618737', '2020-04-25 12:17:05'),
(540, '16', '23.0315358', '72.4618737', '2020-04-25 12:17:06'),
(541, '16', '23.0315358', '72.4618737', '2020-04-25 12:17:06'),
(542, '16', '23.0315358', '72.4618737', '2020-04-25 12:17:09'),
(543, '16', '23.0315358', '72.4618737', '2020-04-25 12:17:42'),
(544, '16', '23.0315358', '72.4618737', '2020-04-25 12:17:42'),
(545, '16', '23.0315358', '72.4618737', '2020-04-25 12:25:09'),
(546, '16', '23.0315358', '72.4618737', '2020-04-25 12:25:10'),
(547, '16', '23.0315358', '72.4618737', '2020-04-25 12:26:00'),
(548, '16', '23.0315358', '72.4618737', '2020-04-25 12:26:00'),
(549, '16', '23.0315358', '72.4618737', '2020-04-25 12:37:00'),
(550, '16', '23.0315358', '72.4618737', '2020-04-25 12:37:00'),
(551, '16', '23.0315358', '72.4618737', '2020-04-25 12:37:51'),
(552, '16', '23.0315358', '72.4618737', '2020-04-25 12:37:51'),
(553, '16', '23.0315358', '72.4618737', '2020-04-25 13:09:34'),
(554, '16', '23.0315358', '72.4618737', '2020-04-25 13:09:34'),
(555, '16', '23.0315358', '72.4618737', '2020-04-25 13:15:23'),
(556, '16', '23.0315358', '72.4618737', '2020-04-25 13:15:23'),
(557, '16', '23.0315358', '72.4618737', '2020-04-25 13:24:28'),
(558, '16', '23.0315358', '72.4618737', '2020-04-25 13:24:29'),
(559, '16', '23.0315358', '72.4618737', '2020-04-26 04:45:16'),
(560, '16', '23.0315358', '72.4618737', '2020-04-26 04:45:16'),
(561, '16', '23.0315358', '72.4618737', '2020-04-26 04:47:40'),
(562, '16', '23.0315358', '72.4618737', '2020-04-26 04:47:40'),
(563, '16', '23.0315358', '72.4618737', '2020-04-26 04:50:15'),
(564, '16', '23.0315358', '72.4618737', '2020-04-26 04:50:15'),
(565, '16', '23.0276647', '72.4646311', '2020-04-26 04:51:06'),
(566, '16', '23.0276647', '72.4646311', '2020-04-26 04:51:06'),
(567, '16', '23.0315358', '72.4618737', '2020-04-26 06:51:48'),
(568, '16', '23.0315358', '72.4618737', '2020-04-26 06:51:49'),
(569, '16', '23.0314465', '72.4614157', '2020-04-26 06:53:23'),
(570, '16', '23.0314465', '72.4614157', '2020-04-26 06:53:23'),
(571, '16', '23.0314465', '72.4614157', '2020-04-26 06:58:23'),
(572, '16', '23.0314465', '72.4614157', '2020-04-26 06:58:24'),
(573, '16', '23.0314465', '72.4614157', '2020-04-26 07:01:58'),
(574, '16', '23.0314465', '72.4614157', '2020-04-26 07:01:58'),
(575, '16', '23.0314465', '72.4614157', '2020-04-26 07:02:01'),
(576, '16', '23.0314465', '72.4614157', '2020-04-26 07:02:18'),
(577, '16', '23.0314465', '72.4614157', '2020-04-26 07:03:17'),
(578, '16', '23.0314465', '72.4614157', '2020-04-26 07:03:17'),
(579, '16', '23.0314465', '72.4614157', '2020-04-26 07:04:12'),
(580, '16', '23.0314465', '72.4614157', '2020-04-26 07:04:12'),
(581, '16', '23.0314465', '72.4614157', '2020-04-26 07:05:27'),
(582, '16', '23.0314465', '72.4614157', '2020-04-26 07:05:27'),
(583, '16', '23.0314465', '72.4614157', '2020-04-26 07:05:29'),
(584, '16', '23.0314465', '72.4614157', '2020-04-26 07:08:24'),
(585, '16', '23.0314465', '72.4614157', '2020-04-26 07:08:24'),
(586, '16', '23.0314465', '72.4614157', '2020-04-26 07:08:35'),
(587, '16', '23.0314465', '72.4614157', '2020-04-26 07:09:13'),
(588, '16', '23.0314465', '72.4614157', '2020-04-26 07:09:14'),
(589, '16', '23.0314465', '72.4614157', '2020-04-26 07:10:11'),
(590, '16', '23.0314465', '72.4614157', '2020-04-26 07:10:12'),
(591, '16', '23.0314465', '72.4614157', '2020-04-26 07:11:08'),
(592, '16', '23.0314465', '72.4614157', '2020-04-26 07:11:08'),
(593, '16', '23.0314465', '72.4614157', '2020-04-26 07:11:12'),
(594, '16', '23.0314465', '72.4614157', '2020-04-26 07:11:12'),
(595, '16', '23.0314465', '72.4614157', '2020-04-26 07:11:59'),
(596, '16', '23.0314465', '72.4614157', '2020-04-26 07:11:59'),
(597, '16', '23.0314465', '72.4614157', '2020-04-26 07:12:02'),
(598, '16', '23.0314465', '72.4614157', '2020-04-26 07:12:02'),
(599, '16', '23.0314465', '72.4614157', '2020-04-26 07:12:19'),
(600, '16', '23.0314465', '72.4614157', '2020-04-26 07:12:19'),
(601, '16', '23.0314465', '72.4614157', '2020-04-26 07:12:30'),
(602, '16', '23.0276647', '72.4646311', '2020-04-26 07:15:00'),
(603, '16', '23.0276647', '72.4646311', '2020-04-26 07:15:00'),
(604, '16', '23.0276647', '72.4646311', '2020-04-26 07:30:22'),
(605, '16', '23.0276647', '72.4646311', '2020-04-26 07:30:22'),
(606, '16', '23.0276647', '72.4646311', '2020-04-26 07:30:24'),
(607, '16', '23.0276647', '72.4646311', '2020-04-26 07:30:39'),
(608, '16', '23.0276647', '72.4646311', '2020-04-26 07:30:42'),
(609, '16', '23.0276647', '72.4646311', '2020-04-26 07:30:46'),
(610, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:10'),
(611, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:11'),
(612, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:11'),
(613, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:14'),
(614, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:14'),
(615, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:30'),
(616, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:31'),
(617, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:32'),
(618, '16', '23.0276647', '72.4646311', '2020-04-26 07:31:36'),
(619, '16', '23.0276647', '72.4646311', '2020-04-26 07:35:32'),
(620, '16', '23.0276647', '72.4646311', '2020-04-26 07:35:33'),
(621, '16', '23.0276647', '72.4646311', '2020-04-26 07:36:11'),
(622, '16', '23.0276647', '72.4646311', '2020-04-26 07:36:22'),
(623, '16', '23.0276647', '72.4646311', '2020-04-26 07:36:22'),
(624, '16', '23.0276647', '72.4646311', '2020-04-26 07:43:47'),
(625, '16', '23.0276647', '72.4646311', '2020-04-26 07:43:47'),
(626, '16', '23.0276647', '72.4646311', '2020-04-26 07:43:48'),
(627, '16', '23.0276647', '72.4646311', '2020-04-26 07:44:00'),
(628, '16', '23.0276647', '72.4646311', '2020-04-26 07:48:44'),
(629, '16', '23.0276647', '72.4646311', '2020-04-26 07:48:44'),
(630, '16', '23.0314463', '72.4614216', '2020-04-26 07:48:47'),
(631, '16', '23.0314463', '72.4614216', '2020-04-26 07:49:00'),
(632, '16', '23.031448', '72.4614185', '2020-04-26 07:49:34'),
(633, '16', '23.031448', '72.4614185', '2020-04-26 07:49:34'),
(634, '16', '23.031448', '72.4614185', '2020-04-26 07:49:37'),
(635, '16', '23.031448', '72.4614185', '2020-04-26 07:49:49'),
(636, '16', '23.031448', '72.4614185', '2020-04-26 07:52:16'),
(637, '16', '23.031448', '72.4614185', '2020-04-26 07:52:17'),
(638, '16', '23.031448', '72.4614185', '2020-04-26 07:52:20'),
(639, '16', '23.031448', '72.4614185', '2020-04-26 07:52:29'),
(640, '16', '23.031448', '72.4614185', '2020-04-26 08:31:23'),
(641, '16', '23.031448', '72.4614185', '2020-04-26 08:31:23'),
(642, '16', '23.031448', '72.4614185', '2020-04-26 08:36:08'),
(643, '16', '23.031448', '72.4614185', '2020-04-26 08:36:08'),
(644, '16', '23.0271948', '72.4648322', '2020-05-10 12:55:03'),
(645, '16', '23.0271948', '72.4648322', '2020-05-10 12:55:04'),
(646, '16', '23.0315358', '72.4618737', '2020-05-10 12:58:12'),
(647, '16', '23.0315358', '72.4618737', '2020-05-10 12:58:12'),
(648, '16', '23.0315358', '72.4618737', '2020-05-10 12:59:03'),
(649, '16', '23.0315358', '72.4618737', '2020-05-10 12:59:03'),
(650, '16', '23.028011', '72.4633076', '2020-05-10 13:49:00'),
(651, '16', '23.028011', '72.4633076', '2020-05-10 13:49:00'),
(652, '16', '23.028011', '72.4633076', '2020-05-10 13:49:50'),
(653, '16', '23.028011', '72.4633076', '2020-05-10 13:49:50'),
(654, '16', '23.028011', '72.4633076', '2020-05-10 13:50:39'),
(655, '16', '23.028011', '72.4633076', '2020-05-10 13:50:39'),
(656, '16', '23.028011', '72.4633076', '2020-05-10 13:52:00'),
(657, '16', '23.028011', '72.4633076', '2020-05-10 13:52:00'),
(658, '16', '23.028011', '72.4633076', '2020-05-10 13:52:00'),
(659, '16', '23.028011', '72.4633076', '2020-05-10 13:52:49'),
(660, '16', '23.028011', '72.4633076', '2020-05-10 13:52:49'),
(661, '16', '23.028011', '72.4633076', '2020-05-10 13:52:49'),
(662, '16', '23.028011', '72.4633076', '2020-05-10 13:53:39'),
(663, '16', '23.028011', '72.4633076', '2020-05-10 13:53:40'),
(664, '16', '23.028011', '72.4633076', '2020-05-10 13:53:40'),
(665, '16', '23.0307278', '72.4652403', '2020-05-10 13:55:05'),
(666, '16', '23.0307278', '72.4652403', '2020-05-10 13:55:05'),
(667, '16', '23.0307278', '72.4652403', '2020-05-10 13:55:50'),
(668, '16', '23.0307278', '72.4652403', '2020-05-10 13:55:54'),
(669, '16', '23.0307278', '72.4652403', '2020-05-10 13:55:55'),
(670, '16', '23.0307278', '72.4652403', '2020-05-10 13:56:39'),
(671, '16', '23.0307278', '72.4652403', '2020-05-10 13:56:44'),
(672, '16', '23.0307278', '72.4652403', '2020-05-10 13:56:45'),
(673, '16', '23.0307278', '72.4652403', '2020-05-10 13:58:24'),
(674, '16', '23.0307278', '72.4652403', '2020-05-10 13:58:52'),
(675, '16', '23.0307278', '72.4652403', '2020-05-10 13:58:52'),
(676, '16', '23.0307278', '72.4652403', '2020-05-10 14:02:00'),
(677, '16', '23.0307278', '72.4652403', '2020-05-10 14:02:16'),
(678, '16', '23.0307278', '72.4652403', '2020-05-10 14:02:16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client_contact`
--
ALTER TABLE `client_contact`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_data`
--
ALTER TABLE `client_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_details`
--
ALTER TABLE `client_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client_contact`
--
ALTER TABLE `client_contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `client_data`
--
ALTER TABLE `client_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `client_details`
--
ALTER TABLE `client_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=679;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;