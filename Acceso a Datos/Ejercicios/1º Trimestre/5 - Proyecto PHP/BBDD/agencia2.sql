-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-11-2020 a las 17:10:45
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agencia2`
--
CREATE DATABASE IF NOT EXISTS `agencia2` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `agencia2`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `Num_Vuelo` int(11) NOT NULL,
  `Cod_Turista` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `Clase` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `reserva`:
--   `Num_Vuelo`
--       `vuelo` -> `Num_Vuelo`
--   `Cod_Turista`
--       `turista` -> `Cod_Turista`
--

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`Num_Vuelo`, `Cod_Turista`, `Clase`) VALUES
(1, '5fa2c3a055', 0),
(2, '5fa2c3bbd2', 0),
(3, '5fa2c6814b', 1),
(4, '5fa2c69f5e', 1),
(5, '5fa2c6d348', 1),
(6, '5fa2c3a055', 0),
(6, '5fa2c6ecaa', 0),
(7, '5fa2c72942', 1),
(8, '5fa2c76426', 0),
(9, '5fa2c3a055', 1),
(9, '5fa2c78115', 0),
(10, '5fa2c7b68a', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `Cod_Sucursal` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `Director` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Num_Trabajadores` int(6) NOT NULL,
  `Direccion` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `Telefono` varchar(9) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `sucursal`:
--

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`Cod_Sucursal`, `Director`, `Num_Trabajadores`, `Direccion`, `Telefono`) VALUES
('2c81fa3587', 'Francisco Pérez Gonzálex', 100, ' Av del Padre Isla', '634523423'),
('2c87b4a014', 'Carlos Pérez Sanz', 245345, 'Av del Río Bernesga', '987283945'),
('2c8b430a5b', 'Alejandro Fernández Fernández', 357, 'Av Magdalena', '987234238'),
('2c8d0e03b0', 'Edurne García Pelaez', 2, 'Av Lancia Nº67', '987234235'),
('2c90653e78', 'Maria Magdalena Pézez Santos', 390, 'Av Madrid 90', '987123547'),
('2c92c24c9b', 'Claudia Martínez Cepeda', 0, ' Av del Reino de León', '614235123'),
('2c94ab8d10', 'Esther Martínez Claudio', 24, 'Av del Príncipe de Asturias', '978234234'),
('2c97c0afbe', 'Jorge Celadilla Marcos', 56, 'Av del Párroco Pablo Díez', '634523423'),
('2c9a6d9968', 'Jose Luis Anta Sincrasio', 8989, 'Av de San Mamés', '987234234'),
('2ca07a9f70', 'O Donell Ragnark', 0, 'Av de San Froilán', '230489235');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turista`
--

CREATE TABLE `turista` (
  `Cod_Turista` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `Apellidos` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Direccion` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `Telefono` varchar(9) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `turista`:
--

--
-- Volcado de datos para la tabla `turista`
--

INSERT INTO `turista` (`Cod_Turista`, `Nombre`, `Apellidos`, `Direccion`, `Telefono`) VALUES
('5fa2c3a055', 'Iván', 'García Prieto', 'Av La Libertad Nº73 1ºB', '601100518'),
('5fa2c3bbd2', 'Diego', 'Azcona Bailén', 'Av de José Antonio', '612312135'),
('5fa2c6814b', 'Juan ', 'Duarte Eizaga', 'Av de la Independencia', '656734523'),
('5fa2c69f5e', 'José Luis', 'Olmedo Rovira', 'Av de la Virgen de los Imposibles', '623463457'),
('5fa2c6d348', 'Lucía', 'Eslava Espasa', ' Av de los Bordadores', '623423426'),
('5fa2c6ecaa', 'Carmen', 'Dueñas Dorcas', ' Av de los Reyes Leoneses', '623423426'),
('5fa2c72942', 'Lourdes', 'Cuello Doncel', 'Av de Ordoño II 29', '620394820'),
('5fa2c76426', 'Milagros', 'Bellón Batres', ' Av de San Ignacio de Loyola', '637846345'),
('5fa2c78115', 'Jose María', 'Abascal Aguilar', 'Av del Doctor Fleming', '634523423'),
('5fa2c7b68a', 'Cristina', 'Almagro Allende', 'Av del General Sanjurjo', '698773576');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

CREATE TABLE `vuelo` (
  `Num_Vuelo` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Origen` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `Destino` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `Plazas_Totales` int(4) NOT NULL,
  `Plazas_Turistas` int(4) NOT NULL,
  `Cod_Sucursal` varchar(10) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `vuelo`:
--   `Cod_Sucursal`
--       `sucursal` -> `Cod_Sucursal`
--

--
-- Volcado de datos para la tabla `vuelo`
--

INSERT INTO `vuelo` (`Num_Vuelo`, `Fecha`, `Hora`, `Origen`, `Destino`, `Plazas_Totales`, `Plazas_Turistas`, `Cod_Sucursal`) VALUES
(1, '2021-11-11', '12:34:00', 'Madrid', 'Barcelona', 338, 45, '2c81fa3587'),
(2, '2022-06-06', '03:03:00', 'Valencia', 'Sevilla', 589, 567, '2c87b4a014'),
(3, '2020-12-12', '12:56:00', 'Zaragoza', 'Málaga', 1000, 899, '2c8b430a5b'),
(4, '2020-12-15', '07:47:00', 'Murcia', 'Palma de Mallorca', 456, 345, '2c8d0e03b0'),
(5, '2021-04-24', '21:21:00', 'Las Palmas Canarias', 'Bilbao', 123, 45, '2c90653e78'),
(6, '2020-12-31', '00:00:00', 'Alicante', 'Córdoba', 345, 234, '2c92c24c9b'),
(7, '2021-02-23', '01:00:00', 'Valladolid', 'Vigo', 876, 765, '2c97c0afbe'),
(8, '2021-06-30', '23:58:00', 'Gijón', 'Hospitalet', 45, 23, '2c9a6d9968'),
(9, '2020-11-04', '04:45:00', 'Vitoria', 'La Coruña', 32, 12, '2ca07a9f70'),
(10, '2021-08-12', '21:30:00', 'Elche', 'Granada', 100, 80, '2ca07a9f70');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`Num_Vuelo`,`Cod_Turista`),
  ADD KEY `Cod_Turista` (`Cod_Turista`),
  ADD KEY `Num_Vuelo` (`Num_Vuelo`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`Cod_Sucursal`),
  ADD KEY `Cod_Sucursal` (`Cod_Sucursal`);

--
-- Indices de la tabla `turista`
--
ALTER TABLE `turista`
  ADD PRIMARY KEY (`Cod_Turista`),
  ADD KEY `Cod_Turista` (`Cod_Turista`);

--
-- Indices de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD PRIMARY KEY (`Num_Vuelo`),
  ADD KEY `Num_Vuelo` (`Num_Vuelo`),
  ADD KEY `Cod_Sucursal` (`Cod_Sucursal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  MODIFY `Num_Vuelo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`Num_Vuelo`) REFERENCES `vuelo` (`Num_Vuelo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`Cod_Turista`) REFERENCES `turista` (`Cod_Turista`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD CONSTRAINT `vuelo_ibfk_1` FOREIGN KEY (`Cod_Sucursal`) REFERENCES `sucursal` (`Cod_Sucursal`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
