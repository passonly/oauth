package com.oauth.test;

import com.oauth.entity.*;
import com.oauth.utils.HttpUtil;
import net.sf.json.JSONObject;

public class TestButton {
//    public static void main(String[] args) {
//        Button button = new Button();
//        button.getButton().add(new ClickButton("菜单一","1"));
//        button.getButton().add(new ViewButton("菜单二","http://www.baidu.com"));
//        SubButton subButton = new SubButton("有子菜单");
//        subButton.getSubButton().add(new ViewButton("网易新闻","http://news.163.com"));
//        subButton.getSubButton().add(new ClickButton("点击","32"));
//        JSONObject jsonObject = JSONObject.fromObject(button);
//        System.out.println(jsonObject.toString());
//        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
//        String accesstoken = "";
//        url = url.replace("ACCESS_TOKEN",accesstoken);
//        String data = "";
//        String s = HttpUtil.doPost(url, data);
//        System.out.println(s);
//    }

    public static void main(String[] args) {
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setTouser("olyFc1CdGLBWWkhkfZoevCnWM1Hc");
        messageTemplate.setTemplate_id("Wu550N9kIrl6K_OLrd4NUiGRZzgFxeoSBBYeKmIyQIs");
        messageTemplate.setUrl("http://jesus.ngrok.xiaomiqiu.cn/aaa.jpg");
        MessageData messageData = new MessageData();
        MessageFont first = new MessageFont();
        first.setValue("汇尚合会员卡号填写");
        first.setColor("#ffff");
        MessageFont keyword1 = new MessageFont();
        keyword1.setValue("keyword1");
        keyword1.setColor("#173177");
        MessageFont keyword2 = new MessageFont();
        keyword2.setValue("keyword2");
        keyword2.setColor("#173177");
        MessageFont remark = new MessageFont();
        remark.setValue("remark");
        remark.setColor("#173177");
        messageData.setFirst(first);
        messageData.setKeyword1(keyword1);
        messageData.setKeyword2(keyword2);
        messageData.setRemark(remark);
        messageTemplate.setData(messageData);
        JSONObject jsonObject = JSONObject.fromObject(messageTemplate);

        System.out.println(jsonObject);

    }
}
