/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : store2

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2020-03-14 19:56:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('87');
INSERT INTO `hibernate_sequence` VALUES ('87');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `action` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `u_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('54', '/stroe4/doLogin', '2020-03-11 17:22:41', 'merchant');
INSERT INTO `log` VALUES ('55', '/stroe4/doLogin', '2020-03-11 17:24:29', 'admin');
INSERT INTO `log` VALUES ('56', '/stroe4/admin/users', '2020-03-11 17:24:34', 'admin');
INSERT INTO `log` VALUES ('57', '/stroe4/doLogin', '2020-03-13 10:16:23', 'admin');
INSERT INTO `log` VALUES ('58', '/stroe4/doLogin', '2020-03-13 11:21:37', 'admin');
INSERT INTO `log` VALUES ('60', '/stroe4/doLogin', '2020-03-13 22:09:40', 'user');
INSERT INTO `log` VALUES ('61', '/stroe4/doLogin', '2020-03-13 22:17:27', 'user');
INSERT INTO `log` VALUES ('63', '/stroe4/doLogin', '2020-03-13 22:20:57', 'user');
INSERT INTO `log` VALUES ('67', '/stroe4/doLogin', '2020-03-13 22:29:03', 'user');
INSERT INTO `log` VALUES ('71', '/stroe4/doLogin', '2020-03-14 18:07:15', 'user');
INSERT INTO `log` VALUES ('72', '/stroe4/product', '2020-03-14 18:08:42', 'user');
INSERT INTO `log` VALUES ('73', '/stroe4/doLogin', '2020-03-14 18:20:22', 'user');
INSERT INTO `log` VALUES ('74', '/stroe4/doLogin', '2020-03-14 18:23:03', 'user');
INSERT INTO `log` VALUES ('75', '/stroe4/doLogin', '2020-03-14 18:25:05', 'user');
INSERT INTO `log` VALUES ('76', '/stroe4/doLogin', '2020-03-14 18:28:36', 'user');
INSERT INTO `log` VALUES ('77', '/stroe4/doLogin', '2020-03-14 18:30:03', 'user');
INSERT INTO `log` VALUES ('78', '/stroe4/doLogin', '2020-03-14 18:31:35', 'user');
INSERT INTO `log` VALUES ('79', '/stroe4/doLogin', '2020-03-14 18:34:54', 'user');
INSERT INTO `log` VALUES ('80', '/stroe4/doLogin', '2020-03-14 18:37:58', 'user');
INSERT INTO `log` VALUES ('81', '/stroe4/doLogin', '2020-03-14 18:44:43', 'user');
INSERT INTO `log` VALUES ('82', '/stroe4/doLogin', '2020-03-14 19:22:52', 'user');
INSERT INTO `log` VALUES ('83', '/stroe4/admin/allotRole', '2020-03-14 19:22:55', 'user');
INSERT INTO `log` VALUES ('84', '/stroe4/admin/allotRole', '2020-03-14 19:23:20', 'user');
INSERT INTO `log` VALUES ('85', '/stroe4/doLogin', '2020-03-14 19:40:50', 'user');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `Order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `buynum` int(3) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('13', '1', '2', '14');
INSERT INTO `orderitem` VALUES ('13', '37', '2', '15');
INSERT INTO `orderitem` VALUES ('41', '2', '2', '42');
INSERT INTO `orderitem` VALUES ('41', '37', '2', '43');
INSERT INTO `orderitem` VALUES ('44', '1', '2', '45');
INSERT INTO `orderitem` VALUES ('44', '37', '2', '46');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `money` double NOT NULL,
  `receiverAddress` varchar(255) NOT NULL,
  `receiverPhone` varchar(255) NOT NULL,
  `paystate` int(1) NOT NULL,
  `ordertime` datetime NOT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('13', '60', '山东', '000000', '0', '2020-03-01 16:46:58', '1');
INSERT INTO `orders` VALUES ('41', '30', '山西', '10541354223', '1', '2020-03-08 22:07:20', '1');
INSERT INTO `orders` VALUES ('44', '60', '山东', '10541354223', '0', '2020-03-08 22:13:23', '1');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `permission` varchar(255) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('2', '1', 'admin:user', '2020-03-10 11:15:22', '2020-03-10 11:15:25');
INSERT INTO `permission` VALUES ('3', '2', 'admin:product', '2020-03-10 11:15:38', '2020-03-10 11:15:40');
INSERT INTO `permission` VALUES ('4', '1', 'admin:role', '2020-03-10 11:15:58', '2020-03-10 11:16:01');
INSERT INTO `permission` VALUES ('5', '1', 'admin:log', '2020-03-10 11:16:24', '2020-03-10 11:16:27');
INSERT INTO `permission` VALUES ('6', '2', 'admin:order', '2020-03-10 11:17:25', '2020-03-10 11:17:28');
INSERT INTO `permission` VALUES ('7', '3', 'user:order', '2020-03-10 11:30:29', '2020-03-10 11:30:31');
INSERT INTO `permission` VALUES ('8', '3', 'user:user', '2020-03-10 12:01:12', '2020-03-10 12:01:17');
INSERT INTO `permission` VALUES ('9', '1', 'admin:permission', '2020-03-11 16:37:58', '2020-03-11 16:38:01');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `categoty` varchar(255) DEFAULT NULL,
  `Pnum` int(11) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', '1', '图书', '10.00', '计算机', '14', 'F:\\imgs\\875b3663400a83f16b90dc5fd95ec6cd.png', '123');
INSERT INTO `products` VALUES ('2', '1', '嘻嘻', '10.00', '食品', '12', 'F:\\imgs\\875b3663400a83f16b90dc5fd95ec6cd.png', '123');
INSERT INTO `products` VALUES ('37', '1', 'bbb', '20.00', '111', '18', null, null);

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('86', '59', '37', '4');

-- ----------------------------
-- Table structure for srole
-- ----------------------------
DROP TABLE IF EXISTS `srole`;
CREATE TABLE `srole` (
  `id` int(11) NOT NULL,
  `rname` varchar(255) NOT NULL,
  `rdesc` varchar(255) NOT NULL,
  `renable` int(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of srole
-- ----------------------------
INSERT INTO `srole` VALUES ('1', '超级管理员', '所有模块的权限', '1', '2020-03-10 11:19:27', '2020-03-10 11:19:29');
INSERT INTO `srole` VALUES ('2', '商场管理员', '只有商场模块的操作权限', '1', '2020-03-10 11:20:07', '2020-03-10 11:20:11');
INSERT INTO `srole` VALUES ('3', '普通用户', '只有查询商品和购买权限', '1', '2020-03-10 11:23:30', '2020-03-10 11:23:33');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('47', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', '1', '1546@qq.com', '2020-03-10 10:17:56');
INSERT INTO `user` VALUES ('48', 'merchant', '836daa6a33e5da26647f22b64d70143c', '2', '1546@qq.com', '2020-03-10 14:04:33');
INSERT INTO `user` VALUES ('59', 'user', '098d2c478e9c11555ce2823231e02ec1', '3', '1546@qq.com', '2020-03-13 22:08:20');
