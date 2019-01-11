package com.oauth.service;

import com.oauth.entity.OrderInfo;

import java.util.List;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/8 12:32.
 */
public interface OrderInfoService {

    List<OrderInfo> selectByEntity(int currentPage, int pageSize,OrderInfo orderInfo);

    void insert(OrderInfo orderInfo);

    void update(OrderInfo orderInfo);

    OrderInfo selectByPrimaryKey(String id);
}
