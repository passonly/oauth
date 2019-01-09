package com.oauth.entity;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/9 19:47.
 */
public class MessageTemplate {

    private String touser;
    private String template_id;
    private String url;
    private MessageData data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MessageData getData() {
        return data;
    }

    public void setData(MessageData data) {
        this.data = data;
    }
}
