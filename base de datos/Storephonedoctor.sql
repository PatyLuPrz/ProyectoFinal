CREATE DATABASE storephonedoctor;
USE storephonedoctor;

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
('Josejose', 'jose@gmail.com', 'Josejose', 'Jose', 'Ramirez', 'Soto', '7751230748', 'Tulancingo'),
('NorbertoPaloma', 'luis@gmail.com', '66f4501149cbc7d3a40f3be7dce4b4b970666649', 'Luis', 'Rodriguez', 'Rodriguez', '7751284715', 'Tulancingo');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `inventario_no_admn`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `inventario_no_admn` (
`NOMBRE_P` varchar(50)
,`CANTIDAD_P` int(11)
,`PRECIO_VENTA_P` float
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
  `PRECIO_VENTA_P` float NOT NULL,
  `PRECIO_COMPRA_P` float NOT NULL,
  `IMG_P` text,
  `DESCRIPCION_P` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='REGISTRO DEL INVENTARIO';

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`CODIGO_P`, `NOMBRE_P`, `CATEGORIA_P`, `MARCA_P`, `CANTIDAD_P`, `PRECIO_VENTA_P`, `PRECIO_COMPRA_P`, `IMG_P`, `DESCRIPCION_P`) VALUES
('0000000000001', 'Mica Iphone 7/8', 'Mica de Vidrio ', 'Generico', 10, 50, 30, NULL, NULL),
('0000000000002', 'Funda Tab 3 Samsung 360 7\" ', 'Funda', 'Generica', 5, 150, 100, NULL, NULL),
('0000000000003', 'Funda 360 Tab A 7\"', 'Funda', 'Samsung', 5, 150, 100, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `FOLIO_S` varchar(10) NOT NULL,
  `USERNAME_US` varchar(20) NOT NULL,
  `USERNAME_CL` varchar(50) NOT NULL,
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
('0000000001', 'Administrador', 'Publico General', '2018-08-14', 'En proceso', 'Jose Ramirez Soto', '7759876543', 'Liberacion Iphone 6 Dorado At&t', 2000),
('0000000002', 'Administrador', 'Josejose', '2018-08-14', 'En proceso', 'Jose Ramirez Soto', '7759876543', 'Liberacion Iphone 6 Dorado At&t', 2000);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `servicios_no_adm`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `servicios_no_adm` (
`FOLIO_S` varchar(10)
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
`FOLIO_S` varchar(10)
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
('Administrador', 'luis_norberto1998@hotmail.com', '435b41068e8665513a20070c033b08b9c66e4332', 'Norberto', 'Paloma', 'Rodriguez', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `FOLIO_V` varchar(10) NOT NULL,
  `USERNAME_US` varchar(20) NOT NULL,
  `USERNAME_CL` varchar(50) NOT NULL,
  `FECHA_V` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IMPORTE_V` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='BITACORA DE VENTAS';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas_producto`
--

CREATE TABLE `ventas_producto` (
  `ID_VP` varchar(5) NOT NULL,
  `FOLIO_V` varchar(10) NOT NULL,
  `CODIGO_P` varchar(13) NOT NULL,
  `PRECIO_P` float NOT NULL,
  `SUBTOTAL_VP` float NOT NULL
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
  ADD PRIMARY KEY (`FOLIO_S`);

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
  ADD PRIMARY KEY (`FOLIO_V`);

--
-- Indices de la tabla `ventas_producto`
--
ALTER TABLE `ventas_producto`
  ADD PRIMARY KEY (`ID_VP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
