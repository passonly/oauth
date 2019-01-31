package com.oauth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.oauth.constant.Constants;
import com.oauth.entity.MessageData;
import com.oauth.entity.MessageFont;
import com.oauth.entity.MessageTemplate;
import com.oauth.entity.User;
import com.oauth.service.UserService;
import com.oauth.utils.HttpClientUtil;
import com.oauth.utils.HttpUtil;

import com.oauth.utils.WXUtil;
import com.oauth.vo.R;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sendMessage")
public class MessageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/sendTemplateMessage")
    public R sendTemplateMessage(HttpServletRequest request, HttpServletResponse response, String openid) throws Exception {


        String access_token = WXUtil.getAccessToken(request, response);
        // 获取网页授权access_token
//        String access_token = "17_xykZ-o18LKmHkAzhXeMheJ5hr448u16eX0VuRaiSLSCLRgWafmRmqoI8GouY_N7iSeDa7QqtqYvHGOQPpV0jbmjLsG8xfrGfCKe-ogD5N--4WUYSeT9KkWL5AkqrWUzmra-8VPP8n8UkniesLGXaAHACXO";
        // 设置要传递的参数messageTemplate
        MessageTemplate messageTemplate = new MessageTemplate();
//        messageTemplate.setTouser("olyFc1CdGLBWWkhkfZoevCnWM1Hc");
        messageTemplate.setTouser(openid);
        messageTemplate.setTemplate_id(Constants.TEMPLATEID);
        messageTemplate.setUrl("http://" + Constants.URL + "/check.html");
        MessageData messageData = new MessageData();
        MessageFont first = new MessageFont();
        first.setValue("汇尚合会员卡号填写");
        first.setColor("#173177");
        MessageFont keyword1 = new MessageFont();
        User user = userService.selectByPrimaryKey(openid);
        keyword1.setValue(user.getUserPhone());
        keyword1.setColor("#173177");
        MessageFont keyword2 = new MessageFont();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        keyword2.setValue(df.format(new Date()).toString());
        keyword2.setColor("#173177");
        MessageFont remark = new MessageFont();
        remark.setValue("请您点击详情填写会员卡信息");
        remark.setColor("#173177");
        messageData.setFirst(first);
        messageData.setKeyword1(keyword1);
        messageData.setKeyword2(keyword2);
        messageData.setRemark(remark);
        messageTemplate.setData(messageData);
        JSONObject jsonObject = JSONObject.fromObject(messageTemplate);
        //参数url

        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        String jsonTW = HttpClientUtil.httpPostWithJSON(url, jsonObject.toString());
        JSONObject jsonObject1 = JSONObject.fromObject(jsonTW);
        Object errcode = jsonObject1.get("errcode");
        if (errcode != null && "0".equals(errcode.toString())) {
            return R.ok();
        } else {
            return R.error("发送公众号通知信息失败！");
        }
    }


//    @RequestMapping("/setindustry")
//    public void setindustry(HttpServletRequest request, HttpServletResponse response,String access_token) throws Exception {
//        // 获取网页授权access_token
////        String access_token = "17_xykZ-o18LKmHkAzhXeMheJ5hr448u16eX0VuRaiSLSCLRgWafmRmqoI8GouY_N7iSeDa7QqtqYvHGOQPpV0jbmjLsG8xfrGfCKe-ogD5N--4WUYSeT9KkWL5AkqrWUzmra-8VPP8n8UkniesLGXaAHACXO";
//        // 设置要传递的参数
//
//        Map<String, Object> pamas = new HashMap<String, Object>();
//        pamas.put("touser", "olyFc1CdGLBWWkhkfZoevCnWM1Hc"); //素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
//        pamas.put("template_id", "Wu550N9kIrl6K_OLrd4NUiGRZzgFxeoSBBYeKmIyQIs");
//        pamas.put("url", "http://jesus.ngrok.xiaomiqiu.cn/aaa.jpg");
//        Map<String, Object> data = new HashMap<String, Object>();
//        Map<String, Object> first = new HashMap<String, Object>();
//        first.put("value","汇尚合会员卡号填写");
//        first.put("color","#173177");
//        Map<String, Object> keyword1 = new HashMap<String, Object>();
//        keyword1.put("value","姓名");
//        keyword1.put("color","#173177");
//        Map<String, Object> keyword2 = new HashMap<String, Object>();
//        keyword2.put("value","时间");
//        keyword2.put("color","#173177");
//        Map<String, Object> remark = new HashMap<String, Object>();
//        remark.put("value","请点击详情填写");
//        remark.put("color","#173177");
//        data.put("first",first);
//        data.put("keyword1",keyword1);
//        data.put("keyword2",keyword2);
//        data.put("remark",remark);
//        pamas.put("data", data);
//
//        String pamasJson = JSON.toJSONString(pamas, SerializerFeature.WriteMapNullValue);
////        String param = "{\n" +
////                "          \"industry_id1\":\"31\",\n" +
////                "          \"industry_id2\":\"41\"\n" +
////                "       }";
//        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
//        url.replace("ACCESS_TOKEN",access_token);
//        String jsonTW = HttpClientUtil.httpPostWithJSON(url, pamasJson);
//        System.out.println(jsonTW);
////        response.sendRedirect(url);
//
//    }

    @RequestMapping("/sendTemplate")
    public void getAccessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取网页授权access_token
        String access_token = "17_RbAT99fLbKp82dgLDtY-uCy3TUFzajnKcLzeSWDOWQeA8SiJHeQlipkBdlIi4_9uiLzCDzaoV7JVKKn2pCKCuRQbHfxWvLHhmGhB-66lsfeYPpw4bOCPdSZG7Ir8VhH_7eeXlUea9tucZNLmQEMaAIAGUC";
        // 设置要传递的参数
        String param = "{\n" +
                "   \"touser\":[\n" +
                "    \"OPENID1\",\n" +
                "    \"OPENID2\"\n" +
                "   ],\n" +
                "    \"msgtype\": \"text\",\n" +
                "    \"text\": { \"content\": \"hello from boxer.\"}\n" +
                "}";
        //具体业务start

        //具体业务end

        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
        String s = HttpUtil.doPost(url, param);
        System.out.println(s);
        response.sendRedirect(url);
    }
}
