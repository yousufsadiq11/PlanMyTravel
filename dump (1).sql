-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: planmytravel
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `username` varchar(200) NOT NULL,
  `password` varchar(220) NOT NULL,
  `email_id` varchar(32) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email_id_UNIQUE` (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('sanju','sanju','sanjukh68@gmail.com','sanju','kh'),('yousuf','19','yousufsadiq11@gmail.com','yousuf','sadiq');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airlines`
--

DROP TABLE IF EXISTS `airlines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airlines` (
  `airlines_id` int(60) NOT NULL AUTO_INCREMENT,
  `airlines_name` varchar(444) NOT NULL,
  PRIMARY KEY (`airlines_id`),
  UNIQUE KEY `airlines_name_UNIQUE` (`airlines_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airlines`
--

LOCK TABLES `airlines` WRITE;
/*!40000 ALTER TABLE `airlines` DISABLE KEYS */;
INSERT INTO `airlines` VALUES (4,' \'Lufthansa\''),(5,'\'american airlines\''),(1,'\'British airways\''),(7,'\'Emirates\''),(6,'\'Ethihad airways\''),(3,'\'Etihad\''),(2,'\'Jet Airways\'');
/*!40000 ALTER TABLE `airlines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booked_flights`
--

DROP TABLE IF EXISTS `booked_flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booked_flights` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `flight_id` int(11) NOT NULL,
  `no_of_economy_seats` int(11) NOT NULL DEFAULT '0',
  `no_of_business_seats` int(11) NOT NULL,
  `checkin_flag` int(1) NOT NULL DEFAULT '0',
  `feedback_flag` int(1) NOT NULL DEFAULT '0',
  `seats_booked` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`booking_id`),
  KEY `fk_username` (`user_name`),
  KEY `fk_flightid` (`flight_id`),
  CONSTRAINT `booked_flights_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `registered_user` (`username`),
  CONSTRAINT `booked_flights_ibfk_2` FOREIGN KEY (`flight_id`) REFERENCES `flight_details` (`flight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booked_flights`
--

LOCK TABLES `booked_flights` WRITE;
/*!40000 ALTER TABLE `booked_flights` DISABLE KEYS */;
INSERT INTO `booked_flights` VALUES (1,'sanju',1,5,0,1,4,0),(21,'yousuf',1,2,2,0,0,0),(22,'sanju',1,5,2,0,0,0),(23,'sanju',1,5,2,0,0,0),(25,'sanju',1,5,2,0,0,0),(26,'sanju',1,5,2,0,0,0),(27,'sanju',1,5,2,0,0,0),(28,'sanju',1,5,2,0,0,0),(29,'sanju',1,5,2,0,0,0),(30,'sanju',2,5,2,0,0,0),(31,'sanju',2,5,2,0,0,0),(32,'sanju',2,5,2,0,0,0),(33,'sanju',2,5,2,0,0,0),(34,'sanju',2,5,2,0,0,7);
/*!40000 ALTER TABLE `booked_flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_details`
--

DROP TABLE IF EXISTS `card_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_details` (
  `card_no` int(20) NOT NULL,
  `name_on_card` varchar(32) NOT NULL,
  `cvv` varchar(5) NOT NULL,
  `card_type` varchar(32) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  `expiry_date` date NOT NULL,
  PRIMARY KEY (`card_no`,`user_name`),
  KEY `fk_username` (`user_name`),
  CONSTRAINT `card_details_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `registered_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_details`
--

LOCK TABLES `card_details` WRITE;
/*!40000 ALTER TABLE `card_details` DISABLE KEYS */;
INSERT INTO `card_details` VALUES (2345,'my card','999','visa','sanju','debit','2016-11-12'),(123456789,'yousuf','133','visa','sanju','debit','2016-11-12');
/*!40000 ALTER TABLE `card_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(32) NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`city_id`),
  KEY `fk_countryid` (`country_id`),
  KEY `idx_cities_city_name` (`city_name`),
  CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Bangalore',2),(2,'Hyderabad',2),(3,'Chennai',2),(4,'Delhi',2),(5,'Charlotte',1),(6,'New York',1),(7,'San Francisco',1),(8,'Los Angeles',1),(9,'Chicago',1);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(32) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_name_UNIQUE` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (3,'Australia'),(2,'India'),(4,'Singapore'),(1,'United States');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_notification`
--

DROP TABLE IF EXISTS `email_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_notification` (
  `booking_id` int(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`booking_id`,`email`),
  KEY `email` (`email`),
  CONSTRAINT `email_notification_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booked_flights` (`booking_id`),
  CONSTRAINT `email_notification_ibfk_2` FOREIGN KEY (`email`) REFERENCES `registered_user` (`emailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_notification`
--

LOCK TABLES `email_notification` WRITE;
/*!40000 ALTER TABLE `email_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_details`
--

DROP TABLE IF EXISTS `flight_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_details` (
  `flight_id` int(11) NOT NULL AUTO_INCREMENT,
  `flight_number` varchar(12) NOT NULL,
  `arrival_time` time NOT NULL,
  `departure_time` time NOT NULL,
  `date_of_arrival` date NOT NULL,
  `date_of_departure` date NOT NULL,
  `economy_seat_count` int(11) NOT NULL,
  `business_seat_count` int(11) NOT NULL,
  `source_id` int(20) NOT NULL,
  `destination_id` int(20) NOT NULL,
  PRIMARY KEY (`flight_id`),
  KEY `fk_sourceid` (`source_id`),
  KEY `fk_destinationid` (`destination_id`),
  KEY `flight_number_idx` (`flight_number`),
  CONSTRAINT `flight_details_ibfk_2` FOREIGN KEY (`source_id`) REFERENCES `cities` (`city_id`),
  CONSTRAINT `flight_details_ibfk_3` FOREIGN KEY (`destination_id`) REFERENCES `cities` (`city_id`),
  CONSTRAINT `flight_number` FOREIGN KEY (`flight_number`) REFERENCES `flight_info` (`flight_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_details`
--

LOCK TABLES `flight_details` WRITE;
/*!40000 ALTER TABLE `flight_details` DISABLE KEYS */;
INSERT INTO `flight_details` VALUES (1,'BA1736','08:00:00','10:00:00','2016-11-20','2016-11-21',15,40,1,5),(2,'AA1236','12:00:00','15:00:00','2016-11-20','2016-11-20',53,8,5,6),(3,'AA1236','22:00:00','01:00:00','2016-11-20','2016-11-20',32,22,6,7),(4,'JA1234','02:00:00','04:30:00','2016-11-20','2016-11-20',21,33,1,2);
/*!40000 ALTER TABLE `flight_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_info`
--

DROP TABLE IF EXISTS `flight_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight_info` (
  `flight_number` varchar(12) NOT NULL,
  `airlines_id` int(11) NOT NULL,
  `total_economy_seats` int(11) NOT NULL,
  `total_business_seats` int(11) NOT NULL,
  PRIMARY KEY (`flight_number`),
  KEY `airlines_id_idx` (`airlines_id`),
  CONSTRAINT `airlines_id` FOREIGN KEY (`airlines_id`) REFERENCES `airlines` (`airlines_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_info`
--

LOCK TABLES `flight_info` WRITE;
/*!40000 ALTER TABLE `flight_info` DISABLE KEYS */;
INSERT INTO `flight_info` VALUES ('AA1122',4,200,200),('AA1236',2,200,100),('BA1736',1,300,300),('JA1234',3,400,200);
/*!40000 ALTER TABLE `flight_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_plan`
--

DROP TABLE IF EXISTS `meal_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal_plan` (
  `meal_id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_name` varchar(300) NOT NULL,
  PRIMARY KEY (`meal_id`),
  UNIQUE KEY `meal_name` (`meal_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_plan`
--

LOCK TABLES `meal_plan` WRITE;
/*!40000 ALTER TABLE `meal_plan` DISABLE KEYS */;
INSERT INTO `meal_plan` VALUES (1,'Chicken biryani'),(3,'Daal rice'),(2,'Vada pav');
/*!40000 ALTER TABLE `meal_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger_details`
--

DROP TABLE IF EXISTS `passenger_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passenger_details` (
  `passport_no` varchar(8) NOT NULL,
  `booking_id` int(11) NOT NULL,
  `passenger_last_name` varchar(45) NOT NULL,
  `passenger_first_name` varchar(45) NOT NULL,
  `meal_id` int(11) NOT NULL,
  KEY `booking_id_idx` (`booking_id`),
  CONSTRAINT `booking_id` FOREIGN KEY (`booking_id`) REFERENCES `booked_flights` (`booking_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger_details`
--

LOCK TABLES `passenger_details` WRITE;
/*!40000 ALTER TABLE `passenger_details` DISABLE KEYS */;
INSERT INTO `passenger_details` VALUES ('n',31,'sanju','h',1),('n',31,'sanju','h',1),('n',32,'sanju','h',1),('n',32,'sanju','h',1),('n',33,'sanju','h',1),('n',33,'sanju','h',1),('n',34,'sanju','h',1),('n',34,'sanju','h',1);
/*!40000 ALTER TABLE `passenger_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registered_user`
--

DROP TABLE IF EXISTS `registered_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registered_user` (
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `emailid` varchar(200) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `emailid_UNIQUE` (`emailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered_user`
--

LOCK TABLES `registered_user` WRITE;
/*!40000 ALTER TABLE `registered_user` DISABLE KEYS */;
INSERT INTO `registered_user` VALUES ('sadiq','19','yousuf','sadiq','yousufsadiq19@gmail.com'),('sanju','sanju','sanju','kh','sanjukh68@gmail.com'),('y','1','a','b','a'),('yousuf','yousuf','yousuf sadiq','mohammed','yousufsadiq11@gmail.com');
/*!40000 ALTER TABLE `registered_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'planmytravel'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `e_hourly` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8 */ ;;
/*!50003 SET character_set_results = utf8 */ ;;
/*!50003 SET collation_connection  = utf8_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `e_hourly` ON SCHEDULE EVERY 1 SECOND STARTS '2016-11-20 21:05:49' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Clears out sessions table each hour.' DO begin
    declare user1 varchar(200);
        declare id int(200);
select b.user_name into user1 from booked_flights b inner join flight_details f on f.flight_id=b.flight_id where year(curdate())-year(date_of_departure)=0 and month(curdate())-month(date_of_departure)=0 and day(date_of_departure)-day(curdate())>0 and day(date_of_departure)-day(curdate())<2;
select b.booking_id into id from booked_flights b inner join flight_details f on f.flight_id=b.flight_id where year(curdate())-year(date_of_departure)=0 and month(curdate())-month(date_of_departure)=0 and day(date_of_departure)-day(curdate())>0 and day(date_of_departure)-day(curdate())<2;
      select email from registered_user where user_name=user1;
      insert into email_notification (booking_id,email) values(id,user1);
         END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'planmytravel'
--
/*!50003 DROP FUNCTION IF EXISTS `check_in` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `check_in`(id int) RETURNS int(11)
BEGIN
   
 DECLARE flag int(10);
 SELECT checkin_flag into flag  FROM booked_flights Where booking_id=id;
 if(flag=0) then
 update booked_flights set checkin_flag=1 where booking_id=id;
 set flag=1;
 else
 set flag=0;
end if;
return flag;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `feedback` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `feedback`(id int,feedback int) RETURNS int(11)
BEGIN
   
 DECLARE flag int(10);
 SELECT feedback_flag into flag  FROM booked_flights Where booking_id=id;
 if(flag=0) then
 update booked_flights set feedback_flag=feedback where booking_id=id;
 set flag=1;
 else
 set flag=0;
end if;
return flag;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `book_flight` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `book_flight`(in f_id int,in user_name text,in economy int,in business int,IN passenger_passport TEXT,In passenger_meal text,IN passenger_last_name TEXT,IN passenger_first_name TEXT)
BEGIN
declare x int(20);
declare y int(20);
declare id int(20);

select economy_seat_count into x from flight_details where flight_id=f_id;
select business_seat_count into y from flight_details where flight_id=f_id;
if(x>=economy && y>=business) then
update flight_details set economy_seat_count=x-economy,business_seat_count=y-business where flight_id=f_id;
call insert_booking_details(user_name,f_id,economy,business);
select max(booking_id) into id from booked_flights;
call insert_passenger_details(id,passenger_passport,passenger_meal,passenger_last_name,passenger_first_name);
END if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_booking_details` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_booking_details`(in id int)
BEGIN
delete from passenger_details where booking_id=id;
delete from booked_flights where booking_id=id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_flight_details` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_flight_details`(in id int)
BEGIN
declare b_id int(200);
select booking_id into b_id from booked_flights where flight_id=id;
delete from passenger_details where booking_id=b_id;
delete from booked_flights where booking_id=b_id;
delete from flight_details where flight_id=id;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_meal_plan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_meal_plan`(in name1 text)
BEGIN
delete from meal_plan where meal_name=name1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_admin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_admin`(in user1 text,in pass text,in email_id text,in first1 text,in last1 text)
BEGIN
insert into admin values(user1,pass,email_id,first1,last1);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_booking_details` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_booking_details`(IN user_name Text,In flight_id int,IN economy int,IN business int)
BEGIN

insert into booked_flights (user_name,flight_id,no_of_economy_seats,no_of_business_seats,seats_booked) values(user_name,flight_id,economy,business,economy+business);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_card_details` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_card_details`(IN card_no int,In name_on_card Text,IN cvv int,IN card_type text,in user_name text,in payment_method text,in expiry_date date)
BEGIN
insert into card_details(card_no,name_on_card,cvv,card_type,user_name,payment_method,expiry_date)values(card_no,name_on_card,cvv,card_type,user_name,payment_method,expiry_date);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_flight_details` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_flight_details`(in number1 text,in a_time time,in d_time time,in d_arrival date,in d_departure date,in economy int,in business int,in sourcename text,in destname text)
BEGIN
declare sid int(100);
declare did int(100);
select city_id into sid from the user where city_name=sourcename;
select city_id into did from the user where city_name=destname;
insert into flight_details(flight_number,arrival_time,departure_time,date_of_arrival,date_of_departure,economy_seat_count,business_seat_count,source_id,destination_id) values(number1,a_time,d_time,d_arrival,d_departure,economy,business,sid,did);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_meal_plan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_meal_plan`(in name text)
BEGIN
insert into meal_plan(meal_name) values(name);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_passenger_details` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_passenger_details`(IN id int,IN passenger_passport TEXT,In passenger_meal text,IN passenger_last_name TEXT,IN passenger_first_name TEXT)
BEGIN
    SET @passenger_last_name = passenger_last_name;
     SET @passenger_first_name = passenger_first_name;
          SET @passenger_meal = passenger_meal;
          SET @passenger_passport = passenger_passport;
         set @id=id;

    WHILE (LOCATE(',', @passenger_last_name) >0)
    DO
        SET @value1 = LEFT(@passenger_last_name, LOCATE(',',@passenger_last_name) - 1);    
        SET @passenger_last_name = SUBSTRING(@passenger_last_name, LOCATE(',',@passenger_last_name) + 1);
         SET @value2 = LEFT(@passenger_first_name, LOCATE(',',@passenger_first_name) - 1);    
        SET @passenger_first_name = SUBSTRING(@passenger_first_name, LOCATE(',',@passenger_first_name) + 1);
         SET @value3 = LEFT(@passenger_meal, LOCATE(',',@passenger_meal) - 1);    
        SET @passenger_meal = SUBSTRING(@passenger_meal, LOCATE(',',@passenger_meal) + 1);
        SET @value4 = LEFT(@passenger_passport, LOCATE(',',@passenger_passport) - 1);    
        SET @passenger_passport = SUBSTRING(@passenger_passport, LOCATE(',',@passenger_passport) + 1);
        insert into passenger_details (select @value4,(select id),'x','y',1);
        update passenger_details set passport_no=(select @value4),passenger_last_name=(select @value1),passenger_first_name=(select @value2),
        meal_id=(select @value3)
        where booking_id=(select id);
       
    
    END WHILE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_registered_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_registered_user`(in user1 text,in pass text,in email_id text,in first1 text,in last1 text)
BEGIN
insert into registered_user values(user1,pass,first1,last1,email_id);
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

-- Dump completed on 2016-11-21  0:48:17
