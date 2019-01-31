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
  `user_country` varchar(20) DEFAULT NULL COMMENT '国家',
  `user_province` varchar(100) DEFAULT NULL COMMENT '省份',
  `user_city` varchar(100) DEFAULT NULL COMMENT '城市',
  `user_headImgUrl` varchar(200) DEFAULT NULL COMMENT '头像连接地址',
  `user_role` char(1) DEFAULT NULL COMMENT '用户角色，管理员：0，客户：1',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `user_status` char(1) DEFAULT NULL COMMENT '用户状态，可用：0，不可用：1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '修改时间',
  `update_person` varchar(100) DEFAULT NULL COMMENT '修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
