package com.oauth.service;

import com.oauth.entity.User;

import java.util.List;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/8 12:32.
 */
public interface UserService {

    List<User> selectByEntity(int currentPage, int pageSize);

    void insert(User user);

    void update(User user);

    User selectByPrimaryKey(String id);
}
