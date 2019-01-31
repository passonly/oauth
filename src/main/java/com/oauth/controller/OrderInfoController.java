package com.oauth.controller;

import com.oauth.constant.Constants;
import com.oauth.entity.OrderInfo;
import com.oauth.entity.TableSplitResult;
import com.oauth.entity.User;
import com.oauth.service.OrderInfoService;
import com.oauth.utils.WXUtil;
import com.oauth.vo.R;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
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

    private static Logger log = LoggerFactory.getLogger(OrderInfoController.class);

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     *
     * @return
     */
    @RequestMapping("/check")
    public void checkOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
//            log.info(cookies.toString());
            request.getRequestDispatcher("/check.html").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("/checkOrder")
    public R checkOrder(HttpServletRequest request, HttpServletResponse response, OrderInfo orderInfo) {

        String userOpenid = WXUtil.getCookie(request, response, "user_openid");

        log.info("user_openid:" + userOpenid);
        if (userOpenid == null || "".equals(userOpenid)) {
            return R.error(40010, "请进行微信登录验证");
        }

        List<OrderInfo> orderInfos = null;
        if (orderInfo.getOrderNumber() != null
                && !"".equals(orderInfo.getOrderNumber())
                && orderInfo.getOrderSercet() != null
                && !"".equals(orderInfo.getOrderSercet())) {
            System.out.println(DigestUtils.md5Hex(orderInfo.getOrderSercet() + Constants.MD5STR));
            orderInfo.setOrderSercet(DigestUtils.md5Hex(orderInfo.getOrderSercet() + Constants.MD5STR));
            orderInfos = orderInfoService.selectByOrderNumber(orderInfo);

        }
        if (orderInfos != null && orderInfos.size() > 1) {
            try {
                return R.error(40011,"订单号不唯一");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (orderInfos == null || orderInfos.size() == 0) {
            try {
                return R.error(40012,"订单号不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        orderInfo = orderInfos.get(0);
        orderInfo.setOrderCanSend("1");
        orderInfo.setUserOpenid(userOpenid);
        orderInfo.setUpdateTime(new Date());
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
    public TableSplitResult<List<OrderInfo>> getList(int pageNumber, int pageSize, OrderInfo orderInfo) {
//        orderInfo.setCreatePerson("2");
        TableSplitResult<List<OrderInfo>> orderInfos = orderInfoService.selectByEntity(pageNumber, pageSize, orderInfo);
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
