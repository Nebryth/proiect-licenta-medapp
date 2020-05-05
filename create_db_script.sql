DROP DATABASE IF EXISTS `med-app-schema`;
CREATE DATABASE IF NOT EXISTS `med-app-schema`;
USE `med-app-schema`;

DROP TABLE IF EXISTS `allergies`;
CREATE TABLE IF NOT EXISTS `allergies` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `additionalinfo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `patients`;
CREATE TABLE IF NOT EXISTS `patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `telephone` varchar(14) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `secret` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (firstname, lastname, email),
  UNIQUE KEY `patients_secret_UNIQUE` (`secret`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `prescriptions`;
CREATE TABLE IF NOT EXISTS `prescriptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `items` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customername` varchar(45) NOT NULL,
  `customeraddress` varchar(100) NOT NULL,
  `customerphone` varchar(14) DEFAULT NULL,
  UNIQUE (customername, customeraddress, customerphone),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


# Tables with constraints
DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(128) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `telephone` varchar(14) DEFAULT NULL,
  `secret` varchar(128) DEFAULT NULL,
  `customerid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `staff_secret_UNIQUE` (`secret`),
  UNIQUE (firstname, lastname, email),
  KEY `fkcustomer_idx` (`customerid`),
  CONSTRAINT `fkcustomer` FOREIGN KEY (`customerId`) REFERENCES `customers` (`id`),
  CONSTRAINT `fkrole` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `appointments`;
CREATE TABLE IF NOT EXISTS `appointments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctorid` int(11) NOT NULL,
  `patientid` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doctorid_idx` (`doctorid`),
  KEY `parientid_idx` (`id`,`patientid`),
  KEY `patientid_idx` (`patientid`),
  KEY `patientidfk_idx` (`patientid`),
  CONSTRAINT `doctorid` FOREIGN KEY (`doctorid`) REFERENCES `staff` (`id`),
  CONSTRAINT `patientidfk` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `examinations`;
CREATE TABLE IF NOT EXISTS `examinations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `symptoms` varchar(45) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  `bloodpressure` varchar(45) DEFAULT NULL,
  `heartrate` int(11) DEFAULT NULL,
#   `diagnoseid` int(11) NOT NULL,
  `appointmentid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `appointmentidfk` FOREIGN KEY (`appointmentid`) REFERENCES `appointments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `diagonoses`;
CREATE TABLE IF NOT EXISTS `diagonoses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctorid` int(11) NOT NULL,
  `patientid` int(11) NOT NULL,
  `items` varchar(45) DEFAULT NULL,
  `examinationid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `diagnoses_examinationidfk` FOREIGN KEY (`examinationid`) REFERENCES `examinations` (`id`),
  CONSTRAINT `diagnoses_doctoridfk` FOREIGN KEY (`doctorid`) REFERENCES `staff` (`id`),
  CONSTRAINT `diagnoses_patientidfk` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `familyantecedents`;
CREATE TABLE IF NOT EXISTS `familyantecedents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patientid` int(11) DEFAULT NULL,
  `kinshipdegree` int(11) DEFAULT NULL,
  `diseases` text,
  PRIMARY KEY (`id`),
  KEY `patientidantecfk_idx` (`patientid`),
  CONSTRAINT `patientidantecfk` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `patientallergies`;
CREATE TABLE IF NOT EXISTS `patientallergies` (
  `allergyid` int(11) NOT NULL,
  `patientid` int(11) NOT NULL,
  KEY `allergyid_idx` (`allergyid`),
  KEY `patientid_idx` (`patientid`),
  CONSTRAINT `allergyid` FOREIGN KEY (`allergyid`) REFERENCES `allergies` (`id`),
  CONSTRAINT `patientid` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `labanalysis`;
CREATE TABLE IF NOT EXISTS `labanalysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patientid` int(11) NOT NULL,
  `contents` text NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `patientidanlysfk_idx` (`patientid`),
  CONSTRAINT `patientidanlysfk` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `patienthistory`;
CREATE TABLE IF NOT EXISTS `patienthistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patientid` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `height` double DEFAULT null,
  `bloodpressure` varchar(45) DEFAULT NULL,
  `heartrate` varchar(45) DEFAULT NULL,
  `diseases` varchar(45) DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `patientidfk_idx` (`patientid`),
  CONSTRAINT `patienthistoryfk` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `patientprescriptions`;
CREATE TABLE IF NOT EXISTS `patientprescriptions` (
  `prescriptionid` int(11) NOT NULL,
  `patientid` int(11) NOT NULL,
  `doctorid` int(11) NOT NULL,
  PRIMARY KEY (prescriptionid, patientid, doctorid),
  KEY `prescriptionidfk_idx` (`prescriptionid`),
  KEY `patientidfk_idx` (`patientid`),
  KEY `doctoridprescfk_idx` (`doctorid`),
  CONSTRAINT `doctoridprescfk` FOREIGN KEY (`doctorid`) REFERENCES `staff` (`id`),
  CONSTRAINT `patientidprescfk` FOREIGN KEY (`patientid`) REFERENCES `patients` (`id`),
  CONSTRAINT `prescriptionid` FOREIGN KEY (`prescriptionid`) REFERENCES `prescriptions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `rolepermissions`;
CREATE TABLE IF NOT EXISTS `rolepermissions` (
    `roleid` int(11) NOT NULL,
    `permissionid` int(11) NOT NULL,
    UNIQUE (roleid, permissionid),
    CONSTRAINT `rolepermissions_roles_id` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`),
    CONSTRAINT `rolepermissions_permissions_id` FOREIGN KEY (`permissionid`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
