USE sys;
CREATE TABLE `credit` (
  `cid` varchar(255) DEFAULT NULL,
  `oid` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

USE ymdd;
CREATE TABLE `orders` (
  `oid` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;