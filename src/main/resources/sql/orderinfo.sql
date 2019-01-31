CREATE TABLE `orderinfo` (
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单id',
  `order_number` varchar(50) DEFAULT NULL COMMENT '订单号码',
  `order_sercet` varchar(100) DEFAULT NULL COMMENT '订单密码',
  `order_status` varchar(2) DEFAULT NULL COMMENT '订单状态',
  `order_can_send` varchar(20) DEFAULT NULL COMMENT '订单可否能发货',
  `order_is_send` char(1) DEFAULT NULL COMMENT '订单是否已发货',
  `order_customer` char(1) DEFAULT NULL COMMENT '订单客户',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT '1990-01-01 00:00:00' COMMENT '修改时间',
  `update_person` varchar(100) DEFAULT NULL COMMENT '修改人',
  `user_openid` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
