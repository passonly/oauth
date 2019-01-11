package com.oauth.service.impl;

import com.github.pagehelper.PageHelper;
import com.oauth.dao.OrderInfoMapper;
import com.oauth.entity.OrderInfo;
import com.oauth.entity.PageBean;
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

    public List<OrderInfo> selectByEntity(int currentPage, int pageSize,OrderInfo orderInfo) {

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        List<OrderInfo> allItems = orderInfoMapper.selectByEntity(orderInfo);        //全部商品
        int countNums = orderInfoMapper.count();            //总记录数
        PageBean<OrderInfo> pageData = new PageBean(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData.getItems();
    }

    public void insert(OrderInfo orderInfo) {
        orderInfoMapper.insert(orderInfo);
    }

    public void update(OrderInfo orderInfo) {
        orderInfoMapper.updateByPrimaryKey(orderInfo);
    }

    public OrderInfo selectByPrimaryKey(String id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }
}
