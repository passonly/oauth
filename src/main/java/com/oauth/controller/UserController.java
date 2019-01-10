package com.oauth.controller;

import com.oauth.entity.OrderInfo;
import com.oauth.entity.User;
import com.oauth.entity.User;
import com.oauth.service.UserService;
import com.oauth.service.UserService;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @RequestMapping("/getList")
    public List<User> getList(int currentPage,int pageSize){
        List<User> users = userService.selectByEntity(currentPage, pageSize);
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
