package com.oauth.controller;

import com.oauth.entity.OrderInfo;
import com.oauth.service.OrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/9 17:43.
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     *
     * @return
     */
    @RequestMapping("/getList")
    public List<OrderInfo> getList(int currentPage,int pageSize){
        List<OrderInfo> orderInfos = orderInfoService.selectByEntity(currentPage, pageSize);
        return orderInfos;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/insert")
    public void insertOrder(OrderInfo orderInfo){
        orderInfo.setOrderId(UUID.randomUUID().toString());
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfoService.insert(orderInfo);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/selectById")
    public OrderInfo selectByPrimaryKey(String id){

       return  orderInfoService.selectByPrimaryKey(id);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/update")
    public void upate(OrderInfo orderInfo){
        orderInfoService.update(orderInfo);
    }

}
