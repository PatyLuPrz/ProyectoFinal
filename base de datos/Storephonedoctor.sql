CREATE DATABASE storephonedoctor;
USE storephonedoctor;

-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-08-2018 a las 06:32:10
-- Versión del servidor: 10.1.33-MariaDB
-- Versión de PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `storephonedoctor`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `USERNAME_CL` varchar(20) NOT NULL,
  `EMAIL_CL` varchar(50) NOT NULL,
  `CONTRASENA_CL` varchar(50) NOT NULL,
  `NOMBRE_CL` varchar(30) NOT NULL,
  `AP_CL` varchar(30) NOT NULL,
  `AM_CL` varchar(30) NOT NULL,
  `TEL_CL` varchar(15) NOT NULL,
  `MUN_CL` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='REGISTRO DE CLIENTES FRECUENTES';

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`USERNAME_CL`, `EMAIL_CL`, `CONTRASENA_CL`, `NOMBRE_CL`, `AP_CL`, `AM_CL`, `TEL_CL`, `MUN_CL`) VALUES
('Publico General', 'storephonedoctor@gmail.com', 'Publico General', 'Store', 'Phone', 'Doctor', '00000000', 'StorePhone Doctor');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `inventario_no_admn`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `inventario_no_admn` (
`NOMBRE_P` varchar(50)
,`CANTIDAD_P` int(11)
,`PRECIO_VENTA_P` double
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `CODIGO_P` varchar(13) NOT NULL,
  `NOMBRE_P` varchar(50) NOT NULL,
  `CATEGORIA_P` varchar(30) NOT NULL,
  `MARCA_P` varchar(30) NOT NULL,
  `CANTIDAD_P` int(11) NOT NULL,
  `PRECIO_VENTA_P` double NOT NULL,
  `PRECIO_COMPRA_P` double NOT NULL,
  `IMG_P` text,
  `DESCRIPCION_P` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='REGISTRO DEL INVENTARIO';

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`CODIGO_P`, `NOMBRE_P`, `CATEGORIA_P`, `MARCA_P`, `CANTIDAD_P`, `PRECIO_VENTA_P`, `PRECIO_COMPRA_P`, `IMG_P`, `DESCRIPCION_P`) VALUES
('SPD0000000001', 'Mica Iphone 5/5s/5c', 'Mica de Vidrio ', 'Apple', 13, 50, 30, NULL, NULL),
('SPD0000000002', 'Mica Iphone 6/6s', 'Mica de Vidrio ', 'Apple', 18, 50, 30, NULL, NULL),
('SPD0000000003', 'Mica Iphone 7/8', 'Mica de Vidrio ', 'Apple', 18, 50, 30, NULL, NULL),
('SPD0000000004', 'Micro SD 8GB Clase 10', 'Memoria', 'Kingston', 8, 120, 80, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `FOLIO_S` varchar(13) NOT NULL,
  `USERNAME_US` varchar(20) NOT NULL,
  `USERNAME_CL` varchar(20) NOT NULL DEFAULT '',
  `FECHALLEGADA_S` varchar(15) NOT NULL,
  `FECHASALIDA_S` varchar(15) DEFAULT NULL,
  `NOMBRECLIENTE_S` varchar(60) NOT NULL,
  `TELEFONOCLIENTE_S` varchar(20) NOT NULL,
  `DESCRIPCION_S` varchar(250) NOT NULL,
  `PRECIO_S` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='BITACORA DE SERVICIOS';

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`FOLIO_S`, `USERNAME_US`, `USERNAME_CL`, `FECHALLEGADA_S`, `FECHASALIDA_S`, `NOMBRECLIENTE_S`, `TELEFONOCLIENTE_S`, `DESCRIPCION_S`, `PRECIO_S`) VALUES
('SSP0000000001', 'NorbertoPaloma1998', 'Publico General', '2018-08-16', 'En proceso', 'Macario Sicario', '7751234567', 'Display iphone 7 negro', 2500),
('SSP0000000002', 'NorbertoPaloma1998', 'MayraPatric', '2018-08-16', 'En proceso', 'Mayra', '7759876543', 'Cambio de Touch Verykool ', 100);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `servicios_no_adm`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `servicios_no_adm` (
`FOLIO_S` varchar(13)
,`FECHALLEGADA_S` varchar(15)
,`NOMBRECLIENTE_S` varchar(60)
,`TELEFONOCLIENTE_S` varchar(20)
,`DESCRIPCION_S` varchar(250)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `servicios_no_adm_dos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `servicios_no_adm_dos` (
`FOLIO_S` varchar(13)
,`FECHALLEGADA_S` varchar(15)
,`NOMBRE_CL` varchar(30)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `USERNAME_US` varchar(20) NOT NULL,
  `EMAIL_US` varchar(50) NOT NULL,
  `CONTRASENA_US` varchar(50) NOT NULL,
  `NOMBRE_US` varchar(30) NOT NULL,
  `AP_US` varchar(30) NOT NULL,
  `AM_US` varchar(30) NOT NULL,
  `TIPO_US` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='REGISTRO DE USUARIOS DEL SISTEMA';

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`USERNAME_US`, `EMAIL_US`, `CONTRASENA_US`, `NOMBRE_US`, `AP_US`, `AM_US`, `TIPO_US`) VALUES
('NorbertoPaloma1998', 'luis_norberto1998@hotmail.com', '66f4501149cbc7d3a40f3be7dce4b4b970666649', 'Luis Norberto', 'Paloma', 'Rodriguez', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `FOLIO_V` varchar(13) NOT NULL,
  `USERNAME_US` varchar(20) NOT NULL,
  `USERNAME_CL` varchar(50) NOT NULL,
  `FECHA_V` varchar(15) NOT NULL,
  `IMPORTE_V` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='BITACORA DE VENTAS' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas_producto`
--

CREATE TABLE `ventas_producto` (
  `ID_VP` int(11) NOT NULL,
  `FOLIO_V` varchar(13) NOT NULL,
  `CODIGO_P` varchar(13) NOT NULL,
  `PRECIO_VP` double NOT NULL,
  `SUBTOTAL_VP` double NOT NULL,
  `CANTIDAD_VP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='PRODUCTOS VENDIDOS';

-- --------------------------------------------------------

--
-- Estructura para la vista `inventario_no_admn`
--
DROP TABLE IF EXISTS `inventario_no_admn`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `inventario_no_admn`  AS  select `productos`.`NOMBRE_P` AS `NOMBRE_P`,`productos`.`CANTIDAD_P` AS `CANTIDAD_P`,`productos`.`PRECIO_VENTA_P` AS `PRECIO_VENTA_P` from `productos` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `servicios_no_adm`
--
DROP TABLE IF EXISTS `servicios_no_adm`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `servicios_no_adm`  AS  select `servicios`.`FOLIO_S` AS `FOLIO_S`,`servicios`.`FECHALLEGADA_S` AS `FECHALLEGADA_S`,`servicios`.`NOMBRECLIENTE_S` AS `NOMBRECLIENTE_S`,`servicios`.`TELEFONOCLIENTE_S` AS `TELEFONOCLIENTE_S`,`servicios`.`DESCRIPCION_S` AS `DESCRIPCION_S` from `servicios` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `servicios_no_adm_dos`
--
DROP TABLE IF EXISTS `servicios_no_adm_dos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `servicios_no_adm_dos`  AS  select `servicios`.`FOLIO_S` AS `FOLIO_S`,`servicios`.`FECHALLEGADA_S` AS `FECHALLEGADA_S`,`clientes`.`NOMBRE_CL` AS `NOMBRE_CL` from (`servicios` join `clientes`) where (`servicios`.`USERNAME_CL` = `clientes`.`USERNAME_CL`) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`USERNAME_CL`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`CODIGO_P`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`FOLIO_S`),
  ADD KEY `USERNAME_CFK_S` (`USERNAME_CL`),
  ADD KEY `USERNAME_UFK_S` (`USERNAME_US`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`USERNAME_US`),
  ADD UNIQUE KEY `EMAIL_US` (`EMAIL_US`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`FOLIO_V`),
  ADD KEY `USERNAME_FK_V` (`USERNAME_US`),
  ADD KEY `USERNAME_CFK_V` (`USERNAME_CL`);

--
-- Indices de la tabla `ventas_producto`
--
ALTER TABLE `ventas_producto`
  ADD PRIMARY KEY (`ID_VP`),
  ADD KEY `FOLIO_V_P` (`FOLIO_V`),
  ADD KEY `CODIGO_P_VP` (`CODIGO_P`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ventas_producto`
--
ALTER TABLE `ventas_producto`
  MODIFY `ID_VP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD CONSTRAINT `USERNAME_CFK_S` FOREIGN KEY (`USERNAME_CL`) REFERENCES `clientes` (`USERNAME_CL`),
  ADD CONSTRAINT `USERNAME_UFK_S` FOREIGN KEY (`USERNAME_US`) REFERENCES `usuarios` (`USERNAME_US`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `USERNAME_CFK_V` FOREIGN KEY (`USERNAME_CL`) REFERENCES `clientes` (`USERNAME_CL`),
  ADD CONSTRAINT `USERNAME_FK_V` FOREIGN KEY (`USERNAME_US`) REFERENCES `usuarios` (`USERNAME_US`);

--
-- Filtros para la tabla `ventas_producto`
--
ALTER TABLE `ventas_producto`
  ADD CONSTRAINT `CODIGO_P_VP` FOREIGN KEY (`CODIGO_P`) REFERENCES `productos` (`CODIGO_P`),
  ADD CONSTRAINT `FOLIO_V_P` FOREIGN KEY (`FOLIO_V`) REFERENCES `ventas` (`FOLIO_V`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
