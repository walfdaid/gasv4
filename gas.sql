-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: gas
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `address`
--

use heroku_bc8542c3f00e647;

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` bigint NOT NULL,
  `apartment_number` int NOT NULL,
  `building_number` int NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `floor` int NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lattiude` double NOT NULL,
  `longittude` double NOT NULL,
  `neighborhood` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (17,0,8,NULL,NULL,0,'http://i.imgur.com/DH4FRuq.jpg',0,0,NULL,NULL),(18,0,7,NULL,NULL,0,'http://i.imgur.com/DH4FRuq.jpg',0,0,NULL,NULL),(19,0,7,NULL,NULL,0,'http://i.imgur.com/DH4FRuq.jpg',0,0,NULL,NULL),(36,3,4,'Irbid','jordan',3,NULL,32.5332173,35.8653203,NULL,'Irbid city center'),(38,3,4,'Ammad','Jordan',3,NULL,32.5332173,35.8653203,NULL,'Irbid Happy land'),(78,3,4,'amman','jordan',3,NULL,13,12,NULL,'adrea');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` int NOT NULL,
  `status` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `UK_c0r9atamxvbhjjvy5j8da1kam` (`email`),
  UNIQUE KEY `UK_ema32g9ju0a46v3ovs4d2imte` (`phone_number`),
  UNIQUE KEY `UK_kqgbjr9wso54y7u1xlsqloc4b` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (26,'2022-05-09 13:09:51.605000',NULL,'thaerhussien@gmail.com','Thaer Selawe','75321598erds','+962799074530',1,_binary '','0000','2022-05-10 02:14:25.069000','thaerselawe'),(65,'2022-05-10 01:14:30.299000',NULL,'haneenhade@gmail.com','hanen ahmed hade','24865789','0774533642',0,_binary '','0000',NULL,'hanen ahmed');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (67);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income` (
  `in_come_id` bigint NOT NULL,
  `amount` double DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `recieved_from` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`in_come_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES (61,162,'10 remaining','2022-05-09 22:28:39.300000','Cash','jafer','order statement 60'),(66,45,NULL,'2022-05-10 01:32:40.008000','CASH','mazenn','statmentt');
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `status` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (2,'2022-05-08 22:51:43.053000','2022-05-08 22:57:07.567000','Gas Cylinder',10,_binary '','2022-05-08 22:51:43.059000'),(39,'2022-05-09 21:45:05.854000',NULL,'Gas Pipline',5,_binary '',NULL),(40,'2022-05-09 21:45:22.338000',NULL,'Gas Watch',30,_binary '',NULL);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_items_id` bigint NOT NULL,
  `item_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `item_item_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_items_id`),
  KEY `FKaa9yyv75oiy833nj200acon5i` (`item_item_id`),
  CONSTRAINT `FKaa9yyv75oiy833nj200acon5i` FOREIGN KEY (`item_item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (42,40,0,40),(44,40,1,40),(46,40,1,40),(48,40,1,40),(50,40,1,40),(51,2,1,2),(53,40,1,40),(54,2,1,2),(55,39,1,39),(57,40,2,40),(58,2,1,2),(59,39,3,39);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `customer_id` bigint NOT NULL,
  `delivered_at` datetime(6) DEFAULT NULL,
  `status` int NOT NULL,
  `status_name` varchar(255) DEFAULT NULL,
  `supplier_id` bigint NOT NULL,
  `total_amount` double NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `supplier_supplier_id` bigint DEFAULT NULL,
  `user_customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKi4ixt5venrtmrsf660oo8ad4w` (`supplier_supplier_id`),
  KEY `FKce3b8a7e90iwau9598lc2nbk6` (`user_customer_id`),
  CONSTRAINT `FKce3b8a7e90iwau9598lc2nbk6` FOREIGN KEY (`user_customer_id`) REFERENCES `user` (`customer_id`),
  CONSTRAINT `FKi4ixt5venrtmrsf660oo8ad4w` FOREIGN KEY (`supplier_supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (43,'2022-05-09 21:56:16.010000',37,NULL,0,'New',41,35,NULL,41,37),(45,'2022-05-09 21:57:02.839000',37,NULL,0,'New',41,35,NULL,41,37),(47,'2022-05-09 21:57:17.909000',37,'2022-05-09 22:26:33.903000',2,'Deliverd',41,35,'2022-05-09 22:26:33.903000',41,37),(49,'2022-05-09 21:57:19.599000',37,NULL,5,'Rejected',41,35,'2022-05-09 22:25:44.025000',41,37),(52,'2022-05-09 21:58:55.718000',35,'2022-05-09 22:25:03.208000',4,'PostPoned',41,65,'2022-05-09 22:25:06.772000',41,35),(56,'2022-05-09 21:59:25.855000',35,NULL,3,'Canceled',41,86,'2022-05-09 22:24:28.649000',41,35),(60,'2022-05-09 22:01:14.295000',35,'2022-05-09 22:23:52.311000',2,'Deliverd',41,86,'2022-05-09 22:23:52.311000',41,35);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_order_items`
--

DROP TABLE IF EXISTS `orders_order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_order_items` (
  `order_order_id` bigint NOT NULL,
  `order_items_order_items_id` bigint NOT NULL,
  UNIQUE KEY `UK_9pryf5t2m33stoqkyc94u818g` (`order_items_order_items_id`),
  KEY `FK4a5vis32u4bexdg4xyjjc7o4j` (`order_order_id`),
  CONSTRAINT `FK4a5vis32u4bexdg4xyjjc7o4j` FOREIGN KEY (`order_order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKloqo06vx35e0ok18iancdip9y` FOREIGN KEY (`order_items_order_items_id`) REFERENCES `order_items` (`order_items_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_order_items`
--

LOCK TABLES `orders_order_items` WRITE;
/*!40000 ALTER TABLE `orders_order_items` DISABLE KEYS */;
INSERT INTO `orders_order_items` VALUES (43,42),(45,44),(47,46),(49,48),(52,50),(52,51),(56,53),(56,54),(56,55),(60,57),(60,58),(60,59);
/*!40000 ALTER TABLE `orders_order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outcome`
--

DROP TABLE IF EXISTS `outcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outcome` (
  `out_come_id` bigint NOT NULL,
  `amount` double DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `paid_to` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`out_come_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outcome`
--

LOCK TABLES `outcome` WRITE;
/*!40000 ALTER TABLE `outcome` DISABLE KEYS */;
INSERT INTO `outcome` VALUES (62,20.9,'Visa will expire soon','2022-05-09 22:31:26.270000','Thaer same','visa','order id 60');
/*!40000 ALTER TABLE `outcome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `payment_method_id` bigint NOT NULL,
  `status` bit(1) NOT NULL,
  `payment_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,_binary '','CASH');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` bigint NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `lattiude` double NOT NULL,
  `longittude` double NOT NULL,
  `online` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `admin_admin_id` bigint DEFAULT NULL,
  PRIMARY KEY (`supplier_id`),
  UNIQUE KEY `UK_g7qiwwu4vpciysmeeyme9gg1d` (`email`),
  UNIQUE KEY `UK_gd9qro7i3c7jhuaps7wsxe6ir` (`phone_number`),
  UNIQUE KEY `UK_5a0knnwsnpbe1l292jaa58rb8` (`user_name`),
  KEY `FK3i4aqjur741lc0y6po4dcs4po` (`admin_admin_id`),
  CONSTRAINT `FK3i4aqjur741lc0y6po4dcs4po` FOREIGN KEY (`admin_admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (27,'2022-05-09 03:00:00.000000',26,NULL,'saraJyousefe22@gmail.com','Sara Jarer Khaled Yousefe',NULL,400,500,_binary '\0','4562137rr','0770760234',_binary '',NULL,NULL,'Sara Jarer',26),(41,'2022-05-09 21:53:05.775000',26,NULL,'salah@gmail.com','Saalah Alddein Selawe',NULL,34.23434,35.23432,_binary '','123456','+9627978451',_binary '','0000',NULL,'salahselawe',26);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_traceable`
--

DROP TABLE IF EXISTS `supplier_traceable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_traceable` (
  `traceable_id` bigint NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `lattiude` double NOT NULL,
  `longittude` double NOT NULL,
  `offline_time` datetime(6) DEFAULT NULL,
  `online` bit(1) NOT NULL,
  `online_time` datetime(6) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `supplier_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`traceable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_traceable`
--

LOCK TABLES `supplier_traceable` WRITE;
/*!40000 ALTER TABLE `supplier_traceable` DISABLE KEYS */;
INSERT INTO `supplier_traceable` VALUES (10,NULL,900,800,NULL,_binary '','2022-05-06 16:51:30.294000','0792368602','saleh same'),(11,NULL,900,800,'2022-05-06 16:52:44.279000',_binary '\0',NULL,'0792368602','saleh same'),(28,NULL,900,800,'2022-05-09 14:11:58.672000',_binary '\0',NULL,'0792368602','saleh same mohammd momne'),(29,NULL,900,800,NULL,_binary '','2022-05-09 14:21:04.139000','0792368602','saleh same mohammd momne'),(30,NULL,900,800,'2022-05-09 14:22:51.270000',_binary '\0',NULL,'0792368602','saleh same mohammd momne'),(31,'2022-05-09',200,300,'2022-04-04 03:00:00.000000',_binary '','2022-05-09 03:00:00.000000','0799074530','mazen ahemd'),(456,'2022-06-18',0,0,NULL,_binary '',NULL,NULL,NULL);
/*!40000 ALTER TABLE `supplier_traceable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `customer_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `online` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `address_address_id` bigint DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_4bgmpi98dylab6qdvf9xyaxu4` (`phone_number`),
  KEY `FKp890qjjx13q8wwq23o1cndl9i` (`address_address_id`),
  CONSTRAINT `FKp890qjjx13q8wwq23o1cndl9i` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (12,'2018-04-16 03:00:00.000000','2022-05-07 11:06:55.155000','jamalsaeed78@gamil.com','jamal saeed mohammad hsenat',_binary '\0','15324865erds','070792978793',_binary '\0','0000','2022-05-07 10:55:18.041000',78),(23,'2015-06-27 03:00:00.000000',NULL,'jamalsaeed78s@gamil.com','jamal saeed mohammad hsenats',_binary '\0','15324865erdssss','0795958231',_binary '','0000','2022-05-09 18:14:06.631000',78),(32,'2019-06-27 03:00:00.000000',NULL,'zenYaser78@gmail.com','Zen Yaser Ahmed Jabale',_binary '','78545632qwer','0779785661',_binary '','MnWBJ2QDLl8zPJzEYDFx',NULL,78),(34,'2022-05-09 21:16:49.265000',NULL,'thaer@gmail.com','Thaer Selawe',_binary '','123456','+962797274126',_binary '','0000',NULL,NULL),(35,'2022-05-09 21:17:52.769000',NULL,'ahmedwajieh@gmail.com','Ahmed Wajieh',_binary '\0','123456','+962797274125',_binary '','0000','2022-05-09 21:34:54.432000',36),(37,'2022-05-09 21:36:08.659000',NULL,'jehadselawe@gmail.com','Jehad Selawe',_binary '\0','123456','+962797274124',_binary '','QntRLobdoZDAnasdMPC5','2022-05-09 21:37:53.217000',38);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-11 15:19:26
