package com.oauth.dao;


import com.oauth.entity.OrderInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/8 12:26.
 */
@Mapper
public interface OrderInfoMapper {

//    @Select("select * from orderinfo")
    List<OrderInfo> selectByEntity();

    void updateByPrimaryKey(OrderInfo orderInfo);

    int count();

    OrderInfo selectByPrimaryKey(String id);

    void insert(OrderInfo orderInfo);
}
