-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: schedule-request
-- ------------------------------------------------------
-- Server version	5.7.36

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
-- Table structure for table `addressee`
--

DROP TABLE IF EXISTS `addressee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addressee` (
  `uuid` binary(16) NOT NULL,
  `addressee` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKblqe8kh327uaksl78mfe2f2g2` (`addressee`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addressee`
--

LOCK TABLES `addressee` WRITE;
/*!40000 ALTER TABLE `addressee` DISABLE KEYS */;
INSERT INTO `addressee` VALUES (_binary '\·»∏äFÀé\Ê{øÙºy\ ','Franklin','(88)997128192','2021-11-12 13:43:59','franklin@gmail.com',NULL),(_binary 'h^áN[ΩC}Æüe,\›§à','Romas','(88)997124578','2021-11-12 01:13:15','romas@gmail.com',NULL),(_binary '±\Ï\n1πHÉs¸jç∏','Anderson Freitas','(88)997128191','2021-11-12 03:50:45','andersonfreitas21@gmail.com',NULL),(_binary '\Âˆ*ï-@˜∂\€\—\n¶\Õ@','Franklin2','(88)997128192','2021-11-12 13:45:10','franklin@gmail.com',NULL),(_binary '˙Vô\◊\–\ÁI<®%7`-IU','Luiz Felipe','(88)997128192','2021-11-12 03:51:45','fepus@gmail.com',NULL);
/*!40000 ALTER TABLE `addressee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `uuid` binary(16) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `message` varchar(255) NOT NULL,
  `send_date` datetime NOT NULL,
  `status` enum('PENDING','SENT','DELETED') NOT NULL,
  `type` enum('WHATSAPP','SMS','EMAIL','PUSH') NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `addressee_uuid` binary(16) NOT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKex3ri6msvy6oc556qlb5rmjbq` (`addressee_uuid`),
  CONSTRAINT `FKex3ri6msvy6oc556qlb5rmjbq` FOREIGN KEY (`addressee_uuid`) REFERENCES `addressee` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (_binary '9\Ó\‹)VEãm\‹¿öWp}','2021-11-12 01:13:15','Mensagem a ser enviada','2021-11-12 00:18:01','DELETED','PUSH','2021-11-12 17:47:02',_binary 'h^áN[ΩC}Æüe,\›§à'),(_binary 'c\∆¯BN∫.\0\‘6Ω0ª','2021-11-12 03:51:45','Mensagem a ser enviada','2021-11-12 00:18:01','PENDING','SMS',NULL,_binary '˙Vô\◊\–\ÁI<®%7`-IU'),(_binary 'n\ÿ\ÂÜ\ÁL¯∞t!V\ƒ\⁄\œe','2021-11-12 13:45:10','Mensagem a ser enviada','2021-11-13 00:18:01','PENDING','SMS',NULL,_binary '\Âˆ*ï-@˜∂\€\—\n¶\Õ@'),(_binary 'z≤QAüI\Ìû\r\Êc∞ä\0r','2021-11-12 03:33:46','Mensagem a ser enviada','2021-11-12 00:18:01','PENDING','WHATSAPP',NULL,_binary 'h^áN[ΩC}Æüe,\›§à'),(_binary 'çDwDå0Hjîß\‡,ãsCö','2021-11-12 13:43:59','Mensagem a ser enviada','2021-11-13 00:18:01','DELETED','SMS','2021-11-12 17:42:30',_binary '\·»∏äFÀé\Ê{øÙºy\ '),(_binary 'íT<fπ|OI´Hé\Ï◊ØΩX','2021-11-12 03:32:02','Mensagem a ser enviada','2021-11-12 00:18:01','DELETED','PUSH','2021-11-12 17:44:54',_binary 'h^áN[ΩC}Æüe,\›§à'),(_binary 'ôv8V`;K˜Ω!∂é\ Ÿê','2021-11-12 12:42:56','Mensagem a ser enviada','2021-11-12 00:18:01','PENDING','SMS',NULL,_binary '˙Vô\◊\–\ÁI<®%7`-IU');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-15 23:24:13
