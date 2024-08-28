--
-- Base de datos: `practicaweb-java`
--
CREATE DATABASE IF NOT EXISTS `practicaweb-java`;
USE `practicaweb-java`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `IdProducto` int NOT NULL AUTO_INCREMENT,
  `ClaveProducto` varchar(50) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `IdTipoProducto` int DEFAULT NULL,
  PRIMARY KEY (`IdProducto`),
  UNIQUE KEY `ClaveProducto` (`ClaveProducto`),
  KEY `IdTipoProducto` (`IdTipoProducto`)
) ENGINE=MyISAM AUTO_INCREMENT=702 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`IdProducto`, `ClaveProducto`, `Nombre`, `Precio`, `IdTipoProducto`) VALUES
(1, 'PAL122', 'Palmolive', 19.00, 1),
(201, 'PIN123', 'Pinol', 22.00, 2),
(701, 'FAB0111', 'Fabuloso', 22.00, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productoproveedor`
--

DROP TABLE IF EXISTS `productoproveedor`;
CREATE TABLE IF NOT EXISTS `productoproveedor` (
  `IdProductoProveedor` int NOT NULL AUTO_INCREMENT,
  `IdProducto` int DEFAULT NULL,
  `IdProveedor` int DEFAULT NULL,
  `ClaveProductoProveedor` varchar(50) NOT NULL,
  `Costo` decimal(10,2) NOT NULL,
  PRIMARY KEY (`IdProductoProveedor`),
  UNIQUE KEY `IdProducto` (`IdProducto`,`IdProveedor`),
  KEY `IdProveedor` (`IdProveedor`)
) ENGINE=MyISAM AUTO_INCREMENT=703 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `productoproveedor`
--

INSERT INTO `productoproveedor` (`IdProductoProveedor`, `IdProducto`, `IdProveedor`, `ClaveProductoProveedor`, `Costo`) VALUES
(9, 1, 3, 'PAL012', 11.00),
(702, 701, 3, 'FAB0012', 12.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `IdProveedor` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` text,
  PRIMARY KEY (`IdProveedor`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`IdProveedor`, `Nombre`, `Descripcion`) VALUES
(1, 'Distribuidora Mexico', 'Distribuidora Mexico'),
(3, 'Abarrotes a Granel Ruiz', 'Abarrotes a Granel Ruiz'),
(4, 'Surtidora La Morena', 'Surtidora La Morena');


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoproducto`
--

DROP TABLE IF EXISTS `tipoproducto`;
CREATE TABLE IF NOT EXISTS `tipoproducto` (
  `IdTipoProducto` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` text,
  PRIMARY KEY (`IdTipoProducto`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipoproducto`
--

INSERT INTO `tipoproducto` (`IdTipoProducto`, `Nombre`, `Descripcion`) VALUES
(1, 'Limpieza', 'Productos de Limpieza'),
(2, 'Limpieza123', 'Productos de Limpieza');
COMMIT;