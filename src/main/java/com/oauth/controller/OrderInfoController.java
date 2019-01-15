package com.oauth.controller;

import com.oauth.constant.Constants;
import com.oauth.entity.OrderInfo;
import com.oauth.entity.User;
import com.oauth.service.OrderInfoService;
import com.oauth.utils.WXUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping("/checkOrder")
    public void checkOrder(HttpServletRequest request, HttpServletResponse response,OrderInfo orderInfo){

        String userphone = WXUtil.getCookie(request, response, "userphone");
        List<OrderInfo> orderInfos = orderInfoService.selectByEntity(0, 10, orderInfo);
        if (orderInfos.size() > 1){
            try {
                throw new Exception("订单号不唯一");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (orderInfos.size() == 0){
            try {
                throw new Exception("订单号不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        orderInfo = orderInfos.get(0);
        orderInfo.setOrderCanSend("1");
        orderInfoService.update(orderInfo);

    }

    /**
     *
     * @return
     */
    @RequestMapping("/queryorder")
    public void queryuser(HttpServletRequest request, HttpServletResponse response){

        String userphone = WXUtil.getCookie(request, response, "userphone");
        try {
            if ( userphone != null && !"".equals(userphone)) {
                response.sendRedirect("http://" + Constants.URL + "/queryorder.html");
            } else {
                response.sendRedirect("http://" + Constants.URL + "/login.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("/getList")
    public List<OrderInfo> getList(int currentPage,int pageSize,OrderInfo orderInfo){
//        orderInfo.setCreatePerson("2");
        List<OrderInfo> orderInfos = orderInfoService.selectByEntity(currentPage, pageSize,orderInfo);
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
