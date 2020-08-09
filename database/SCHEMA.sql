CREATE DATABASE  IF NOT EXISTS `thesis-proj-schema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `thesis-proj-schema`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: thesis-proj-schema
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `allergies`
--

DROP TABLE IF EXISTS `allergies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergies` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `additionalinfo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergies`
--

LOCK TABLES `allergies` WRITE;
/*!40000 ALTER TABLE `allergies` DISABLE KEYS */;
/*!40000 ALTER TABLE `allergies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctorid` int NOT NULL,
  `patientid` int NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doctorid_idx` (`doctorid`),
  KEY `parientid_idx` (`id`,`patientid`),
  KEY `patientid_idx` (`patientid`),
  KEY `patientidfk_idx` (`patientid`),
  CONSTRAINT `appointments_doctorid` FOREIGN KEY (`doctorid`) REFERENCES `staff` (`id`),
  CONSTRAINT `appointments_patientid` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customername` varchar(45) NOT NULL,
  `customeraddress` varchar(100) NOT NULL,
  `customerphone` varchar(14) DEFAULT NULL,
  `google_maps_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customername` (`customername`,`customeraddress`,`customerphone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'INITIAL','some address','some phoen','....');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagonoses`
--

DROP TABLE IF EXISTS `diagonoses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagonoses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctorid` int NOT NULL,
  `patientid` int NOT NULL,
  `items` varchar(45) DEFAULT NULL,
  `examinationid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `diagnoses_examinationid` (`examinationid`),
  KEY `diagnoses_doctorid` (`doctorid`),
  KEY `diagnoses_patientid` (`patientid`),
  CONSTRAINT `diagnoses_doctorid` FOREIGN KEY (`doctorid`) REFERENCES `staff` (`id`),
  CONSTRAINT `diagnoses_examinationid` FOREIGN KEY (`examinationid`) REFERENCES `examinations` (`id`),
  CONSTRAINT `diagnoses_patientid` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagonoses`
--

LOCK TABLES `diagonoses` WRITE;
/*!40000 ALTER TABLE `diagonoses` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagonoses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examinations`
--

DROP TABLE IF EXISTS `examinations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examinations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `symptoms` varchar(45) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  `bloodpressure` varchar(45) DEFAULT NULL,
  `heartrate` int DEFAULT NULL,
  `appointmentid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `examinations_appointmentid` (`appointmentid`),
  CONSTRAINT `examinations_appointmentid` FOREIGN KEY (`appointmentid`) REFERENCES `appointments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examinations`
--

LOCK TABLES `examinations` WRITE;
/*!40000 ALTER TABLE `examinations` DISABLE KEYS */;
/*!40000 ALTER TABLE `examinations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familyantecedents`
--

DROP TABLE IF EXISTS `familyantecedents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familyantecedents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patientid` int NOT NULL,
  `kinshipdegree` int DEFAULT NULL,
  `diseases` text,
  `details` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `patientidantecfk_idx` (`patientid`),
  CONSTRAINT `familyantecedents_patientid` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familyantecedents`
--

LOCK TABLES `familyantecedents` WRITE;
/*!40000 ALTER TABLE `familyantecedents` DISABLE KEYS */;
/*!40000 ALTER TABLE `familyantecedents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labanalysis`
--

DROP TABLE IF EXISTS `labanalysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `labanalysis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patientid` int NOT NULL,
  `contents` text NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `patientidanlysfk_idx` (`patientid`),
  CONSTRAINT `labanalysis_patientid` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labanalysis`
--

LOCK TABLES `labanalysis` WRITE;
/*!40000 ALTER TABLE `labanalysis` DISABLE KEYS */;
/*!40000 ALTER TABLE `labanalysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientprescriptions`
--

DROP TABLE IF EXISTS `patientprescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patientprescriptions` (
  `prescriptionid` int NOT NULL,
  `patientid` int NOT NULL,
  `doctorid` int NOT NULL,
  PRIMARY KEY (`prescriptionid`,`patientid`,`doctorid`),
  KEY `prescriptionidfk_idx` (`prescriptionid`),
  KEY `patientidfk_idx` (`patientid`),
  KEY `doctoridprescfk_idx` (`doctorid`),
  CONSTRAINT `doctoridprescfk` FOREIGN KEY (`doctorid`) REFERENCES `staff` (`id`),
  CONSTRAINT `patientidprescfk` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`),
  CONSTRAINT `prescriptionid` FOREIGN KEY (`prescriptionid`) REFERENCES `prescriptions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientprescriptions`
--

LOCK TABLES `patientprescriptions` WRITE;
/*!40000 ALTER TABLE `patientprescriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientprescriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `telephone` varchar(14) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birthday` date NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `firstname` (`first_name`,`last_name`),
  KEY `patients_userid_idx` (`user_id`),
  CONSTRAINT `patients_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientsallergies`
--

DROP TABLE IF EXISTS `patientsallergies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patientsallergies` (
  `allergyid` int NOT NULL,
  `patientid` int NOT NULL,
  KEY `allergyid_idx` (`allergyid`),
  KEY `patientid_idx` (`patientid`),
  CONSTRAINT `patientsallergies_allergyid` FOREIGN KEY (`allergyid`) REFERENCES `allergies` (`id`),
  CONSTRAINT `patientsallergies_patientid` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientsallergies`
--

LOCK TABLES `patientsallergies` WRITE;
/*!40000 ALTER TABLE `patientsallergies` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientsallergies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientsdetails`
--

DROP TABLE IF EXISTS `patientsdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patientsdetails` (
  `id` int NOT NULL,
  `blood_type` varchar(6) NOT NULL,
  `blood_rh` varchar(45) NOT NULL,
  `current_weight` double DEFAULT NULL,
  `current_height` double DEFAULT NULL,
  `bmi` double DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `patientsdetails_patientid_idx` (`patient_id`),
  CONSTRAINT `patientsdetails_patientid` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientsdetails`
--

LOCK TABLES `patientsdetails` WRITE;
/*!40000 ALTER TABLE `patientsdetails` DISABLE KEYS */;
INSERT INTO `patientsdetails` VALUES (1,'AB','+',68,170,NULL,NULL),(2,'A2','-',68,170,NULL,NULL),(3,'AAA','A',68,170,NULL,NULL),(4,'t','t',1,2,NULL,NULL),(5,'t','t',68,170,0.002352941176470588,NULL),(6,'t','t',68,1.7,23.529411764705884,NULL),(7,'t','t',68,1.7,23.5,NULL),(8,'AB','Positive',68,170,0.2,NULL),(9,'t','t',68,170,23.5,NULL);
/*!40000 ALTER TABLE `patientsdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`user123`@`%`*/ /*!50003 TRIGGER `patientsdetails_BEFORE_INSERT` BEFORE INSERT ON `patientsdetails` FOR EACH ROW BEGIN
	set @calculated1 = new.current_weight;
    set @HeightInCm = new.current_height / 100;
    
    set @calculated2 = @HeightInCm * @HeightInCm;
	set @calculated = @calculated1 / @calculated2;
    
    set new.bmi = truncate(@calculated, 1);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `patientshistory`
--

DROP TABLE IF EXISTS `patientshistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patientshistory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patientid` int DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `bloodpressure` varchar(45) DEFAULT NULL,
  `heartrate` varchar(45) DEFAULT NULL,
  `diseases` varchar(45) DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `patientidfk_idx` (`patientid`),
  CONSTRAINT `patientshistory_patientid` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientshistory`
--

LOCK TABLES `patientshistory` WRITE;
/*!40000 ALTER TABLE `patientshistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientshistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescriptions`
--

DROP TABLE IF EXISTS `prescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescriptions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `items` varchar(45) NOT NULL,
  `patient_id` int NOT NULL,
  `examination_id` int NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prescriptions_patientid_idx` (`patient_id`),
  KEY `prescriptions_examinationid_idx` (`examination_id`),
  CONSTRAINT `prescriptions_examinationid` FOREIGN KEY (`examination_id`) REFERENCES `examinations` (`id`),
  CONSTRAINT `prescriptions_patientid` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescriptions`
--

LOCK TABLES `prescriptions` WRITE;
/*!40000 ALTER TABLE `prescriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'APP_ADMIN'),(2,'OFFICE_ADMIN'),(3,'DOCTOR'),(4,'NURSE');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `telephone` varchar(14) DEFAULT NULL,
  `birthday` date NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `firstname` (`first_name`,`last_name`),
  KEY `staff_userid_idx` (`user_id`),
  CONSTRAINT `staff_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `using2fa` tinyint NOT NULL DEFAULT '0',
  `roleid` int NOT NULL,
  `secretkey` varchar(100) DEFAULT NULL,
  `customerid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `users_roleid_idx` (`roleid`),
  KEY `users_customerid_idx` (`customerid`),
  CONSTRAINT `users_customerid` FOREIGN KEY (`customerid`) REFERENCES `customers` (`id`),
  CONSTRAINT `users_roleid` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'first@mailinator.com','123456',0,4,NULL,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'thesis-proj-schema'
--

--
-- Dumping routines for database 'thesis-proj-schema'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-21 16:01:22
