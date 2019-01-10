package com.oauth.service.impl;

import com.github.pagehelper.PageHelper;
import com.oauth.dao.UserMapper;
import com.oauth.entity.User;
import com.oauth.entity.PageBean;
import com.oauth.service.UserService;

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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper UserMapper;

    public List<User> selectByEntity(int currentPage, int pageSize) {

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        List<User> allItems = UserMapper.selectByEntity();        //全部商品
        int countNums = UserMapper.count();            //总记录数
        PageBean<User> pageData = new PageBean(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData.getItems();


//        return UserMapper.selectByEntity();
    }

    public void insert(User user) {
        UserMapper.insert(user);
    }

    public void update(User user) {
        UserMapper.updateByPrimaryKey(user);
    }

    public User selectByPrimaryKey(String id) {
        return UserMapper.selectByPrimaryKey(id);
    }
}
