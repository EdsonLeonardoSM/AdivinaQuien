-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-06-2025 a las 02:54:46
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `juego`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidas`
--

CREATE TABLE `partidas` (
  `jugador1` varchar(100) DEFAULT NULL,
  `jugador2` varchar(100) DEFAULT NULL,
  `ganador` varchar(100) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `duracion` time DEFAULT NULL,
  `objeto_ganador` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partidas`
--

INSERT INTO `partidas` (`jugador1`, `jugador2`, `ganador`, `fecha`, `duracion`, `objeto_ganador`) VALUES
('hola', 'holaaa2', 'hola', '2025-06-19 22:41:50', '00:00:00', 'EndCrystal'),
('1234', 'kjkj', '1234', '2025-06-19 23:05:25', '00:00:00', 'Caña'),
('1324', 'hhh', '1324', '2025-06-19 23:12:24', '00:00:00', 'Ojo de araña'),
('jose2', 'kok', 'jose2', '2025-06-21 20:31:03', '00:00:13', 'Ojo de araña'),
('marci', 'kokkkkk', 'marci', '2025-06-21 20:34:13', '00:00:20', 'Espada'),
('alon', '777', 'alon', '2025-06-21 20:36:17', '00:00:17', 'Ballesta'),
('pop', 'uiu', 'uiu', '2025-06-21 20:59:13', '00:00:12', 'EndCrystal'),
('carlos', 'juan', 'carlos', '2025-06-21 22:58:15', '00:00:13', 'Melon'),
('Pedro', 'pollo', 'pollo', '2025-06-21 23:23:41', '00:00:38', 'Antorcha'),
('rich', 'poor', 'poor', '2025-06-21 23:35:20', '00:00:36', 'Lana Rosa'),
('ñoño', 'ñoña', 'ñoño', '2025-06-21 23:42:53', '00:00:26', 'EndCrystal'),
('fjk', 'n,mn', 'fjk', '2025-06-21 23:57:02', '00:00:33', 'Libro Encantado'),
('888', '000', '888', '2025-06-22 00:25:51', '00:00:14', 'Antorcha');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
