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
  PRIMARY KEY (`a_id`),
  KEY `a_body_type_fk` (`a_body_id`),
  CONSTRAINT `a_body_type_fk` FOREIGN KEY (`a_body_id`) REFERENCES `au_body_type` (`bt_id`) ON DELETE RESTRICT,
  CONSTRAINT `au_automobiles_chk_1` CHECK ((`a_place_count` > 0)),
  CONSTRAINT `au_automobiles_chk_2` CHECK ((`a_prod_year` > 1800))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_automobiles`
--

LOCK TABLES `au_automobiles` WRITE;
/*!40000 ALTER TABLE `au_automobiles` DISABLE KEYS */;
INSERT INTO `au_automobiles` VALUES (1,'Toyota','Camry',1,5,2022),(2,'Ford','Mustang',3,4,2021),(3,'Tesla','Model S',2,5,2023),(8,'Nissan','Juke',2,5,2021);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
-- Table structure for table `au_contracts`
--

DROP TABLE IF EXISTS `au_contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_contracts` (
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
  CONSTRAINT `c_equip_fk` FOREIGN KEY (`c_auto_id`, `c_equip_id`) REFERENCES `au_equipments` (`e_auto_id`, `e_id`),
  CONSTRAINT `c_user_fk` FOREIGN KEY (`c_user_id`) REFERENCES `au_users` (`u_id`),
  CONSTRAINT `c_warranty_fk` FOREIGN KEY (`c_warranty_id`) REFERENCES `au_warranties` (`w_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_contracts`
--

LOCK TABLES `au_contracts` WRITE;
/*!40000 ALTER TABLE `au_contracts` DISABLE KEYS */;
INSERT INTO `au_contracts` VALUES (2,2,1,1,2,2,'2024-12-15 16:00:19'),(3,3,1,1,3,3,'2024-12-15 16:00:19'),(4,4,2,2,1,1,'2024-12-15 16:00:19'),(5,5,2,2,2,2,'2024-12-15 16:00:19'),(6,6,2,2,3,3,'2024-12-15 16:00:19'),(7,7,3,3,1,1,'2024-12-15 16:00:19'),(8,8,3,3,2,2,'2024-12-15 16:00:19'),(9,9,3,3,3,3,'2024-12-15 16:00:19'),(10,10,1,1,1,1,'2024-12-15 16:00:19');
/*!40000 ALTER TABLE `au_contracts` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_dealers`
--

LOCK TABLES `au_dealers` WRITE;
/*!40000 ALTER TABLE `au_dealers` DISABLE KEYS */;
INSERT INTO `au_dealers` VALUES (1,'Auto Star','Cuza Voda 3/2','+37368742637','+37368742742'),(2,'Mercedes-Benz','Dacia 20/9','+37354967423','+37354969741'),(3,'DAAC Hermes','Petricani 37/1','+37369658247','+37369652475');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
-- Table structure for table `au_equipments`
--

DROP TABLE IF EXISTS `au_equipments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_equipments` (
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
  `e_weight` int NOT NULL,
  `e_price` decimal(9,2) NOT NULL,
  `e_image` varchar(255) DEFAULT NULL,
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
  CONSTRAINT `e_susp_fk` FOREIGN KEY (`e_susp_id`) REFERENCES `au_suspension_type` (`st_id`),
  CONSTRAINT `au_equipments_chk_1` CHECK ((`e_horse_power` > 0)),
  CONSTRAINT `au_equipments_chk_2` CHECK ((`e_speed_count` > 0)),
  CONSTRAINT `au_equipments_chk_3` CHECK ((`e_weight` > 0)),
  CONSTRAINT `au_equipments_chk_4` CHECK ((`e_price` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_equipments`
--

LOCK TABLES `au_equipments` WRITE;
/*!40000 ALTER TABLE `au_equipments` DISABLE KEYS */;
INSERT INTO `au_equipments` VALUES (1,1,'Standard','V6',1,3.5,301,1,2,2,6,1,'Fabric','None',1500,25000.00,NULL),(1,2,'Sport','V6',1,3.5,301,2,2,2,6,1,'Leather','Sport',1550,27000.00,NULL),(1,3,'Luxury','V6',1,3.5,301,1,2,2,6,1,'Leather','Luxury',1600,30000.00,NULL),(2,1,'EcoBoost','V6',1,2.3,310,2,3,2,6,1,'Fabric','Standard',1400,26000.00,NULL),(2,2,'GT','V8',2,5.0,450,2,3,2,6,1,'Leather','Sport',1550,35000.00,NULL),(2,3,'Shelby','V8',2,5.2,526,2,3,2,6,1,'Leather','Sport',1700,50000.00,NULL),(3,1,'Standard Range','Electric',3,0.0,670,1,1,2,1,3,'Fabric','None',2100,79990.00,NULL),(3,2,'Long Range','Electric',3,0.0,670,1,1,2,1,3,'Leather','Standard',2150,89990.00,NULL),(3,3,'Plaid','Electric',3,0.0,1020,1,1,2,1,3,'Leather','Sport',2200,129990.00,NULL);
/*!40000 ALTER TABLE `au_equipments` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
-- Table structure for table `au_suspension_type`
--

DROP TABLE IF EXISTS `au_suspension_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_suspension_type` (
  `st_id` int NOT NULL AUTO_INCREMENT,
  `st_name` varchar(50) NOT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_suspension_type`
--

LOCK TABLES `au_suspension_type` WRITE;
/*!40000 ALTER TABLE `au_suspension_type` DISABLE KEYS */;
INSERT INTO `au_suspension_type` VALUES (1,'Standard'),(2,'Sport'),(3,'Off-Road');
/*!40000 ALTER TABLE `au_suspension_type` ENABLE KEYS */;
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
  `u_role` varchar(50) DEFAULT 'user',
  `u_password` varchar(50) NOT NULL,
  `u_name` varchar(40) NOT NULL,
  `u_surname` varchar(40) NOT NULL,
  `u_birthday` date NOT NULL,
  `u_email` varchar(50) NOT NULL,
  `u_telephone` char(12) NOT NULL,
  `u_address` varchar(70) NOT NULL,
  `u_avatar` varchar(1000) NOT NULL DEFAULT 'images/deafult.png',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_login` (`u_login`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_users`
--

LOCK TABLES `au_users` WRITE;
/*!40000 ALTER TABLE `au_users` DISABLE KEYS */;
INSERT INTO `au_users` VALUES (1,'user1','user','pass1','John','Doe','1985-01-01','john.doe@example.com','123456789012','123 Elm Street','images/deafult.png'),(2,'user2','user','pass2','Jane','Smith','1990-02-02','jane.smith@example.com','123456789013','456 Oak Avenue','images/deafult.png'),(3,'user3','user','pass3','Bob','Brown','1988-03-03','bob.brown@example.com','123456789014','789 Pine Road','images/deafult.png'),(4,'user4','user','pass4','Alice','Johnson','1992-04-04','alice.j@example.com','123456789015','101 Maple Lane','images/deafult.png'),(5,'user5','user','pass5','Tom','Davis','1985-05-05','tom.d@example.com','123456789016','202 Cedar Blvd','images/deafult.png'),(6,'user6','user','pass6','Lisa','Wilson','1983-06-06','lisa.w@example.com','123456789017','303 Birch St','images/deafult.png'),(7,'user7','user','pass7','Mark','Taylor','1979-07-07','mark.t@example.com','123456789018','404 Cherry Ave','images/deafult.png'),(8,'user8','user','pass8','Sara','Lee','1995-08-08','sara.l@example.com','123456789019','505 Spruce Rd','images/deafult.png'),(9,'user9','user','pass9','Paul','Walker','1981-09-09','paul.w@example.com','123456789020','606 Willow Dr','images/deafult.png'),(10,'user10','user','pass10','Emma','Harris','1993-10-10','emma.h@example.com','123456789021','707 Ash St','images/deafult.png'),(14,'vaxa','admin','admin','Vagid','Zibliuc','2005-05-23','vaxa@gmail.com','+37367292196','gde-to','images/deafult.png'),(15,'abdul','user','qwerty','Abdul','Amar','2005-12-05','abdul@gmail.com','+3736852341','Somwhere','images/deafult.png');
/*!40000 ALTER TABLE `au_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `au_warranties`
--

DROP TABLE IF EXISTS `au_warranties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `au_warranties` (
  `w_id` int NOT NULL AUTO_INCREMENT,
  `w_name` varchar(50) NOT NULL,
  `w_duration` int NOT NULL,
  `w_price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`w_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `au_warranties`
--

LOCK TABLES `au_warranties` WRITE;
/*!40000 ALTER TABLE `au_warranties` DISABLE KEYS */;
INSERT INTO `au_warranties` VALUES (1,'Basic Warranty',24,500.00),(2,'Extended Warranty',36,750.00),(3,'Premium Warranty',48,1000.00);
/*!40000 ALTER TABLE `au_warranties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'autoland_db'
--

--
-- Dumping routines for database 'autoland_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-26 22:27:17
