package com.oauth.test;

import com.oauth.entity.*;
import com.oauth.utils.HttpUtil;
import net.sf.json.JSONObject;

public class TestButton {
    public static void main(String[] args) {
        Button button = new Button();
        button.getButton().add(new ClickButton("菜单一","1"));
        button.getButton().add(new ViewButton("菜单二","http://www.baidu.com"));
        SubButton subButton = new SubButton("有子菜单");
        subButton.getSubButton().add(new ViewButton("网易新闻","http://news.163.com"));
        subButton.getSubButton().add(new ClickButton("点击","32"));
        JSONObject jsonObject = JSONObject.fromObject(button);
        System.out.println(jsonObject.toString());
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        String accesstoken = "";
        url = url.replace("ACCESS_TOKEN",accesstoken);
        String data = "";
        String s = HttpUtil.doPost(url, data);
        System.out.println(s);
    }
}
