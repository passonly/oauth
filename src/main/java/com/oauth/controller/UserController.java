package com.oauth.controller;

import com.oauth.entity.User;
import com.oauth.service.UserService;
import com.oauth.utils.WXUtil;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @RequestMapping("/masterLogin")
    public void masterLogin(HttpServletRequest request, HttpServletResponse response, User user) {
        List<User> users = null;
        try {
            if (user != null
                    && user.getUserPhone() != null
                    && !"".equals(user.getUserPhone())
                    && user.getUserPassword() != null
                    && !"".equals(user.getUserPassword())) {
                users = userService.selectByEntity(user);
            }
            if (users != null && users.size() != 0) {
                Cookie cookie = new Cookie("user_openid", users.get(0).getUserPhone());
//            cookie.setDomain("localhost");
                cookie.setPath("/");
                response.addCookie(cookie);
                response.sendRedirect("/orderlist.html");
            } else {
                response.sendRedirect("/masterLogin.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("/queryuser")
    public void queryuser(HttpServletRequest request, HttpServletResponse response) {

        String userphone = WXUtil.getCookie(request, response, "user_openid");
        try {
            if (userphone != null && !"".equals(userphone)) {
//                response.sendRedirect("http://" + Constants.URL + "/queryuser.html");
                response.sendRedirect("/userlist.html");
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
    public List<User> getList(int currentPage, int pageSize, User user) {
//        user.setUserName("ddd");
//        user.setUserOpenid("2");
        List<User> users = userService.selectByEntity(currentPage, pageSize, user);
        return users;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/insert")
    public void insertOrder(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.insert(user);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/selectById")
    public User selectByPrimaryKey(String id) {

        return userService.selectByPrimaryKey(id);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/update")
    public void upate(User user) {
        userService.update(user);
    }
}
