start transaction;

create database `Acme-Handy-Worker`;

use `Acme-Handy-Worker`

create user 'acme-user'@'%' 
	identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' 
	identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Handy-Worker`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
   on `Acme-Handy-Worker`.* to 'acme-manager'@'%';




-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Handy-Worker
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor_boxes`
--

DROP TABLE IF EXISTS `actor_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_boxes` (
  `actor` int(11) NOT NULL,
  `boxes` int(11) NOT NULL,
  UNIQUE KEY `UK_6n6psqivvjho155qcf9kjvv1h` (`boxes`),
  CONSTRAINT `FK_6n6psqivvjho155qcf9kjvv1h` FOREIGN KEY (`boxes`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_boxes`
--

LOCK TABLES `actor_boxes` WRITE;
/*!40000 ALTER TABLE `actor_boxes` DISABLE KEYS */;
INSERT INTO `actor_boxes` VALUES (35,37),(35,38),(35,39),(35,40);
/*!40000 ALTER TABLE `actor_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_profiles`
--

DROP TABLE IF EXISTS `actor_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_profiles` (
  `actor` int(11) NOT NULL,
  `profiles` int(11) NOT NULL,
  UNIQUE KEY `UK_hdd4x67ooucg6f2yfe4edbs52` (`profiles`),
  CONSTRAINT `FK_hdd4x67ooucg6f2yfe4edbs52` FOREIGN KEY (`profiles`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_profiles`
--

LOCK TABLES `actor_profiles` WRITE;
/*!40000 ALTER TABLE `actor_profiles` DISABLE KEYS */;
INSERT INTO `actor_profiles` VALUES (35,36);
/*!40000 ALTER TABLE `actor_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (35,0,'Torreperojil 4','admin1@gmail.com','\0','\0','admin1','admin1','684143460','http://www.tumblr.com/photo','admin1',34);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `customer_comments` varchar(255) DEFAULT NULL,
  `hw_comments` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `price` double NOT NULL,
  `status` int(11) NOT NULL,
  `credit_card` int(11) DEFAULT NULL,
  `fix_up_task` int(11) NOT NULL,
  `handy_worker` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qrtc8d7gr5htxb67vvyl92du7` (`credit_card`),
  KEY `FK_i544pbdabjdesit0c4afue6pl` (`fix_up_task`),
  KEY `FK_ldpicm7in0u6b3qjcdo0v8n4c` (`handy_worker`),
  CONSTRAINT `FK_ldpicm7in0u6b3qjcdo0v8n4c` FOREIGN KEY (`handy_worker`) REFERENCES `handy_worker` (`id`),
  CONSTRAINT `FK_i544pbdabjdesit0c4afue6pl` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`),
  CONSTRAINT `FK_qrtc8d7gr5htxb67vvyl92du7` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_system` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (37,0,'','INBOX'),(38,0,'','OUTBOX'),(39,0,'','SPAMBOX'),(40,0,'','TRASHBOX');
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box_messages`
--

DROP TABLE IF EXISTS `box_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box_messages` (
  `box` int(11) NOT NULL,
  `messages` int(11) NOT NULL,
  KEY `FK_acfjrqu1jeixjmv14c0386o0s` (`messages`),
  KEY `FK_e6boieojekgfg919on0dci4na` (`box`),
  CONSTRAINT `FK_e6boieojekgfg919on0dci4na` FOREIGN KEY (`box`) REFERENCES `box` (`id`),
  CONSTRAINT `FK_acfjrqu1jeixjmv14c0386o0s` FOREIGN KEY (`messages`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box_messages`
--

LOCK TABLES `box_messages` WRITE;
/*!40000 ALTER TABLE `box_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `box_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `nameen` varchar(255) DEFAULT NULL,
  `namees` varchar(255) DEFAULT NULL,
  `parents` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_henwwe05xaqitcxee4tm663nw` (`parents`),
  CONSTRAINT `FK_henwwe05xaqitcxee4tm663nw` FOREIGN KEY (`parents`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (42,0,'CATEGORY','CATEGORÍA',NULL),(43,0,'Carpentry','Carpintería',42),(44,1,'Ceiling Repair','Reparación de techo',64),(45,0,'Cleaning','Limpieza',42),(46,0,'Concrete Work','Trabajo Concreto',42),(47,0,'Doors','Puertas',42),(48,1,'Electrical Wiring','Cableado Eléctrico',66),(49,1,'Fan Installation','Instalación de ventilación',65),(50,1,'Fence Fixing','Reparación de vallado',64),(51,1,'Home Security Systems','Sistemas de seguridad para el hogar',66),(52,1,'Insulation Installation','Instalación de aislamiento',65),(53,1,'Lamp Repair','Reparación de lámparas',64),(54,0,'Moving','Mudanza',42),(55,0,'Painting','Pintura',42),(56,0,'Pest Control','Control de Plagas',42),(57,1,'Plumbing Repair','Reparación de fontanería',64),(58,0,'Roofing','Techado',42),(59,1,'ShelfInstallation','Instalación de estantería',65),(60,1,'Solar Panels','Paneles Solares',66),(61,1,'Sondproofing','Insonorización',65),(62,1,'Sprinkler Repair','Reparación de Aspersores',64),(63,1,'Window Repair','Reparación de ventanas',64),(64,0,'Repairments','Reparaciones',42),(65,0,'Installations','Instalaciones',42),(66,0,'Electronic','Electrónica',42);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `customer` int(11) NOT NULL,
  `fix_up_tasks` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jlpo668tu0b8mmsjsg8g13inu` (`ticker`),
  KEY `FK_f3jhv2pg69qixbskj7vl99pm9` (`customer`),
  KEY `FK_fggvnx54g9eo1r9rnynsqmesl` (`fix_up_tasks`),
  CONSTRAINT `FK_fggvnx54g9eo1r9rnynsqmesl` FOREIGN KEY (`fix_up_tasks`) REFERENCES `fix_up_task` (`id`),
  CONSTRAINT `FK_f3jhv2pg69qixbskj7vl99pm9` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint_attachment`
--

DROP TABLE IF EXISTS `complaint_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint_attachment` (
  `complaint` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  KEY `FK_gf1y3fnhbb27v294jickim98o` (`complaint`),
  CONSTRAINT `FK_gf1y3fnhbb27v294jickim98o` FOREIGN KEY (`complaint`) REFERENCES `complaint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint_attachment`
--

LOCK TABLES `complaint_attachment` WRITE;
/*!40000 ALTER TABLE `complaint_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `vatpercent` int(11) NOT NULL,
  `bannerurl` varchar(255) DEFAULT NULL,
  `max_results` int(11) NOT NULL,
  `max_time` int(11) NOT NULL,
  `page_name` varchar(255) DEFAULT NULL,
  `phoneccode` int(11) NOT NULL,
  `welcomeen` varchar(255) DEFAULT NULL,
  `welcomees` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (41,0,21,'https://tinyurl.com/acme-handy-worker-logo',10,1,'Acme-Handy-Worker',34,'Welcome to Acme Handy Worker! Price, quality, and trust in a single place','¡Bienvenidos a Acme Handy Worker! Precio, calidad y confianza en el mismo sitio');
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_c_cards_makes`
--

DROP TABLE IF EXISTS `configuration_c_cards_makes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_c_cards_makes` (
  `configuration` int(11) NOT NULL,
  `c_cards_makes` varchar(255) DEFAULT NULL,
  KEY `FK_eb4co3kgkw6ldth8ju0a5j3v0` (`configuration`),
  CONSTRAINT `FK_eb4co3kgkw6ldth8ju0a5j3v0` FOREIGN KEY (`configuration`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_c_cards_makes`
--

LOCK TABLES `configuration_c_cards_makes` WRITE;
/*!40000 ALTER TABLE `configuration_c_cards_makes` DISABLE KEYS */;
INSERT INTO `configuration_c_cards_makes` VALUES (41,'AMEX'),(41,'VISA'),(41,'MASTER'),(41,'DINNERS');
/*!40000 ALTER TABLE `configuration_c_cards_makes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_spam_words`
--

DROP TABLE IF EXISTS `configuration_spam_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_spam_words` (
  `configuration` int(11) NOT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  KEY `FK_qk3m1jkx4rq87ofjee19f3b33` (`configuration`),
  CONSTRAINT `FK_qk3m1jkx4rq87ofjee19f3b33` FOREIGN KEY (`configuration`) REFERENCES `configuration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_spam_words`
--

LOCK TABLES `configuration_spam_words` WRITE;
/*!40000 ALTER TABLE `configuration_spam_words` DISABLE KEYS */;
INSERT INTO `configuration_spam_words` VALUES (41,'sex'),(41,'viagra'),(41,'cialis'),(41,'one million'),(41,'you\'ve been selected'),(41,'Nigeria'),(41,'sexo'),(41,'un millon'),(41,'has sido seleccionado');
/*!40000 ALTER TABLE `configuration_spam_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `month` int(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula`
--

DROP TABLE IF EXISTS `curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `personal_record` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gent7yxpsyplweumuql7kiyqh` (`ticker`),
  KEY `FK_2tuytvvko9f96rtan6nwbpuei` (`personal_record`),
  CONSTRAINT `FK_2tuytvvko9f96rtan6nwbpuei` FOREIGN KEY (`personal_record`) REFERENCES `personal_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula`
--

LOCK TABLES `curricula` WRITE;
/*!40000 ALTER TABLE `curricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_educational_record`
--

DROP TABLE IF EXISTS `curricula_educational_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_educational_record` (
  `curricula` int(11) NOT NULL,
  `educational_record` int(11) NOT NULL,
  UNIQUE KEY `UK_3qh1lvid80oqlck0dqciu1kk7` (`educational_record`),
  KEY `FK_mj8yv0h8flms89325frqvi4yy` (`curricula`),
  CONSTRAINT `FK_mj8yv0h8flms89325frqvi4yy` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_3qh1lvid80oqlck0dqciu1kk7` FOREIGN KEY (`educational_record`) REFERENCES `educational_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_educational_record`
--

LOCK TABLES `curricula_educational_record` WRITE;
/*!40000 ALTER TABLE `curricula_educational_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curricula_educational_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_endorser_record`
--

DROP TABLE IF EXISTS `curricula_endorser_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_endorser_record` (
  `curricula` int(11) NOT NULL,
  `endorser_record` int(11) NOT NULL,
  UNIQUE KEY `UK_61mli4cpjs6itmtxfop04k8m` (`endorser_record`),
  KEY `FK_p3d6f326boeq7l4a35qjtqf2k` (`curricula`),
  CONSTRAINT `FK_p3d6f326boeq7l4a35qjtqf2k` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_61mli4cpjs6itmtxfop04k8m` FOREIGN KEY (`endorser_record`) REFERENCES `endorser_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_endorser_record`
--

LOCK TABLES `curricula_endorser_record` WRITE;
/*!40000 ALTER TABLE `curricula_endorser_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curricula_endorser_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_misc_record`
--

DROP TABLE IF EXISTS `curricula_misc_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_misc_record` (
  `curricula` int(11) NOT NULL,
  `misc_record` int(11) NOT NULL,
  UNIQUE KEY `UK_gql8e0cigtqefu311batjelg6` (`misc_record`),
  KEY `FK_f3csm1q2f3isb9ihdlf5jwmh3` (`curricula`),
  CONSTRAINT `FK_f3csm1q2f3isb9ihdlf5jwmh3` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_gql8e0cigtqefu311batjelg6` FOREIGN KEY (`misc_record`) REFERENCES `misc_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_misc_record`
--

LOCK TABLES `curricula_misc_record` WRITE;
/*!40000 ALTER TABLE `curricula_misc_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curricula_misc_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_professional_record`
--

DROP TABLE IF EXISTS `curricula_professional_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_professional_record` (
  `curricula` int(11) NOT NULL,
  `professional_record` int(11) NOT NULL,
  UNIQUE KEY `UK_7gcy0gsj1ptb9qwxy4olu88gk` (`professional_record`),
  KEY `FK_cu80w5sjj78kwe1y4fddla8ph` (`curricula`),
  CONSTRAINT `FK_cu80w5sjj78kwe1y4fddla8ph` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_7gcy0gsj1ptb9qwxy4olu88gk` FOREIGN KEY (`professional_record`) REFERENCES `professional_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_professional_record`
--

LOCK TABLES `curricula_professional_record` WRITE;
/*!40000 ALTER TABLE `curricula_professional_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curricula_professional_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mbvdes9ypo1yu76so76owiyqx` (`user_account`),
  CONSTRAINT `FK_mbvdes9ypo1yu76so76owiyqx` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educational_record`
--

DROP TABLE IF EXISTS `educational_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `educational_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `diplomas_title` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `studies_beggining` date DEFAULT NULL,
  `studies_ending` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educational_record`
--

LOCK TABLES `educational_record` WRITE;
/*!40000 ALTER TABLE `educational_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `educational_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorser_record`
--

DROP TABLE IF EXISTS `endorser_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorser_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `linked_in_profile` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorser_record`
--

LOCK TABLES `endorser_record` WRITE;
/*!40000 ALTER TABLE `endorser_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorser_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `date_finish_range` date DEFAULT NULL,
  `date_start_range` date DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `range_finish` int(11) DEFAULT NULL,
  `range_start` int(11) DEFAULT NULL,
  `single_key_word` varchar(255) DEFAULT NULL,
  `warranty_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_fix_up_task`
--

DROP TABLE IF EXISTS `finder_fix_up_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_fix_up_task` (
  `finder` int(11) NOT NULL,
  `fix_up_task` int(11) NOT NULL,
  KEY `FK_3vayn26asbv6xkwtfmo94xl3w` (`fix_up_task`),
  KEY `FK_rkebmdefvgdr5q0u7m1eeq253` (`finder`),
  CONSTRAINT `FK_rkebmdefvgdr5q0u7m1eeq253` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_3vayn26asbv6xkwtfmo94xl3w` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_fix_up_task`
--

LOCK TABLES `finder_fix_up_task` WRITE;
/*!40000 ALTER TABLE `finder_fix_up_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_fix_up_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fix_up_task`
--

DROP TABLE IF EXISTS `fix_up_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fix_up_task` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `estimated_date` datetime DEFAULT NULL,
  `max_price` double NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `category` int(11) NOT NULL,
  `customer` int(11) NOT NULL,
  `warranty` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1ucy18fywvpk17lfcafstl8p` (`ticker`),
  KEY `FK_rraseqm4xixdwpi08ac3s0wo5` (`category`),
  KEY `FK_a3nekh0t15hcur166hb4snvjg` (`customer`),
  KEY `FK_eeisx1c0ohidkpqgqbib91s6x` (`warranty`),
  CONSTRAINT `FK_eeisx1c0ohidkpqgqbib91s6x` FOREIGN KEY (`warranty`) REFERENCES `warranty` (`id`),
  CONSTRAINT `FK_a3nekh0t15hcur166hb4snvjg` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_rraseqm4xixdwpi08ac3s0wo5` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fix_up_task`
--

LOCK TABLES `fix_up_task` WRITE;
/*!40000 ALTER TABLE `fix_up_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `fix_up_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `handy_worker`
--

DROP TABLE IF EXISTS `handy_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `handy_worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  `finder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_leravvuqbwfdxtdxpc4ssiful` (`curricula`),
  KEY `FK_s80hn9dk7bcwsqotvtoo6wxr3` (`finder`),
  KEY `FK_jpa4nvxb706tgsd90160obc6r` (`user_account`),
  CONSTRAINT `FK_jpa4nvxb706tgsd90160obc6r` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_leravvuqbwfdxtdxpc4ssiful` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_s80hn9dk7bcwsqotvtoo6wxr3` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `handy_worker`
--

LOCK TABLES `handy_worker` WRITE;
/*!40000 ALTER TABLE `handy_worker` DISABLE KEYS */;
/*!40000 ALTER TABLE `handy_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `priority` int(11) NOT NULL,
  `send_date` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipient`
--

DROP TABLE IF EXISTS `message_recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipient` (
  `message` int(11) NOT NULL,
  `recipient` int(11) NOT NULL,
  KEY `FK_3070ehveirqwra9h3g8nxkqn1` (`message`),
  CONSTRAINT `FK_3070ehveirqwra9h3g8nxkqn1` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipient`
--

LOCK TABLES `message_recipient` WRITE;
/*!40000 ALTER TABLE `message_recipient` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_recipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `misc_record`
--

DROP TABLE IF EXISTS `misc_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `misc_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `misc_record`
--

LOCK TABLES `misc_record` WRITE;
/*!40000 ALTER TABLE `misc_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `misc_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `customer_comments` varchar(255) DEFAULT NULL,
  `hw_comments` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `referee_comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_record`
--

DROP TABLE IF EXISTS `personal_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `linked_in_profile` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_record`
--

LOCK TABLES `personal_record` WRITE;
/*!40000 ALTER TABLE `personal_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phase`
--

DROP TABLE IF EXISTS `phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phase` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `finish_moment` datetime DEFAULT NULL,
  `number` int(11) NOT NULL,
  `start_moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `fix_up_task` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cu5vf3n12srsghjtjckiuysvk` (`fix_up_task`),
  CONSTRAINT `FK_cu5vf3n12srsghjtjckiuysvk` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phase`
--

LOCK TABLES `phase` WRITE;
/*!40000 ALTER TABLE `phase` DISABLE KEYS */;
/*!40000 ALTER TABLE `phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professional_record`
--

DROP TABLE IF EXISTS `professional_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professional_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `job_beggining` date DEFAULT NULL,
  `job_ending` date DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional_record`
--

LOCK TABLES `professional_record` WRITE;
/*!40000 ALTER TABLE `professional_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `professional_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `social_net_name` varchar(255) DEFAULT NULL,
  `social_net_prof_link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (36,0,'Sergio Perez','Sergii','sergii1998','https://www.twitch.tv/');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referee`
--

DROP TABLE IF EXISTS `referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referee` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_suspicious` bit(1) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_303c1oipw0t6mbnnpvtfv70w5` (`user_account`),
  CONSTRAINT `FK_303c1oipw0t6mbnnpvtfv70w5` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee`
--

LOCK TABLES `referee` WRITE;
/*!40000 ALTER TABLE `referee` DISABLE KEYS */;
/*!40000 ALTER TABLE `referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_final` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `complaint` int(11) NOT NULL,
  `referee` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_48rqaecflpcs8unotw4drrtfw` (`complaint`),
  KEY `FK_tdhsgxfp2kgx2mqkwh1l51x8j` (`referee`),
  CONSTRAINT `FK_tdhsgxfp2kgx2mqkwh1l51x8j` FOREIGN KEY (`referee`) REFERENCES `referee` (`id`),
  CONSTRAINT `FK_48rqaecflpcs8unotw4drrtfw` FOREIGN KEY (`complaint`) REFERENCES `complaint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_notes`
--

DROP TABLE IF EXISTS `report_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_notes` (
  `report` int(11) NOT NULL,
  `notes` int(11) NOT NULL,
  UNIQUE KEY `UK_m22isl38uqck3fp3rmsmbnh5k` (`notes`),
  KEY `FK_o7r37khw24dlpe07md1qqakce` (`report`),
  CONSTRAINT `FK_o7r37khw24dlpe07md1qqakce` FOREIGN KEY (`report`) REFERENCES `report` (`id`),
  CONSTRAINT `FK_m22isl38uqck3fp3rmsmbnh5k` FOREIGN KEY (`notes`) REFERENCES `note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_notes`
--

LOCK TABLES `report_notes` WRITE;
/*!40000 ALTER TABLE `report_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (34,0,'21232f297a57a5a743894a0e4a801fc3','admin');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (34,'ADMIN');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty`
--

DROP TABLE IF EXISTS `warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranty` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `applicable_laws` varchar(255) DEFAULT NULL,
  `is_final` bit(1) NOT NULL,
  `terms` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty`
--

LOCK TABLES `warranty` WRITE;
/*!40000 ALTER TABLE `warranty` DISABLE KEYS */;
/*!40000 ALTER TABLE `warranty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-15 19:00:24
commit;
