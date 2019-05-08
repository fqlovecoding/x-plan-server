CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;