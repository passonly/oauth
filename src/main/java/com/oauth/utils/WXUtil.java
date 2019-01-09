package com.oauth.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oauth.constant.Constants;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/9 9:53.
 */
public class WXUtil {

    public static String getAccessToken() throws Exception {
        try {
            String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?" + "grant_type=client_credential"
                    + "&appid=" + Constants.APPID
                    + "&secret=" + Constants.APPSERECT;
            String accessTokenInfo = HttpClientUtil.doGetRequest(tokenUrl);
            System.out.print(accessTokenInfo + "\n\n");
            if (accessTokenInfo != null && !"".equals(accessTokenInfo)) {
                JSONObject j = JSON.parseObject(accessTokenInfo);
                int expires_in = j.getIntValue("expires_in");
                if(expires_in == 7200){
                    return j.getString("access_token");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("获取accessToken异常!");
    }

    public static String getAccessTokenFromCookie(HttpServletRequest request, HttpServletResponse response){
        // 获取request里面的cookie cookie里面存值方式也是 键值对的方式
        String access_token = null;
        Cookie[] cookie = request.getCookies();
        if (cookie == null) return null;
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if(cook.getName().equalsIgnoreCase("access_token")){ //获取键
                System.out.println("account:"+cook.getValue().toString());    //获取值
                access_token = cook.getValue().toString();
            }
        }
        return access_token;
    }

}
