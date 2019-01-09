package com.oauth.service.impl;

import com.oauth.dao.OrderInfoMapper;
import com.oauth.entity.OrderInfo;
import com.oauth.service.OrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/8 12:33.
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> selectByEntity() {
        return orderInfoMapper.selectByEntity();
    }

    @Override
    public void insertOrder(OrderInfo orderInfo) {
        orderInfoMapper.insertOrder(orderInfo);
    }
}
