-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rentit_db
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alquiler`
--

DROP TABLE IF EXISTS `alquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alquiler` (
  `alquilerID` int NOT NULL AUTO_INCREMENT,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `materialID` int DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`alquilerID`),
  KEY `materialID` (`materialID`),
  CONSTRAINT `alquiler_ibfk_2` FOREIGN KEY (`materialID`) REFERENCES `material` (`materialID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alquiler`
--

LOCK TABLES `alquiler` WRITE;
/*!40000 ALTER TABLE `alquiler` DISABLE KEYS */;
INSERT INTO `alquiler` VALUES (1,'2023-03-01','2023-03-15',1,150.00),(2,'2023-03-01','2023-03-02',2,200.00),(3,'2024-05-12','2024-05-12',1,150.00),(4,'2024-05-13','2024-05-24',1,150.00),(5,'2024-05-12','2024-05-19',2,200.00),(6,'2024-05-26','2024-05-25',1,150.00),(7,'2024-05-25','2024-06-08',1,150.00),(8,'2024-05-17','2024-05-31',1,150.00),(9,'2024-05-17','2024-05-31',1,150.00),(10,'2024-05-25','2024-05-25',1,150.00),(11,'2024-05-14','2024-05-29',1,150.00),(12,'2024-05-17','2024-05-22',2,200.00),(13,'2024-05-14','2024-05-18',1,150.00),(14,'2024-05-14','2024-05-21',1,150.00),(15,'2024-05-14','2024-05-21',1,150.00),(16,'2024-05-14','2024-05-21',1,150.00),(17,'2024-05-14','2024-05-16',1,150.00),(18,'2024-05-08','2024-05-31',2,200.00);
/*!40000 ALTER TABLE `alquiler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `categoriaID` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`categoriaID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Equipos de Elevación','Grúas y elevadores para construcción y mantenimiento'),(2,'Vehículos de Construcción','Vehículos especializados para la construcción y transporte de materiales'),(3,'Equipos de Elevación','Grúas y elevadores para construcción y mantenimiento'),(4,'Vehículos de Construcción','Vehículos especializados para la construcción y transporte de materiales'),(5,'Herramientas Manuales','Herramientas para uso manual en diversas tareas de construcción'),(6,'Equipos de Seguridad','Equipos y dispositivos para la seguridad en el trabajo'),(7,'Equipos de Demolición','Equipos utilizados para la demolición de estructuras'),(8,'Equipos de Medición','Instrumentos utilizados para medir y nivelar en la construcción'),(9,'Equipos de Acabado','Herramientas y máquinas para los acabados de construcción'),(10,'Equipos de Movimiento de Tierra','Máquinas utilizadas para mover y nivelar tierra y otros materiales'),(11,'Equipos de Perforación','Herramientas y máquinas para perforar el suelo o materiales duros'),(12,'Equipos de Limpieza','Equipos utilizados para la limpieza de obras y áreas de trabajo');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `materialID` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text,
  `precio` decimal(10,2) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `categoriaID` int DEFAULT NULL,
  PRIMARY KEY (`materialID`),
  KEY `categoriaID` (`categoriaID`),
  CONSTRAINT `material_ibfk_1` FOREIGN KEY (`categoriaID`) REFERENCES `categoria` (`categoriaID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'Grúa Torre','Grúa de torre para construcción de altura',150.00,'Disponible',1),(2,'Camión Volteo','Camión para transporte de materiales grandes',200.00,'Disponible',2),(3,'Grúa Torre','Grúa de torre para construcción de altura',150.00,'Disponible',1),(4,'Camión Volteo','Camión para transporte de materiales grandes',200.00,'Disponible',2),(5,'Excavadora','Máquina utilizada para excavaciones profundas',300.00,'Disponible',2),(6,'Andamio','Estructura temporal para sostener a trabajadores',50.00,'Disponible',1),(7,'Montacargas','Vehículo industrial utilizado para el levantamiento de materiales',250.00,'Disponible',2),(8,'Plataforma Elevadora','Equipo para elevar trabajadores a grandes alturas',100.00,'Disponible',1),(9,'Bulldozer','Máquina de construcción para mover grandes cantidades de material',400.00,'Disponible',2),(10,'Compactador','Equipo utilizado para compactar suelo y asfalto',150.00,'Disponible',2),(11,'Hormigonera','Equipo para mezclar cemento y hormigón',120.00,'Disponible',2),(12,'Cargadora Frontal','Vehículo de construcción para cargar materiales',350.00,'Disponible',2);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-08 17:06:13
