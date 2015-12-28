/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : auto_marking

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-12-24 23:09:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL auto_increment,
  `classname` varchar(32) default NULL,
  `username` varchar(32) default NULL,
  `introduce` varchar(1024) default '',
  PRIMARY KEY  (`id`),
  KEY `fk_user_id` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('8', 'android1513', null, '安卓就业班');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `score` int(3) NOT NULL default '0',
  `class_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_class_id` (`class_id`),
  CONSTRAINT `fk_class_id` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of score
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) default NULL,
  `errors` int(4) default '0',
  `rights` int(4) default '0',
  `age` int(2) default NULL,
  `phone` varchar(11) default NULL,
  `classname` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '张三', '1', '3', '20', '13578912345', 'android1513');
INSERT INTO `student` VALUES ('2', '李四', '2', '3', '26', '13578912346', 'android1513');
INSERT INTO `student` VALUES ('3', '王五', '2', '2', '20', '13578912347', 'android1513');
INSERT INTO `student` VALUES ('4', '赵六', '0', '2', '27', '13578912348', 'android1513');
INSERT INTO `student` VALUES ('5', '田七', '1', '2', '28', '13578912349', 'android1513');
INSERT INTO `student` VALUES ('6', '王八', '0', '0', '24', '13578912340', 'android1513');
INSERT INTO `student` VALUES ('7', '久久', '0', '0', '25', '13578912341', 'android1513');
INSERT INTO `student` VALUES ('8', '十全', '1', '0', '20', '13578912342', 'android1513');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(64) default NULL,
  `password` varchar(8) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hgz', '123456');
INSERT INTO `user` VALUES ('2', 'good', '123');
INSERT INTO `user` VALUES ('3', 'andy', '123');
