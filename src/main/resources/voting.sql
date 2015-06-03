-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: security
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Current Database: `security`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `security`;

--
-- Table structure for table `principles`
--

DROP TABLE IF EXISTS `principles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `principles` (
  `principal_id` varchar(64) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`principal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `principles`
--

LOCK TABLES `principles` WRITE;
/*!40000 ALTER TABLE `principles` DISABLE KEYS */;
INSERT INTO `principles` VALUES ('admin','admin'),('TestUserOne','PasswordOne'),('TestUserTwo','PasswordTwo'),('user','user');
/*!40000 ALTER TABLE `principles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `principal_id` varchar(64) DEFAULT NULL,
  `user_role` varchar(64) DEFAULT NULL,
  `role_group` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('TestUserOne','TestRoleOne','TestUserOneGroup'),('TestUserTwo','TestRoleTwo','TestUserTwoGroup'),('admin','admin-role','admin-group'),('user','user-role','user-group');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `voting`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `voting` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `voting`;

--
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_llgse5i1dglntkxuvbhpy9l9p` (`region_id`),
  CONSTRAINT `FK_llgse5i1dglntkxuvbhpy9l9p` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (1,NULL,'Іван','Маслюк',3),(2,NULL,'John','Doe',1),(3,'Успішний політик','Дмитро','Андрієвський',3),(4,'Люблячий батько','Дарт','Вейдер',3);
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'RV','Rivne'),(2,'LV','Lviv'),(3,'KV','Kiev');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  `voter_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_87hdeb6rar83gjos3jj56gf63` (`candidate_id`),
  KEY `FK_ixksqbgpaax4drd88knwn5gj4` (`voter_id`),
  CONSTRAINT `FK_87hdeb6rar83gjos3jj56gf63` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`),
  CONSTRAINT `FK_ixksqbgpaax4drd88knwn5gj4` FOREIGN KEY (`voter_id`) REFERENCES `voter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (1,'2015-05-26 21:25:55',1,1),(2,'2015-05-27 11:38:57',1,1),(3,'2015-05-27 11:38:57',1,1),(4,'2015-05-27 11:38:57',1,1),(5,'2015-05-27 11:38:57',1,1),(6,'2015-05-27 11:38:57',1,1),(7,'2015-05-27 11:38:58',1,1),(8,'2015-05-27 11:38:58',1,1),(9,'2015-05-27 11:38:58',1,1),(10,'2015-05-27 11:41:10',3,1),(11,'2015-05-27 11:41:10',3,1),(12,'2015-05-27 12:20:55',2,1),(13,'2015-05-27 12:20:56',2,1),(14,'2015-05-27 12:20:58',2,1),(15,'2015-05-27 12:20:59',2,1),(16,'2015-05-27 12:21:02',2,1),(17,'2015-05-27 13:37:46',2,1),(18,'2015-05-27 13:40:47',1,1),(19,'2015-05-27 13:40:48',1,1),(20,'2015-05-27 13:40:50',3,1),(21,'2015-05-27 13:40:51',4,1),(22,'2015-05-27 13:40:51',4,1),(23,'2015-05-27 13:40:52',4,1),(24,'2015-05-27 13:40:52',4,1),(25,'2015-05-27 13:40:52',4,1),(26,'2015-05-27 13:40:52',4,1),(27,'2015-05-27 13:40:53',4,1),(28,'2015-05-27 13:40:54',4,1),(29,'2015-05-27 13:41:50',3,1),(30,'2015-05-27 13:41:50',3,1),(31,'2015-05-27 13:41:50',3,1),(32,'2015-05-27 13:41:50',3,1),(33,'2015-05-27 13:41:51',3,1),(34,'2015-05-27 13:41:51',3,1),(35,'2015-05-27 13:41:51',3,1),(36,'2015-05-27 13:41:51',3,1),(37,'2015-05-27 13:41:52',1,1),(38,'2015-05-27 13:41:52',1,1),(39,'2015-05-27 13:41:52',1,1),(40,'2015-05-27 13:41:53',1,1),(41,'2015-05-27 13:41:53',1,1),(42,'2015-05-27 13:41:53',1,1),(43,'2015-05-27 13:41:53',1,1),(44,'2015-05-27 13:41:53',1,1),(45,'2015-05-27 13:41:54',1,1),(46,'2015-05-27 13:41:54',1,1),(47,'2015-05-27 13:41:54',1,1),(48,'2015-05-27 13:41:54',1,1),(49,'2015-05-27 13:41:54',1,1),(50,'2015-05-27 13:41:55',1,1),(51,'2015-05-27 13:41:55',1,1),(52,'2015-05-27 13:41:55',1,1),(53,'2015-05-27 13:41:55',1,1),(54,'2015-05-27 13:41:55',1,1),(55,'2015-05-27 13:41:55',1,1),(56,'2015-05-27 13:41:56',1,1),(57,'2015-05-27 13:41:56',1,1),(58,'2015-05-27 13:41:56',1,1),(59,'2015-05-27 13:41:56',1,1),(60,'2015-05-27 13:41:57',1,1),(61,'2015-05-27 13:41:57',1,1),(62,'2015-05-27 13:41:57',1,1),(63,'2015-05-27 13:41:57',1,1),(64,'2015-05-27 15:04:50',2,1),(65,'2015-05-27 17:12:00',2,1),(66,'2015-05-27 17:12:12',3,1),(67,'2015-05-27 17:12:42',3,1),(68,'2015-05-27 17:12:44',4,1),(69,'2015-05-27 17:12:45',1,1),(70,'2015-05-27 17:12:48',2,1),(71,'2015-05-27 17:12:49',2,1),(72,'2015-05-27 17:12:49',2,1),(73,'2015-05-27 17:12:49',2,1),(74,'2015-05-27 17:12:50',2,1),(75,'2015-05-27 17:12:50',2,1),(76,'2015-05-27 17:12:50',2,1),(77,'2015-05-27 17:12:51',2,1),(78,'2015-05-27 17:12:51',2,1),(79,'2015-05-27 17:12:51',2,1),(80,'2015-05-27 17:12:52',2,1),(81,'2015-05-27 17:12:52',2,1),(82,'2015-05-27 17:12:52',2,1),(83,'2015-05-27 17:12:53',2,1),(84,'2015-05-27 17:12:53',2,1),(85,'2015-05-27 17:12:54',2,1),(86,'2015-05-27 17:13:00',2,1),(87,'2015-05-27 17:13:02',2,1),(88,'2015-05-27 17:13:03',2,1),(89,'2015-05-27 17:13:04',2,1),(90,'2015-05-27 17:13:31',2,1);
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voter`
--

DROP TABLE IF EXISTS `voter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `personalId` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cqmejbnob58x5c69yphbxgtpn` (`region_id`),
  CONSTRAINT `FK_cqmejbnob58x5c69yphbxgtpn` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voter`
--

LOCK TABLES `voter` WRITE;
/*!40000 ALTER TABLE `voter` DISABLE KEYS */;
INSERT INTO `voter` VALUES (1,'Michail','1','Sergeev',3);
/*!40000 ALTER TABLE `voter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-02 11:44:46
