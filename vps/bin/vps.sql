/*
SQLyog Community v10.12 
MySQL - 5.5.36-MariaDB : Database - vps
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vps` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `vps`;

/*Table structure for table `vps_appointment_details` */

DROP TABLE IF EXISTS `vps_appointment_details`;

CREATE TABLE `vps_appointment_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `mother_name` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `birth_place` varchar(100) DEFAULT NULL,
  `nationality` int(11) DEFAULT NULL,
  `present_nationality` int(11) DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  `profession` varchar(100) DEFAULT NULL,
  `home_address` varchar(500) DEFAULT NULL,
  `home_address_contact` varchar(100) DEFAULT NULL,
  `office_address` varchar(500) DEFAULT NULL,
  `office_address_contact` varchar(100) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `marital_status` int(11) DEFAULT NULL,
  `religion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sex_fk_id` (`sex`),
  KEY `marital_status_fk_id` (`marital_status`),
  KEY `religion_fk_id` (`religion`),
  KEY `nationality_fk_id` (`nationality`),
  KEY `present_nationality_fk_id` (`present_nationality`),
  CONSTRAINT `present_nationality_fk_id` FOREIGN KEY (`present_nationality`) REFERENCES `vps_appointment_nationality` (`id`),
  CONSTRAINT `marital_status_fk_id` FOREIGN KEY (`marital_status`) REFERENCES `vps_appointment_marital_status` (`id`),
  CONSTRAINT `nationality_fk_id` FOREIGN KEY (`nationality`) REFERENCES `vps_appointment_nationality` (`id`),
  CONSTRAINT `religion_fk_id` FOREIGN KEY (`religion`) REFERENCES `vps_appointment_religion` (`id`),
  CONSTRAINT `sex_fk_id` FOREIGN KEY (`sex`) REFERENCES `vps_appointment_sex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `vps_appointment_details` */

insert  into `vps_appointment_details`(`id`,`name`,`mother_name`,`dob`,`birth_place`,`nationality`,`present_nationality`,`qualification`,`profession`,`home_address`,`home_address_contact`,`office_address`,`office_address_contact`,`sex`,`marital_status`,`religion`) values (7,'raju','ramana','1991-04-15','warangal',1,2,'B.Tech','employee','hyderabad','1234','hyderabad','1234',1,1,1);

/*Table structure for table `vps_appointment_marital_status` */

DROP TABLE IF EXISTS `vps_appointment_marital_status`;

CREATE TABLE `vps_appointment_marital_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marital_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `vps_appointment_marital_status` */

insert  into `vps_appointment_marital_status`(`id`,`marital_status`) values (1,'Single'),(2,'Married');

/*Table structure for table `vps_appointment_nationality` */

DROP TABLE IF EXISTS `vps_appointment_nationality`;

CREATE TABLE `vps_appointment_nationality` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nationality` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `vps_appointment_nationality` */

insert  into `vps_appointment_nationality`(`id`,`nationality`) values (1,'India'),(2,'USA'),(3,'UK'),(4,'Australia');

/*Table structure for table `vps_appointment_origins` */

DROP TABLE IF EXISTS `vps_appointment_origins`;

CREATE TABLE `vps_appointment_origins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `country_fk_id` (`country_id`),
  CONSTRAINT `country_fk_id` FOREIGN KEY (`country_id`) REFERENCES `vps_appointment_nationality` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `vps_appointment_origins` */

insert  into `vps_appointment_origins`(`id`,`name`,`country_id`) values (1,'Hyderabad',1),(2,'Delhi',1),(4,'Mumbai',1),(5,'Chennai',1),(6,'Pune',1),(7,'Bangalore',1),(8,'New York',2),(9,'California',2),(10,'Sydney',4),(11,'Melborn',4),(12,'Brisbane',4);

/*Table structure for table `vps_appointment_religion` */

DROP TABLE IF EXISTS `vps_appointment_religion`;

CREATE TABLE `vps_appointment_religion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `religion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `vps_appointment_religion` */

insert  into `vps_appointment_religion`(`id`,`religion`) values (1,'Hindu'),(2,'Muslim'),(3,'Sikh'),(4,'Parsy');

/*Table structure for table `vps_appointment_sex` */

DROP TABLE IF EXISTS `vps_appointment_sex`;

CREATE TABLE `vps_appointment_sex` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `vps_appointment_sex` */

insert  into `vps_appointment_sex`(`id`,`sex`) values (1,'Male'),(2,'Female');

/*Table structure for table `vps_appointment_visa_category` */

DROP TABLE IF EXISTS `vps_appointment_visa_category`;

CREATE TABLE `vps_appointment_visa_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `vps_appointment_visa_category` */

insert  into `vps_appointment_visa_category`(`id`,`category_type`) values (1,'Business Visit'),(2,'Family Visit'),(3,'Special Visit'),(4,'Private Visit');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
