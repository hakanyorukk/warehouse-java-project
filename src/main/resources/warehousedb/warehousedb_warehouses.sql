-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: warehousedb
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `warehouses`
--

DROP TABLE IF EXISTS `warehouses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `dimensions` varchar(255) DEFAULT NULL,
  `area` double DEFAULT NULL,
  `dateCreated` date DEFAULT NULL,
  `owner_id` int NOT NULL,
  `climatic_conditions` enum('AMBIENT','COOLED','HEATED','HUMIDITY_CONTROLLED') DEFAULT NULL,
  `agent_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_owners` (`owner_id`),
  KEY `fk_warehous_agent_id` (`agent_id`),
  CONSTRAINT `fk_owners` FOREIGN KEY (`owner_id`) REFERENCES `warehouse_owners` (`owner_id`),
  CONSTRAINT `fk_warehous_agent_id` FOREIGN KEY (`agent_id`) REFERENCES `warehouse_agents` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouses`
--

LOCK TABLES `warehouses` WRITE;
/*!40000 ALTER TABLE `warehouses` DISABLE KEYS */;
INSERT INTO `warehouses` VALUES (1,'Warehouse A','123 Industrial Street','50x30m',150,'2024-01-15',1,NULL,NULL),(2,'Warehouse B','456 Storage Avenue','40x25m',100,'2024-02-20',1,NULL,NULL),(3,'Warehouse C','890 test','40 50',50,'2025-11-28',2,'HUMIDITY_CONTROLLED',2),(4,'Warehouse D','fadsf','45',10,'2025-11-28',3,'HUMIDITY_CONTROLLED',2),(5,'Warehouse test','test avenue','90 30',55,'2025-11-28',3,'COOLED',3),(6,'Test 123','123','123',123,'2025-11-28',1,'AMBIENT',3),(7,'New warehouse','123','30m',30,'2025-11-28',1,'AMBIENT',3),(8,'Test','test','test',40,'2025-11-28',3,'HUMIDITY_CONTROLLED',3),(9,'Test','teset','etw',40,'2025-11-28',2,'AMBIENT',1),(10,'Test Warehouse','new address','30 40',250,'2025-11-29',1,'HUMIDITY_CONTROLLED',4),(11,'Warehouse with multiple agents','Test Address','430 20',245.96,'2025-11-29',2,'HUMIDITY_CONTROLLED',NULL);
/*!40000 ALTER TABLE `warehouses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-30 14:47:51
