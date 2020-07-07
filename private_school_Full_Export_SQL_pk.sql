CREATE DATABASE  IF NOT EXISTS `privateschool` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `privateschool`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: privateschool
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `sub_date_time` datetime NOT NULL,
  `oral_mark` decimal(5,2) NOT NULL DEFAULT '0.00',
  `total_mark` decimal(5,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AS_UNIQUE` (`course_id`,`title`,`description`,`sub_date_time`),
  KEY `fk_assignments_courses_idx` (`course_id`),
  CONSTRAINT `fk_assignments_courses` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (1,1,'Private School','Java 1st assignment','2020-04-04 11:23:10',20.00,80.00),(2,3,'Private School','Java 1st assignment','2020-04-07 11:23:10',20.00,80.00),(3,2,'National Bank','C# 1st assignment','2020-05-05 10:10:20',15.00,70.00),(4,4,'National Bank','C# 1st assignment','2020-03-05 10:10:20',15.00,70.00),(5,1,'University school','Java 2nd assignment','2020-03-03 09:09:09',20.00,60.00),(6,3,'University school','Java 2nd assignment','2020-06-03 09:09:09',20.00,60.00),(7,2,'Bootcamp Training','C# 2nd assignment','2020-08-03 08:08:08',15.00,50.00),(8,4,'Bootcamp campus','C# 2nd assignment','2020-08-05 08:08:08',15.00,50.00),(9,5,'Zoo','Javascript 1st','2020-06-09 05:05:05',25.00,100.00),(10,6,'Zoo','Javascript 1st','2020-06-06 05:05:05',25.00,100.00),(11,5,'Eshop','Javascript 2nd','2020-01-08 04:04:04',25.00,90.00),(12,6,'Eshop','Javascript 2nd','2020-04-08 04:04:04',25.00,90.00),(13,1,'Efood','Java 3rd','2020-07-02 03:03:03',10.00,75.00),(14,3,'Efood shop','Java 3rd','2020-10-02 03:03:03',10.00,75.00),(15,2,'Car_Sells','C# 3rd','2020-11-01 01:01:01',30.00,100.00),(16,4,'Car Wash','C# 3rd','2020-11-11 01:01:01',30.00,100.00),(19,1,'Pharmacy Lab','Exercise on virus in java','2020-07-08 11:59:59',33.00,100.00);
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `stream` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CO_UNIQUE` (`title`,`stream`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'CB09','Java','Part-time','2020-03-03','2020-09-20'),(2,'CB13','C#','Part-time','2020-03-03','2020-09-20'),(3,'CB12','Java','Full-time','2020-03-03','2020-07-03'),(4,'CB10','C#','Full-time','2020-03-03','2020-07-03'),(5,'CB08','Javascript','Full-time','2020-04-08','2020-07-08'),(6,'CB07','Javascript','Part-time','2020-04-08','2020-10-15');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_grades`
--

DROP TABLE IF EXISTS `student_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_grades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `assignment_id` int NOT NULL,
  `st_oralmark` decimal(5,2) NOT NULL DEFAULT '0.00',
  `st_totalmark` decimal(5,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `st_ass_index` (`student_id`,`assignment_id`),
  KEY `fk_student_grades_assignments1` (`assignment_id`),
  CONSTRAINT `fk_student_grades_assignments1` FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`id`),
  CONSTRAINT `fk_student_grades_students1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_grades`
--

LOCK TABLES `student_grades` WRITE;
/*!40000 ALTER TABLE `student_grades` DISABLE KEYS */;
INSERT INTO `student_grades` VALUES (1,1,5,20.00,50.00),(2,8,5,30.00,40.00),(3,1,6,35.00,60.00),(4,3,6,25.00,71.00),(5,5,6,23.50,66.00),(6,1,1,15.00,80.00),(7,8,1,29.00,35.00),(8,1,2,35.00,76.00),(9,3,2,25.00,80.00),(10,5,2,33.00,70.13),(11,3,11,15.00,66.66),(12,4,12,20.00,28.00),(13,10,11,15.00,85.00),(14,6,10,20.00,80.00),(15,11,7,15.00,50.00);
/*!40000 ALTER TABLE `student_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_courses`
--

DROP TABLE IF EXISTS `student_has_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_has_courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `st_co_index` (`course_id`,`student_id`),
  KEY `fk_students_has_courses_students1` (`student_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_students_has_courses_courses1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `fk_students_has_courses_students1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_courses`
--

LOCK TABLES `student_has_courses` WRITE;
/*!40000 ALTER TABLE `student_has_courses` DISABLE KEYS */;
INSERT INTO `student_has_courses` VALUES (1,1,1),(14,8,1),(2,2,2),(13,7,2),(17,11,2),(7,1,3),(3,3,3),(11,5,3),(8,2,4),(4,4,4),(15,9,4),(9,3,5),(5,5,5),(16,10,5),(12,3,6),(10,4,6),(6,6,6);
/*!40000 ALTER TABLE `student_has_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `date_of_birth` date NOT NULL,
  `tuition_fees` decimal(6,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ST_UNIQUE` (`first_name`,`last_name`,`date_of_birth`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Philip','Rakitzidis','1982-07-25',2500.00),(2,'Eleni','Martidi','1999-03-20',2000.00),(3,'Dionysis','Fouzis','1990-09-15',2250.00),(4,'George','Krepas','1995-11-05',2000.00),(5,'Takis','Kalatzis','1977-03-11',2250.00),(6,'Yiannis','Krallis','1975-04-22',2500.00),(7,'Maria','Gkintziri','1985-05-02',2500.00),(8,'Aris','Plexidas','1986-03-19',2250.00),(9,'Kostas','Papavramides','1981-07-23',1500.00),(10,'Alexander','Korovesis','1987-08-08',1750.00),(11,'Alexandra','Validi','1980-06-05',1700.00),(12,'Stavros','Kontovas','1982-12-08',1500.00);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer_has_courses`
--

DROP TABLE IF EXISTS `trainer_has_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer_has_courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `trainer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tr_co_index` (`course_id`,`trainer_id`),
  KEY `fk_courses_has_trainers_trainers1_idx` (`trainer_id`),
  CONSTRAINT `fk_courses_has_trainers_courses1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `fk_courses_has_trainers_trainers1` FOREIGN KEY (`trainer_id`) REFERENCES `trainers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer_has_courses`
--

LOCK TABLES `trainer_has_courses` WRITE;
/*!40000 ALTER TABLE `trainer_has_courses` DISABLE KEYS */;
INSERT INTO `trainer_has_courses` VALUES (1,1,1),(2,1,5),(3,2,2),(4,2,3),(5,3,4),(6,3,6),(7,4,7),(8,4,8),(20,4,9),(15,5,2),(13,6,5);
/*!40000 ALTER TABLE `trainer_has_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainers`
--

DROP TABLE IF EXISTS `trainers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `subject` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TR_UNIQUE` (`first_name`,`last_name` DESC,`subject` DESC)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainers`
--

LOCK TABLES `trainers` WRITE;
/*!40000 ALTER TABLE `trainers` DISABLE KEYS */;
INSERT INTO `trainers` VALUES (5,'Alexander','Kotas','Front End'),(8,'Anastasios','Lavos','Robotics'),(7,'Argyris','Pagidas','mySQL'),(3,'Dionisis','Kamkou','C#'),(6,'George','Pasparakis','C#'),(4,'George','Ioakeimidis','Java'),(2,'Maria','Marilou','Front End'),(9,'Michalis','Chamilos','mySQL'),(1,'Nikos','Karapas','Java');
/*!40000 ALTER TABLE `trainers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-03 17:21:35
