-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: autoland_db
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `au_automobiles`
--

DROP TABLE IF EXISTS `au_automobiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_automobiles` (
  `a_id` int NOT NULL AUTO_INCREMENT,
  `a_mark` varchar(50) NOT NULL,
  `a_model` varchar(50) NOT NULL,
  `a_body_id` int NOT NULL,
  `a_place_count` tinyint NOT NULL,
  `a_prod_year` int NOT NULL,
  `a_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  KEY `a_body_type_fk` (`a_body_id`),
  CONSTRAINT `a_body_type_fk` FOREIGN KEY (`a_body_id`) REFERENCES `au_body_type` (`bt_id`) ON DELETE RESTRICT,
  CONSTRAINT `au_automobiles_chk_1` CHECK ((`a_place_count` > 0)),
  CONSTRAINT `au_automobiles_chk_2` CHECK ((`a_prod_year` > 1800))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_automobiles`
--

LOCK TABLES `au_automobiles` WRITE;
/*!40000 ALTER TABLE `au_automobiles` DISABLE KEYS */;
INSERT INTO `au_automobiles` VALUES (1,'Toyota','Camry',1,5,2022,'src/resources/car_images/new_camry.jpg'),(2,'Ford','Mustang',3,4,2021,'src/resources/car_images/mustang.jpg'),(3,'Tesla','Model S',2,5,2023,'src/resources/car_images/models.jpg');
/*!40000 ALTER TABLE `au_automobiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_body_type`
--

DROP TABLE IF EXISTS `au_body_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_body_type` (
  `bt_id` int NOT NULL AUTO_INCREMENT,
  `bt_name` varchar(50) NOT NULL,
  PRIMARY KEY (`bt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_body_type`
--

LOCK TABLES `au_body_type` WRITE;
/*!40000 ALTER TABLE `au_body_type` DISABLE KEYS */;
INSERT INTO `au_body_type` VALUES (1,'Sedan'),(2,'SUV'),(3,'Coupe');
/*!40000 ALTER TABLE `au_body_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_contract`
--

DROP TABLE IF EXISTS `au_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_contract` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_user_id` int NOT NULL,
  `c_dealer_id` int NOT NULL,
  `c_auto_id` int NOT NULL,
  `c_equip_id` int NOT NULL,
  `c_warranty_id` int NOT NULL,
  `c_data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`c_id`),
  KEY `c_user_fk` (`c_user_id`),
  KEY `c_dealer_fk` (`c_dealer_id`),
  KEY `c_equip_fk` (`c_auto_id`,`c_equip_id`),
  KEY `c_warranty_fk` (`c_warranty_id`),
  CONSTRAINT `c_auto_fk` FOREIGN KEY (`c_auto_id`) REFERENCES `au_automobiles` (`a_id`),
  CONSTRAINT `c_dealer_fk` FOREIGN KEY (`c_dealer_id`) REFERENCES `au_dealers` (`d_id`),
  CONSTRAINT `c_equip_fk` FOREIGN KEY (`c_auto_id`, `c_equip_id`) REFERENCES `au_equipment` (`e_auto_id`, `e_id`),
  CONSTRAINT `c_user_fk` FOREIGN KEY (`c_user_id`) REFERENCES `au_users` (`u_id`),
  CONSTRAINT `c_warranty_fk` FOREIGN KEY (`c_warranty_id`) REFERENCES `au_warranty` (`w_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_contract`
--

LOCK TABLES `au_contract` WRITE;
/*!40000 ALTER TABLE `au_contract` DISABLE KEYS */;
INSERT INTO `au_contract` VALUES (1,1,1,1,1,1,'2024-11-22 13:17:15'),(2,2,1,1,2,2,'2024-11-22 13:17:15'),(3,3,1,1,3,3,'2024-11-22 13:17:15'),(4,4,2,2,1,1,'2024-11-22 13:17:15'),(5,5,2,2,2,2,'2024-11-22 13:17:15'),(6,6,2,2,3,3,'2024-11-22 13:17:15'),(7,7,3,3,1,1,'2024-11-22 13:17:15'),(8,8,3,3,2,2,'2024-11-22 13:17:15'),(9,9,3,3,3,3,'2024-11-22 13:17:15'),(10,10,1,1,1,1,'2024-11-22 13:17:15'),(12,14,1,1,3,1,'2024-12-03 23:03:08');
/*!40000 ALTER TABLE `au_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_dealers`
--

DROP TABLE IF EXISTS `au_dealers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_dealers` (
  `d_id` int NOT NULL AUTO_INCREMENT,
  `d_name` varchar(40) NOT NULL,
  `d_address` varchar(70) NOT NULL,
  `d_telephone` char(12) NOT NULL,
  `d_fax` char(12) NOT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_dealers`
--

LOCK TABLES `au_dealers` WRITE;
/*!40000 ALTER TABLE `au_dealers` DISABLE KEYS */;
INSERT INTO `au_dealers` VALUES (1,'Auto Dealer A','101 Main St','1112223333','1112223334'),(2,'Auto Dealer B','202 Central Ave','2223334444','2223334445'),(3,'Auto Dealer C','303 West Rd','3334445555','3334445556');
/*!40000 ALTER TABLE `au_dealers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_drive_type`
--

DROP TABLE IF EXISTS `au_drive_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_drive_type` (
  `dt_id` int NOT NULL AUTO_INCREMENT,
  `dt_name` varchar(50) NOT NULL,
  PRIMARY KEY (`dt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_drive_type`
--

LOCK TABLES `au_drive_type` WRITE;
/*!40000 ALTER TABLE `au_drive_type` DISABLE KEYS */;
INSERT INTO `au_drive_type` VALUES (1,'AWD'),(2,'FWD'),(3,'RWD');
/*!40000 ALTER TABLE `au_drive_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_engine_type`
--

DROP TABLE IF EXISTS `au_engine_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_engine_type` (
  `et_id` int NOT NULL AUTO_INCREMENT,
  `et_name` varchar(50) NOT NULL,
  PRIMARY KEY (`et_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_engine_type`
--

LOCK TABLES `au_engine_type` WRITE;
/*!40000 ALTER TABLE `au_engine_type` DISABLE KEYS */;
INSERT INTO `au_engine_type` VALUES (1,'V6'),(2,'V8'),(3,'Electric');
/*!40000 ALTER TABLE `au_engine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_equipment`
--

DROP TABLE IF EXISTS `au_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_equipment` (
  `e_auto_id` int NOT NULL,
  `e_id` int NOT NULL,
  `e_name` varchar(70) NOT NULL DEFAULT 'default',
  `e_engine_name` varchar(70) NOT NULL,
  `e_engine_id` int NOT NULL,
  `e_engine_volume` decimal(3,1) NOT NULL,
  `e_horse_power` int NOT NULL,
  `e_susp_id` int NOT NULL,
  `e_drive_id` int NOT NULL,
  `e_gearbox_id` int NOT NULL,
  `e_speed_count` int NOT NULL,
  `e_fuel_id` int NOT NULL,
  `e_interior` varchar(70) NOT NULL,
  `e_body_kit` varchar(70) NOT NULL,
  `e_weigth` int NOT NULL,
  `e_price` decimal(9,2) NOT NULL,
  PRIMARY KEY (`e_auto_id`,`e_id`),
  KEY `e_engine_fk` (`e_engine_id`),
  KEY `e_susp_fk` (`e_susp_id`),
  KEY `e_drive_fk` (`e_drive_id`),
  KEY `e_gearbox_fk` (`e_gearbox_id`),
  KEY `e_fuel_fk` (`e_fuel_id`),
  CONSTRAINT `e_auto_fk` FOREIGN KEY (`e_auto_id`) REFERENCES `au_automobiles` (`a_id`),
  CONSTRAINT `e_drive_fk` FOREIGN KEY (`e_drive_id`) REFERENCES `au_drive_type` (`dt_id`),
  CONSTRAINT `e_engine_fk` FOREIGN KEY (`e_engine_id`) REFERENCES `au_engine_type` (`et_id`),
  CONSTRAINT `e_fuel_fk` FOREIGN KEY (`e_fuel_id`) REFERENCES `au_fuel_type` (`ft_id`),
  CONSTRAINT `e_gearbox_fk` FOREIGN KEY (`e_gearbox_id`) REFERENCES `au_gearbox_type` (`gt_id`),
  CONSTRAINT `e_susp_fk` FOREIGN KEY (`e_susp_id`) REFERENCES `au_suspensive_type` (`st_id`),
  CONSTRAINT `au_equipment_chk_1` CHECK ((`e_horse_power` > 0)),
  CONSTRAINT `au_equipment_chk_2` CHECK ((`e_speed_count` > 0)),
  CONSTRAINT `au_equipment_chk_3` CHECK ((`e_weigth` > 0)),
  CONSTRAINT `au_equipment_chk_4` CHECK ((`e_price` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_equipment`
--

LOCK TABLES `au_equipment` WRITE;
/*!40000 ALTER TABLE `au_equipment` DISABLE KEYS */;
INSERT INTO `au_equipment` VALUES (1,1,'Standard','V6',1,3.5,301,1,2,2,6,1,'Fabric','None',1500,25000.00),(1,2,'Sport','V6',1,3.5,301,2,2,2,6,1,'Leather','Sport',1550,27000.00),(1,3,'Luxury','V6',1,3.5,301,1,2,2,6,1,'Leather','Luxury',1600,30000.00),(2,1,'EcoBoost','V6',1,2.3,310,2,3,2,6,1,'Fabric','Standard',1400,26000.00),(2,2,'GT','V8',2,5.0,450,2,3,2,6,1,'Leather','Sport',1550,35000.00),(2,3,'Shelby','V8',2,5.2,526,2,3,2,6,1,'Leather','Sport',1700,50000.00),(3,1,'Standard Range','Electric',3,0.0,670,1,1,2,1,3,'Fabric','None',2100,79990.00),(3,2,'Long Range','Electric',3,0.0,670,1,1,2,1,3,'Leather','Standard',2150,89990.00),(3,3,'Plaid','Electric',3,0.0,1020,1,1,2,1,3,'Leather','Sport',2200,129990.00);
/*!40000 ALTER TABLE `au_equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_fuel_type`
--

DROP TABLE IF EXISTS `au_fuel_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_fuel_type` (
  `ft_id` int NOT NULL AUTO_INCREMENT,
  `ft_name` varchar(50) NOT NULL,
  PRIMARY KEY (`ft_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_fuel_type`
--

LOCK TABLES `au_fuel_type` WRITE;
/*!40000 ALTER TABLE `au_fuel_type` DISABLE KEYS */;
INSERT INTO `au_fuel_type` VALUES (1,'Petrol'),(2,'Diesel'),(3,'Electric');
/*!40000 ALTER TABLE `au_fuel_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_gearbox_type`
--

DROP TABLE IF EXISTS `au_gearbox_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_gearbox_type` (
  `gt_id` int NOT NULL AUTO_INCREMENT,
  `gt_name` varchar(50) NOT NULL,
  PRIMARY KEY (`gt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_gearbox_type`
--

LOCK TABLES `au_gearbox_type` WRITE;
/*!40000 ALTER TABLE `au_gearbox_type` DISABLE KEYS */;
INSERT INTO `au_gearbox_type` VALUES (1,'Manual'),(2,'Automatic'),(3,'CVT');
/*!40000 ALTER TABLE `au_gearbox_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_suspensive_type`
--

DROP TABLE IF EXISTS `au_suspensive_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_suspensive_type` (
  `st_id` int NOT NULL AUTO_INCREMENT,
  `st_name` varchar(50) NOT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_suspensive_type`
--

LOCK TABLES `au_suspensive_type` WRITE;
/*!40000 ALTER TABLE `au_suspensive_type` DISABLE KEYS */;
INSERT INTO `au_suspensive_type` VALUES (1,'Standard'),(2,'Sport'),(3,'Off-Road');
/*!40000 ALTER TABLE `au_suspensive_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_users`
--

DROP TABLE IF EXISTS `au_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_users` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `u_login` varchar(50) NOT NULL,
  `u_password` varchar(50) NOT NULL,
  `u_name` varchar(40) NOT NULL,
  `u_surname` varchar(40) NOT NULL,
  `u_birthday` date NOT NULL,
  `u_email` varchar(50) NOT NULL,
  `u_telephone` char(12) NOT NULL,
  `u_address` varchar(70) NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_login` (`u_login`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_users`
--

LOCK TABLES `au_users` WRITE;
/*!40000 ALTER TABLE `au_users` DISABLE KEYS */;
INSERT INTO `au_users` VALUES (1,'user1','pass1','John','Doe','1985-01-01','john.doe@example.com','123456789012','123 Elm Street'),(2,'user2','pass2','Jane','Smith','1990-02-02','jane.smith@example.com','123456789013','456 Oak Avenue'),(3,'user3','pass3','Bob','Brown','1988-03-03','bob.brown@example.com','123456789014','789 Pine Road'),(4,'user4','pass4','Alice','Johnson','1992-04-04','alice.j@example.com','123456789015','101 Maple Lane'),(5,'user5','pass5','Tom','Davis','1985-05-05','tom.d@example.com','123456789016','202 Cedar Blvd'),(6,'user6','pass6','Lisa','Wilson','1983-06-06','lisa.w@example.com','123456789017','303 Birch St'),(7,'user7','pass7','Mark','Taylor','1979-07-07','mark.t@example.com','123456789018','404 Cherry Ave'),(8,'user8','pass8','Sara','Lee','1995-08-08','sara.l@example.com','123456789019','505 Spruce Rd'),(9,'user9','pass9','Paul','Walker','1981-09-09','paul.w@example.com','123456789020','606 Willow Dr'),(10,'user10','pass10','Emma','Harris','1993-10-10','emma.h@example.com','123456789021','707 Ash St'),(14,'vaxa','admin','Vagid','Zibliuc','2005-05-23','vaxa@gmail.com','+37367292196','gde-to');
/*!40000 ALTER TABLE `au_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_warranty`
--

DROP TABLE IF EXISTS `au_warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_warranty` (
  `w_id` int NOT NULL AUTO_INCREMENT,
  `w_name` varchar(50) NOT NULL,
  `w_duartion` int NOT NULL,
  `w_price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`w_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_warranty`
--

LOCK TABLES `au_warranty` WRITE;
/*!40000 ALTER TABLE `au_warranty` DISABLE KEYS */;
INSERT INTO `au_warranty` VALUES (1,'Basic Warranty',24,500.00),(2,'Extended Warranty',36,750.00),(3,'Premium Warranty',48,1000.00);
/*!40000 ALTER TABLE `au_warranty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `automobilewithbodytype`
--

DROP TABLE IF EXISTS `automobilewithbodytype`;
/*!50001 DROP VIEW IF EXISTS `automobilewithbodytype`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `automobilewithbodytype` AS SELECT 
 1 AS `a_id`,
 1 AS `a_mark`,
 1 AS `a_model`,
 1 AS `bt_name`,
 1 AS `a_place_count`,
 1 AS `a_prod_year`,
 1 AS `a_image`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `fullequipment`
--

DROP TABLE IF EXISTS `fullequipment`;
/*!50001 DROP VIEW IF EXISTS `fullequipment`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `fullequipment` AS SELECT 
 1 AS `e_auto_id`,
 1 AS `e_id`,
 1 AS `e_name`,
 1 AS `e_engine_name`,
 1 AS `et_name`,
 1 AS `e_engine_volume`,
 1 AS `e_horse_power`,
 1 AS `st_name`,
 1 AS `dt_name`,
 1 AS `gt_name`,
 1 AS `e_speed_count`,
 1 AS `ft_name`,
 1 AS `e_interior`,
 1 AS `e_body_kit`,
 1 AS `e_weigth`,
 1 AS `e_price`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'autoland_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `get_drive_type_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`autoadmin`@`localhost` PROCEDURE `get_drive_type_id`(IN drive_name VARCHAR(50))
BEGIN
	SELECT dt_id
    FROM au_drive_type
	WHERE dt_name LIKE drive_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_engine_type_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`autoadmin`@`localhost` PROCEDURE `get_engine_type_id`(IN engine_name VARCHAR(50))
BEGIN
	SELECT et_id
    FROM au_engine_type
	WHERE et_name LIKE engine_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_fuel_type_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`autoadmin`@`localhost` PROCEDURE `get_fuel_type_id`(IN fuel_name VARCHAR(50))
BEGIN
	SELECT ft_id
    FROM au_fuel_type
	WHERE ft_name LIKE fuel_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_gearbox_type_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`autoadmin`@`localhost` PROCEDURE `get_gearbox_type_id`(IN gearbox_name VARCHAR(50))
BEGIN
	SELECT gt_id
    FROM au_gearbox_type
	WHERE gt_name LIKE gearbox_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_suspensive_type_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`autoadmin`@`localhost` PROCEDURE `get_suspensive_type_id`(IN susp_name VARCHAR(50))
BEGIN
	SELECT st_id
    FROM au_suspensive_type
	WHERE st_name LIKE susp_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `automobilewithbodytype`
--

/*!50001 DROP VIEW IF EXISTS `automobilewithbodytype`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`autoadmin`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `automobilewithbodytype` AS select `au_automobiles`.`a_id` AS `a_id`,`au_automobiles`.`a_mark` AS `a_mark`,`au_automobiles`.`a_model` AS `a_model`,`au_body_type`.`bt_name` AS `bt_name`,`au_automobiles`.`a_place_count` AS `a_place_count`,`au_automobiles`.`a_prod_year` AS `a_prod_year`,`au_automobiles`.`a_image` AS `a_image` from (`au_body_type` join `au_automobiles` on((`au_body_type`.`bt_id` = `au_automobiles`.`a_body_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `fullequipment`
--

/*!50001 DROP VIEW IF EXISTS `fullequipment`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`autoadmin`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `fullequipment` AS select `au_equipment`.`e_auto_id` AS `e_auto_id`,`au_equipment`.`e_id` AS `e_id`,`au_equipment`.`e_name` AS `e_name`,`au_equipment`.`e_engine_name` AS `e_engine_name`,`au_engine_type`.`et_name` AS `et_name`,`au_equipment`.`e_engine_volume` AS `e_engine_volume`,`au_equipment`.`e_horse_power` AS `e_horse_power`,`au_suspensive_type`.`st_name` AS `st_name`,`au_drive_type`.`dt_name` AS `dt_name`,`au_gearbox_type`.`gt_name` AS `gt_name`,`au_equipment`.`e_speed_count` AS `e_speed_count`,`au_fuel_type`.`ft_name` AS `ft_name`,`au_equipment`.`e_interior` AS `e_interior`,`au_equipment`.`e_body_kit` AS `e_body_kit`,`au_equipment`.`e_weigth` AS `e_weigth`,`au_equipment`.`e_price` AS `e_price` from (((((`au_equipment` join `au_engine_type` on((`au_equipment`.`e_engine_id` = `au_engine_type`.`et_id`))) join `au_suspensive_type` on((`au_equipment`.`e_susp_id` = `au_suspensive_type`.`st_id`))) join `au_drive_type` on((`au_equipment`.`e_drive_id` = `au_drive_type`.`dt_id`))) join `au_gearbox_type` on((`au_equipment`.`e_gearbox_id` = `au_gearbox_type`.`gt_id`))) join `au_fuel_type` on((`au_equipment`.`e_fuel_id` = `au_fuel_type`.`ft_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-03 23:15:11
