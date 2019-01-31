package com.oauth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/8 12:20.
 */
public class OrderInfo {

    private String orderId;
    private String orderNumber;
    private String orderSercet;
    private String orderStatus;
    private String orderCanSend;//待发货
    private String orderIsSend; //已发货
    private String orderCustomer;
    private Date   createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createPerson;
    private String userOpenid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date   updateTime;
    private String updatePerson;
    private String userName;
    private String userPhone;
    private String orderPerson;
    private String orderPersonPhone;
    private String orderPersonAddress;
    private String remark;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderSercet() {
        return orderSercet;
    }

    public void setOrderSercet(String orderSercet) {
        this.orderSercet = orderSercet;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCanSend() {
        return orderCanSend;
    }

    public void setOrderCanSend(String orderCanSend) {
        this.orderCanSend = orderCanSend;
    }

    public String getOrderIsSend() {
        return orderIsSend;
    }

    public void setOrderIsSend(String orderIsSend) {
        this.orderIsSend = orderIsSend;
    }

    public String getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(String orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    public String getOrderPerson() {
        return orderPerson;
    }

    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public String getOrderPersonPhone() {
        return orderPersonPhone;
    }

    public void setOrderPersonPhone(String orderPersonPhone) {
        this.orderPersonPhone = orderPersonPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderPersonAddress() {
        return orderPersonAddress;
    }

    public void setOrderPersonAddress(String orderPersonAddress) {
        this.orderPersonAddress = orderPersonAddress;
    }
}
