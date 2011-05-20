CREATE DATABASE  IF NOT EXISTS `innowhite_whiteboard` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `innowhite_whiteboard`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: innowhite_whiteboard
-- ------------------------------------------------------
-- Server version	5.1.47-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room_id` varchar(45) NOT NULL,
  `org_name` varchar(150) NOT NULL,
  `room_active` varchar(15) DEFAULT 'INACTIVE',
  `users_count` int(11) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `group_leader` varchar(150) DEFAULT NULL,
  `inserted_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `id` int(7) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('12783631721534558','Company1','ACTIVE',0,0,NULL,'2010-07-05 00:00:00','2010-07-05 15:17:53',NULL,3),('12783632253965875','Company1','INACTIVE',0,0,NULL,'2010-07-05 00:00:00','2010-07-05 00:00:00',NULL,4),('12783640055528439','Company1','ACTIVE',0,0,NULL,'2010-07-05 00:00:00','2010-07-05 00:00:00',NULL,5),('1278364042544552','Company1','INACTIVE',0,0,NULL,'2010-07-05 00:00:00','2010-07-05 00:00:00',NULL,6),('12783641492545572','Company1','INACTIVE',0,0,NULL,'2010-07-05 00:00:00','2010-07-05 15:22:14','2010-07-05 15:24:47',7),('12783641524873007','Company1','ACTIVE',0,0,NULL,'2010-07-05 00:00:00','2010-07-05 00:00:00',NULL,8),('12783651546852727','Company1','INACTIVE',0,0,NULL,'2010-07-05 00:00:00','2010-07-05 00:00:00',NULL,9),('12783655884726820','Company1','INACTIVE',0,0,NULL,'2010-07-05 14:33:08',NULL,NULL,10),('57430581936','Company1','ACTIVE',0,0,NULL,'2010-07-05 14:35:43','2010-07-05 21:26:39','2010-07-05 21:26:39',11),('61313363851','Company1','ACTIVE',0,0,NULL,'2010-07-05 14:42:11',NULL,NULL,12),('67805984414','Company1','ACTIVE',0,0,NULL,'2010-07-05 14:53:00',NULL,NULL,13),('70134195479','Company1',NULL,0,0,NULL,'2010-07-05 20:30:13',NULL,NULL,14),('71934722517','Company1','INACTIVE',0,0,NULL,'2010-07-05 20:33:13',NULL,NULL,15);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-07-05 21:47:56