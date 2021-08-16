CREATE TABLE `vehicle` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `capacity` tinyint(1) NOT NULL,
  `production_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_vehicle_1` (`production_date`)
) ENGINE=InnoDB;
