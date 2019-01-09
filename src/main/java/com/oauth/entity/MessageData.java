package com.oauth.entity;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/9 19:48.
 */
public class MessageData {
    private MessageFont first;
    private MessageFont keyword1;
    private MessageFont keyword2;
    private MessageFont remark;

    public MessageFont getFirst() {
        return first;
    }

    public void setFirst(MessageFont first) {
        this.first = first;
    }

    public MessageFont getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(MessageFont keyword1) {
        this.keyword1 = keyword1;
    }

    public MessageFont getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(MessageFont keyword2) {
        this.keyword2 = keyword2;
    }

    public MessageFont getRemark() {
        return remark;
    }

    public void setRemark(MessageFont remark) {
        this.remark = remark;
    }
}
