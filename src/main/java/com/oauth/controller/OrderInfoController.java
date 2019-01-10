package com.oauth.controller;

import com.oauth.entity.OrderInfo;
import com.oauth.service.OrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String getList(){
        List<OrderInfo> orderInfos = orderInfoService.selectByEntity();
        return orderInfos.toString();
    }

    /**
     *
     * @return
     */
    @RequestMapping("/insertOrder")
    public void insertOrder(OrderInfo orderInfo){


        orderInfoService.insertOrder(orderInfo);
    }

}
