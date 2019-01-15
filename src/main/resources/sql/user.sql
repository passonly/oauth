/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : huishanghe

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-01-15 19:02:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `user_openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `user_password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `user_nickname` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `user_sex` varchar(30) DEFAULT NULL COMMENT '性别，男：1，女：0',
  `user_country` varchar(2) DEFAULT NULL COMMENT '国家',
  `user_province` char(1) DEFAULT NULL COMMENT '省份',
  `user_city` char(1) DEFAULT NULL COMMENT '城市',
  `user_headImgUrl` varchar(200) DEFAULT NULL COMMENT '头像连接地址',
  `user_role` char(1) DEFAULT NULL COMMENT '用户角色，管理员：0，客户：1',
  `user_phone` char(1) DEFAULT NULL COMMENT '用户电话',
  `user_status` char(1) DEFAULT NULL COMMENT '用户状态，可用：0，不可用：1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `update_person` varchar(100) DEFAULT NULL COMMENT '修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'aaa', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:32', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '2', 'ccc', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:37', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '3', 'aaabbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-11 11:15:45', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '4', 'dddaaa', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:39', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '5', 'aaaddd', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:41', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '6', 'dddaaa', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:43', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '7', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:45', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '8', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:47', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '9', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:49', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '10', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:52', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '11', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:53', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '12', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:54', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '13', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:57', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '14', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:45:59', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '15', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:46:01', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '16', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:46:03', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '17', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:46:05', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '18', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:46:07', 'create_person', '0000-00-00 00:00:00', 'update_person');
INSERT INTO `user` VALUES ('1', '19', 'bbb', 'user_password', 'user_nickname', 'user_sex', 'us', 'u', 'u', 'user_headImgUrl', 'u', 'u', 'u', '2019-01-14 11:46:10', 'create_person', '0000-00-00 00:00:00', 'update_person');
