/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : huishanghe

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-01-15 19:03:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单id',
  `order_number` varchar(100) DEFAULT NULL COMMENT '订单号码',
  `order_sercet` varchar(30) DEFAULT NULL COMMENT '订单密码',
  `order_status` varchar(2) DEFAULT NULL COMMENT '订单状态',
  `order_can_send` varchar(20) DEFAULT NULL COMMENT '订单可否能发货',
  `order_is_send` char(1) DEFAULT NULL COMMENT '订单是否已发货',
  `order_customer` char(1) DEFAULT NULL COMMENT '订单客户',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '修改时间',
  `update_person` varchar(100) DEFAULT NULL COMMENT '修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('1', '2', '3', '4', '0', '6', '7', '2019-01-14 16:50:13', '8', '2019-12-12 00:00:00', '9');
INSERT INTO `orderinfo` VALUES ('2', '3', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:16', '2', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('3', '4', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:17', '2', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('4', '5', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:18', '2', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('5', '6', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:19', '3', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('6', '7', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:20', '3', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('7', '8', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:19', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('8', '9', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:21', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('9', '10', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:22', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('10', '11', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:21', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('11', '12', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:23', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('12', '13', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:23', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('13', '14', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:24', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('14', '15', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:24', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('15', '16', 'order_sercet', 'or', '0', 'o', 'o', '2019-01-14 16:50:25', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `orderinfo` VALUES ('e6f1951e-7591-4e11-9a19-d36c097ef0a1', null, null, null, null, null, null, '2019-01-10 17:10:40', null, '2019-01-10 17:10:40', null);
