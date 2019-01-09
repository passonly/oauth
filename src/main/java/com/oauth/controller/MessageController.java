package com.oauth.controller;

import com.oauth.constant.Constants;
import com.oauth.utils.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sendMessage")
public class MessageController {

    @RequestMapping("/setindustry")
    public void setindustry(HttpServletRequest request, HttpServletResponse response,String access_token) throws Exception {
        // 获取网页授权access_token
//        String access_token = "17_xykZ-o18LKmHkAzhXeMheJ5hr448u16eX0VuRaiSLSCLRgWafmRmqoI8GouY_N7iSeDa7QqtqYvHGOQPpV0jbmjLsG8xfrGfCKe-ogD5N--4WUYSeT9KkWL5AkqrWUzmra-8VPP8n8UkniesLGXaAHACXO";
        // 设置要传递的参数
        String param = "{\n" +
                "          \"industry_id1\":\"31\",\n" +
                "          \"industry_id2\":\"41\"\n" +
                "       }";
        //具体业务start

        //具体业务end

        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
        url.replace("ACCESS_TOKEN",access_token);
        String s = HttpUtil.doPost(url, param);
        System.out.println(s);
        response.sendRedirect(url);

    }

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
