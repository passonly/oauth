package com.oauth.dao;


import com.oauth.entity.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/8 12:26.
 */
@Mapper
public interface UserMapper {

//    @Select("select * from orderinfo")
    List<User> selectByEntity();

    void updateByPrimaryKey(User user);

    int count();

    User selectByPrimaryKey(String id);

    void insert(User user);
}
