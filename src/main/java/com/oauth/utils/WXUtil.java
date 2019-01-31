package com.oauth.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oauth.constant.Constants;
import com.oauth.controller.WxController;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/9 9:53.
 */
public class WXUtil {
    private static Logger log = LoggerFactory.getLogger(WXUtil.class);

    public static String getAccessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //如果cookie中有，则从cookie中获取
//        String accessToken = getAccessTokenFromCookie(request, response);

        //如果cookie中没有，则重新获取access_token，并存入cookie
//        if (accessToken == null || "".equals(accessToken)) {
//            accessToken = WXUtil.getAccessTokenfromHttp();
//            Cookie cookie = new Cookie("access_token", accessToken);
//            cookie.setMaxAge(7200);
//            response.addCookie(cookie);
//        }
        return WXUtil.getAccessTokenfromHttp();
    }

    public static String getAccessTokenfromHttp() throws Exception {

        //从cookie中获取access_token
        try {
            String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?" + "grant_type=client_credential"
                    + "&appid=" + Constants.APPID
                    + "&secret=" + Constants.APPSERECT;
            log.info("获取token的Url："+tokenUrl);
            String accessTokenInfo = HttpClientUtil.doGetRequest(tokenUrl);
            log.info(accessTokenInfo + "获取的accessTokenInfo");
            if (accessTokenInfo != null && !"".equals(accessTokenInfo)) {
                JSONObject j = JSON.parseObject(accessTokenInfo);
                int expires_in = j.getIntValue("expires_in");
                if (expires_in == 7200) {
                    return j.getString("access_token");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("获取accessToken异常!");
    }

    public static String getAccessTokenFromCookie(HttpServletRequest request, HttpServletResponse response) {
        // 获取request里面的cookie cookie里面存值方式也是 键值对的方式
        String access_token = null;
        Cookie[] cookie = request.getCookies();
        if (cookie == null) return null;
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equalsIgnoreCase("access_token")) { //获取键
                System.out.println("access_token:" + cook.getValue().toString());    //获取值
                access_token = cook.getValue().toString();
            }
        }
        return access_token;
    }

    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        // 获取request里面的cookie cookie里面存值方式也是 键值对的方式
        String cookieValue = null;
        Cookie[] cookie = request.getCookies();
        if (cookie == null) return null;
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equalsIgnoreCase(cookieName)) { //获取键
                cookieValue = cook.getValue().toString();
            }
        }
        return cookieValue;
    }
}
