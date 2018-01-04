/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-01-04 17:52:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `bookinfo`;
CREATE TABLE `bookinfo` (
  `id` varchar(32) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `bookconcern` varchar(255) DEFAULT NULL COMMENT '出版社',
  `publish_date` datetime DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `amount` int(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookinfo
-- ----------------------------
INSERT INTO `bookinfo` VALUES ('1', 'Java', '小红', '电子工业出版社', '2016-05-24 00:00:00', '99.00', '100', null);
INSERT INTO `bookinfo` VALUES ('2', 'C++', '小红', '电子工业出版社', '2016-05-24 00:00:00', '99.00', '100', null);
INSERT INTO `bookinfo` VALUES ('3', 'PHP', '小红', '电子工业出版社', '2016-05-24 00:00:00', '99.00', '100', null);
