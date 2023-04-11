-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- 생성 시간: 23-04-11 07:31
-- 서버 버전: 10.4.27-MariaDB
-- PHP 버전: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `space-invaders`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `userdata`
--

CREATE TABLE `userdata` (
  `id` char(15) NOT NULL,
  `password` char(15) NOT NULL,
  `nickname` char(10) DEFAULT NULL,
  `stage_process` int(11) DEFAULT 0,
  `coin` int(11) DEFAULT 0,
  `stage1_best_score` int(11) DEFAULT 0,
  `stage2_best_score` int(11) DEFAULT 0,
  `stage3_best_score` int(11) DEFAULT 0,
  `stage4_best_score` int(11) DEFAULT 0,
  `stage5_best_score` int(11) DEFAULT 0,
  `is_hard_ship` tinyint(1) DEFAULT 0,
  `is_lucky_ship` tinyint(1) DEFAULT 0,
  `HP_potion` int(11) DEFAULT 0,
  `speed_potion` int(11) DEFAULT 0,
  `selected_ship` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 테이블의 덤프 데이터 `userdata`
--

INSERT INTO `userdata` (`id`, `password`, `nickname`, `stage_process`, `coin`, `stage1_best_score`, `stage2_best_score`, `stage3_best_score`, `stage4_best_score`, `stage5_best_score`, `is_hard_ship`, `is_lucky_ship`, `HP_potion`, `speed_potion`, `selected_ship`) VALUES
('themineg_p', 'goldengol', 'Lostddd', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('dfdf', 'dfdfd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('dfdf', 'ffff', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('dfdf', 'qqqq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('dfdff', 'dfdfddd', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('ffffff', 'ddddd', '1213123', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL),
('idid', 'pwpw', 'themineg_p', 0, 3550, 0, 0, 0, 0, 0, 1, 1, 41, 60, 0),
('123645', '5555', 'golden', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL),
('wwwwwwww', 'dddddddd', 'qwewqeqw', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL),
('wwwwwwwww', 'gggggggg', 'qweqewqeqw', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL),
('ffffffffffff', 'wwwwwwwww', 'ffffwefdfw', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
