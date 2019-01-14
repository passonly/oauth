package com.oauth.controller;

import com.oauth.constant.Constants;
import com.oauth.entity.OrderInfo;
import com.oauth.entity.User;
import com.oauth.entity.User;
import com.oauth.service.UserService;
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
    public void masterLogin(HttpServletRequest request, HttpServletResponse response,User user){
        user.setUserName("ddd");
        user.setUserOpenid("2");
        user.setUserPassword("2");
        List<User> users = userService.selectByEntity(user);
        if (user != null){
            Cookie cookie = new Cookie("userphone", users.get(0).getUserPhone());
            response.addCookie(cookie);
        }
        try {
            response.sendRedirect("http://"+ Constants.URL+"/queryuser.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping("/queryuser")
    public void queryuser(HttpServletRequest request, HttpServletResponse response){

        String userphone = WXUtil.getCookie(request, response, "userphone");
        try {
            if ( userphone != null && !"".equals(userphone)) {
                response.sendRedirect("http://" + Constants.URL + "/queryuser.html");
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
    public List<User> getList(int currentPage,int pageSize,User user){
//        user.setUserName("ddd");
//        user.setUserOpenid("2");
        List<User> users = userService.selectByEntity(currentPage, pageSize,user);
        return users;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/insert")
    public void insertOrder(User user){
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
    public User selectByPrimaryKey(String id){

       return  userService.selectByPrimaryKey(id);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/update")
    public void upate(User user){
        userService.update(user);
    }
}
