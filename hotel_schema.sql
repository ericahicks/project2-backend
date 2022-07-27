CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservationid` int unsigned NOT NULL AUTO_INCREMENT,
  `roomnumber` int unsigned NOT NULL,
  `userid` int unsigned NOT NULL,
  `checkin` date DEFAULT NULL,
  `checkout` date DEFAULT NULL,
  PRIMARY KEY (`reservationid`),
  KEY `fk_reservation_room_idx` (`roomnumber`),
  KEY `fk_reservation_user_idx` (`userid`),
  CONSTRAINT `fk_reservation_room` FOREIGN KEY (`roomnumber`) REFERENCES `room` (`roomnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_user` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (6,513,56,'2022-08-11','2022-08-09'),(8,220,41,'2022-08-17','2022-08-16'),(9,307,44,'2022-08-11','2022-08-09'),(11,419,23,'2022-08-13','2022-08-08'),(12,216,62,'2022-08-19','2022-09-04'),(13,515,66,'2022-08-09','2022-08-09'),(14,313,45,'2022-08-08','2022-09-08'),(15,418,4,'2022-08-19','2022-08-22'),(16,206,67,'2022-08-15','2022-08-19'),(17,219,29,'2022-08-13','2022-08-07'),(18,303,67,'2022-08-18','2022-09-06'),(19,518,81,'2022-08-11','2022-08-24'),(20,214,14,'2022-08-07','2022-08-19'),(21,201,37,'2022-08-18','2022-09-07'),(22,511,19,'2022-08-11','2022-08-10'),(23,104,23,'2022-08-12','2022-08-24'),(24,320,51,'2022-08-18','2022-08-26'),(25,105,44,'2022-08-07','2022-09-07'),(26,506,25,'2022-08-13','2022-08-31'),(27,507,23,'2022-08-17','2022-08-15'),(28,112,81,'2022-08-17','2022-08-26'),(29,320,82,'2022-08-19','2022-08-16'),(30,508,14,'2022-08-14','2022-09-03'),(31,402,78,'2022-08-15','2022-08-22'),(32,516,34,'2022-08-19','2022-09-02'),(33,410,40,'2022-08-14','2022-08-19'),(34,120,21,'2022-08-08','2022-08-14'),(35,210,73,'2022-08-08','2022-09-07'),(36,412,35,'2022-08-07','2022-08-30'),(37,419,46,'2022-08-13','2022-09-05'),(38,404,35,'2022-08-06','2022-08-12'),(39,310,75,'2022-08-19','2022-08-26'),(40,513,69,'2022-08-12','2022-08-14'),(41,113,36,'2022-08-11','2022-08-21'),(42,419,16,'2022-08-15','2022-09-06'),(43,401,25,'2022-08-18','2022-08-24'),(44,519,73,'2022-08-07','2022-08-08'),(45,411,62,'2022-08-12','2022-09-06'),(46,215,52,'2022-08-17','2022-08-31'),(47,111,49,'2022-08-06','2022-09-09'),(48,115,15,'2022-08-14','2022-09-06'),(49,505,51,'2022-08-19','2022-08-13'),(50,505,3,'2022-08-08','2022-08-14'),(51,314,12,'2022-08-16','2022-09-01'),(52,514,36,'2022-08-14','2022-09-06'),(53,520,28,'2022-08-16','2022-08-14'),(54,202,35,'2022-08-18','2022-09-08'),(55,515,15,'2022-08-08','2022-08-07'),(56,503,12,'2022-08-11','2022-08-08'),(57,111,44,'2022-08-18','2022-08-14'),(58,113,35,'2022-08-10','2022-09-05'),(59,201,37,'2022-08-09','2022-08-21'),(60,212,27,'2022-08-06','2022-09-07');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `roomnumber` int unsigned NOT NULL,
  `roomtypeid` int unsigned NOT NULL,
  PRIMARY KEY (`roomnumber`),
  KEY `fk_room_roomtype_idx` (`roomtypeid`),
  CONSTRAINT `fk_room_roomtype` FOREIGN KEY (`roomtypeid`) REFERENCES `roomtype` (`roomtypeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (110,1),(113,1),(202,1),(216,1),(304,1),(408,1),(413,1),(518,1),(101,2),(107,2),(116,2),(117,2),(118,2),(201,2),(203,2),(204,2),(209,2),(210,2),(211,2),(212,2),(214,2),(217,2),(301,2),(319,2),(320,2),(406,2),(410,2),(418,2),(502,2),(509,2),(510,2),(515,2),(516,2),(102,3),(104,3),(106,3),(108,3),(109,3),(114,3),(120,3),(207,3),(208,3),(215,3),(220,3),(302,3),(305,3),(308,3),(309,3),(313,3),(316,3),(317,3),(318,3),(401,3),(402,3),(403,3),(405,3),(411,3),(414,3),(415,3),(417,3),(419,3),(420,3),(501,3),(504,3),(506,3),(507,3),(512,3),(513,3),(520,3),(105,4),(112,4),(119,4),(205,4),(206,4),(218,4),(306,4),(307,4),(311,4),(409,4),(416,4),(503,4),(508,4),(514,4),(519,4),(103,5),(111,5),(115,5),(213,5),(219,5),(303,5),(310,5),(312,5),(314,5),(315,5),(404,5),(407,5),(412,5),(505,5),(511,5),(517,5);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomtype` (
  `roomtypeid` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`roomtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
INSERT INTO `roomtype` VALUES (1,'1 Queen Bed Standard Room',115.00),(2,'2 Queen Beds Standard Room',109.00),(3,'2 Queen Beds Suite',121.00),(4,'1 King Bed Standard Room',130.00),(5,'1 King Bed Suite',140.00);
/*!40000 ALTER TABLE `roomtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` int unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'Stanford','Jakeway','+994 (572) 836-1888','sjakeway0@yahoo.com'),(4,'Zena','Belson','+1 (682) 779-0213','zbelson1@netlog.com'),(5,'Jaine','Lau','+57 (616) 116-5853','jlau2@boston.com'),(6,'Pet','MacKettrick','+963 (970) 885-4306','pmackettrick3@scribd.com'),(7,'Susie','Robert','+62 (251) 749-5234','srobert4@technorati.com'),(8,'James','Arr','+358 (124) 687-3208','jarr5@wikispaces.com'),(9,'Cristine','Everwin','+386 (132) 151-6580','ceverwin6@blogger.com'),(10,'Dorey','Balloch','+351 (506) 764-2054','dballoch7@webeden.co.uk'),(11,'Almire','Brockherst','+7 (341) 599-1312','abrockherst8@altervista.org'),(12,'Marcella','Sebire','+93 (600) 130-1395','msebire9@whitehouse.gov'),(13,'Tanhya','Lavies','+86 (693) 768-5629','tlaviesa@chronoengine.com'),(14,'Lorens','Pellington','+1 (425) 862-8350','lpellingtonb@ustream.tv'),(15,'Randee','Tine','+86 (295) 533-8841','rtinec@vistaprint.com'),(16,'Beatriz','Sparry','+84 (574) 741-0434','bsparryd@ihg.com'),(17,'Chery','Antcliff','+86 (181) 189-2927','cantcliffe@illinois.edu'),(18,'Tate','Knibley','+358 (698) 863-4191','tknibleyf@theguardian.com'),(19,'Elfie','Dunlop','+48 (143) 519-5481','edunlopg@deviantart.com'),(20,'Barbee','Richemont','+380 (576) 322-7729','brichemonth@nature.com'),(21,'Tito','Broe','+55 (270) 724-7599','tbroei@ft.com'),(22,'Smith','Callacher','+7 (123) 394-7153','scallacherj@bigcartel.com'),(23,'Fionna','Trimnell','+57 (431) 685-1641','ftrimnellk@paypal.com'),(24,'Tally','Grievson','+51 (524) 316-5655','tgrievsonl@friendfeed.com'),(25,'Maye','Durn','+86 (440) 793-7530','mdurnm@fema.gov'),(26,'Benny','Skeene','+98 (566) 570-0123','bskeenen@harvard.edu'),(27,'Corissa','Craine','+7 (815) 864-2672','ccraineo@miitbeian.gov.cn'),(28,'Caz','Chiverton','+86 (126) 101-9764','cchivertonp@cam.ac.uk'),(29,'Lucine','Corless','+62 (453) 372-2464','lcorlessq@booking.com'),(30,'Burnaby','MacKeever','+7 (883) 575-3828','bmackeeverr@ow.ly'),(31,'Jessamyn','Coger','+81 (265) 765-9322','jcogers@jugem.jp'),(32,'Rudolf','Copnar','+351 (899) 305-1373','rcopnart@umn.edu'),(33,'Shell','Darrel','+62 (804) 795-5761','sdarrelu@nationalgeographic.com'),(34,'Kristi','Chalfain','+351 (379) 592-6735','kchalfainv@engadget.com'),(35,'Reinwald','Tosdevin','+351 (267) 979-5275','rtosdevinw@4shared.com'),(36,'Jaymee','Fernehough','+507 (477) 579-2456','jfernehoughx@ustream.tv'),(37,'Granny','Outerbridge','+81 (778) 124-7690','gouterbridgey@smugmug.com'),(38,'Ashlie','Nix','+62 (381) 589-9353','anixz@cdbaby.com'),(39,'Marybeth','Chippin','+46 (456) 676-1485','mchippin10@nsw.gov.au'),(40,'Alix','Marcombe','+238 (540) 657-0345','amarcombe11@netscape.com'),(41,'Briant','Alben','+86 (256) 286-7320','balben12@taobao.com'),(42,'Mag','O\'Donohue','+63 (179) 928-6765','modonohue13@goodreads.com'),(43,'Lenard','Edelheit','+63 (430) 264-9634','ledelheit14@ning.com'),(44,'Meredith','Blanche','+81 (483) 661-0101','mblanche15@instagram.com'),(45,'Zak','Reeder','+62 (627) 597-0679','zreeder16@youku.com'),(46,'Ettore','Rochester','+7 (817) 144-5659','erochester17@cyberchimps.com'),(47,'Daffi','Howard - Gater','+86 (489) 392-6494','dhowardgater18@dyndns.org'),(48,'Gun','Djorvic','+62 (653) 441-5514','gdjorvic19@theatlantic.com'),(49,'Luke','de Nore','+86 (425) 937-5792','ldenore1a@fema.gov'),(50,'Lorrie','Rawlingson','+7 (813) 991-2220','lrawlingson1b@dot.gov'),(51,'Griffith','Leggott','+63 (489) 970-5840','gleggott1c@toplist.cz'),(52,'Benedicta','Halbord','+7 (655) 133-1361','bhalbord1d@globo.com'),(53,'Ogdon','Lupson','+86 (941) 135-6354','olupson1e@unesco.org'),(54,'Gracia','Hayles','+86 (617) 365-8383','ghayles1f@simplemachines.org'),(55,'Em','Baddiley','+82 (219) 783-5975','ebaddiley1g@biglobe.ne.jp'),(56,'Cathee','Dronsfield','+237 (436) 929-2520','cdronsfield1h@xing.com'),(57,'Randal','O\'Shiel','+81 (215) 543-0203','roshiel1i@constantcontact.com'),(58,'Orlando','Daulby','+81 (643) 830-5554','odaulby1j@merriam-webster.com'),(59,'Pebrook','Ruoss','+86 (931) 529-3721','pruoss1k@joomla.org'),(60,'Reidar','Hamber','+1 (918) 210-8575','rhamber1l@a8.net'),(61,'Corrinne','Tonn','+86 (576) 639-4321','ctonn1m@etsy.com'),(62,'Ninnette','Vearncomb','+63 (659) 277-4743','nvearncomb1n@a8.net'),(63,'Nehemiah','MacAne','+57 (545) 386-9741','nmacane1o@blogtalkradio.com'),(64,'Randolf','Valance','+54 (797) 265-9175','rvalance1p@latimes.com'),(65,'Lark','McPhilemy','+1 (339) 661-4351','lmcphilemy1q@mapquest.com'),(66,'Tabitha','Letford','+7 (319) 510-8083','tletford1r@creativecommons.org'),(67,'Kristofor','Pillman','+48 (334) 133-8946','kpillman1s@toplist.cz'),(68,'Alanson','Sparkwell','+62 (971) 203-9448','asparkwell1t@tinypic.com'),(69,'Bartolomeo','Revelle','+63 (395) 721-1387','brevelle1u@sourceforge.net'),(70,'Honoria','Larcier','+84 (892) 106-8404','hlarcier1v@netlog.com'),(71,'Bonita','Jiras','+27 (287) 456-5975','bjiras1w@ucla.edu'),(72,'Duke','Sibery','+234 (359) 143-5117','dsibery1x@hostgator.com'),(73,'Beaufort','Dinneen','+27 (138) 843-9944','bdinneen1y@mlb.com'),(74,'Winonah','Olufsen','+7 (272) 376-1890','wolufsen1z@cafepress.com'),(75,'Dallis','MacCrackan','+234 (206) 203-9577','dmaccrackan20@unblog.fr'),(76,'Marys','Aldous','+86 (545) 644-4748','maldous21@wordpress.org'),(77,'Lacey','Spicer','+265 (292) 888-9101','lspicer22@springer.com'),(78,'Bogey','Heberden','+63 (322) 821-0761','bheberden23@xinhuanet.com'),(79,'Forrest','Overstone','+48 (899) 580-7631','foverstone24@plala.or.jp'),(80,'Goldy','Ferryman','+86 (407) 125-3689','gferryman25@elegantthemes.com'),(81,'Arlen','Skill','+55 (739) 984-8256','askill26@amazon.co.jp'),(82,'Bill','Tanby','+355 (626) 722-9891','btanby27@dailymotion.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hotel'
--
/*!50003 DROP PROCEDURE IF EXISTS `isAvailable_by_roomnumber` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `isAvailable_by_roomnumber`(IN roomNum INT, IN userCheckin DATE, IN userCheckout DATE)
BEGIN
   SELECT (userCheckin >= checkout) as 'senario1', (userCheckout <= checkin) as 'senario3', ((userCheckin >= checkout) OR (userCheckout <= checkin)) as isAvailable FROM reservation WHERE roomnumber = roomNum;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `roomtype_count` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `roomtype_count`()
BEGIN
   SELECT COUNT(*), roomtypeid 
   FROM room 
   GROUP BY roomtypeid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `roomtype_count_by_floor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `roomtype_count_by_floor`(IN floorNum INT)
BEGIN
   SELECT COUNT(*) as count, room.roomtypeid, floor(roomnumber/100) as `floor` 
   FROM room 
   GROUP BY  roomtypeid, `floor` 
   HAVING `floor` = floorNum;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-27 14:53:24
