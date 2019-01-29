package com.oauth.controller;

import com.oauth.constant.Constants;
import com.oauth.entity.OrderInfo;
import com.oauth.entity.User;
import com.oauth.service.OrderInfoService;
import com.oauth.utils.WXUtil;
import com.oauth.vo.R;

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
    public R checkOrder(HttpServletRequest request, HttpServletResponse response, OrderInfo orderInfo) {

//        String userphone = WXUtil.getCookie(request, response, "userphone");
        String userOpenid = WXUtil.getCookie(request, response, "user_openid");
        if (userOpenid == null || "".equals(userOpenid)){
            try {
                response.sendRedirect("/authorize");
                return R.error("请进行微信登录验证");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<OrderInfo> orderInfos = null;
        if (orderInfo.getOrderNumber() != null
                && !"".equals(orderInfo.getOrderNumber())
                && orderInfo.getOrderSercet() != null
                && !"".equals(orderInfo.getOrderSercet())) {
            orderInfos = orderInfoService.selectByEntity(0, 10, orderInfo);
        }
        if (orderInfos.size() > 1) {
            try {
//                throw new Exception("订单号不唯一");
                return R.error("订单号不唯一");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (orderInfos == null || orderInfos.size() == 0) {
            try {
                return R.error("订单号不存在");
//                throw new Exception("订单号不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        orderInfo = orderInfos.get(0);
        orderInfo.setOrderCanSend("1");
        orderInfo.setUserOpenid(userOpenid);
        orderInfoService.update(orderInfo);
        return R.ok();
    }

    /**
     *
     * @return
     */
    @RequestMapping("/queryorder")
    public void queryuser(HttpServletRequest request, HttpServletResponse response) {

        String userphone = WXUtil.getCookie(request, response, "user_openid");
        try {
            if (userphone != null && !"".equals(userphone)) {
//                response.sendRedirect("http://" + Constants.URL + "/queryorder.html");
                response.sendRedirect("/orderlist.html");
            } else {
//                response.sendRedirect("http://" + Constants.URL + "/login.html");
                response.sendRedirect("/masterlogin.html");
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
    public List<OrderInfo> getList(int currentPage, int pageSize, OrderInfo orderInfo) {
//        orderInfo.setCreatePerson("2");
        List<OrderInfo> orderInfos = orderInfoService.selectByEntity(currentPage, pageSize, orderInfo);
        return orderInfos;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/insert")
    public void insertOrder(OrderInfo orderInfo) {
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
    public OrderInfo selectByPrimaryKey(String id) {

        return orderInfoService.selectByPrimaryKey(id);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/update")
    public void upate(OrderInfo orderInfo) {
        orderInfoService.update(orderInfo);
    }

}
