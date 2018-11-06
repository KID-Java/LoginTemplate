-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: template
-- ------------------------------------------------------
-- Server version	5.7.23

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
-- Table structure for table `tb_roles_permissions`
--

DROP TABLE IF EXISTS `tb_roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_roles_permissions` (
  `role` varchar(100) NOT NULL COMMENT '角色',
  `permission` varchar(200) NOT NULL COMMENT '权限'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色_权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_roles_permissions`
--

LOCK TABLES `tb_roles_permissions` WRITE;
/*!40000 ALTER TABLE `tb_roles_permissions` DISABLE KEYS */;
INSERT INTO `tb_roles_permissions` VALUES ('admin','user:updata');
/*!40000 ALTER TABLE `tb_roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_users` (
  `username` varchar(32) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL,
  `salt` varchar(32) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES ('zhangsan','7494ed32a1d99532dc13fda29aa22674','e5dcea2bcadd4323b1df77710cb0ade3');
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users_roles`
--

DROP TABLE IF EXISTS `tb_users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_users_roles` (
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `role` varchar(100) NOT NULL COMMENT '角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户_角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users_roles`
--

LOCK TABLES `tb_users_roles` WRITE;
/*!40000 ALTER TABLE `tb_users_roles` DISABLE KEYS */;
INSERT INTO `tb_users_roles` VALUES ('zhangsan','admin');
/*!40000 ALTER TABLE `tb_users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-06 23:40:37
