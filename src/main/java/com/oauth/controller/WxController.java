package com.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.oauth.constant.Constants;
import com.oauth.entity.User;
import com.oauth.service.OrderInfoService;
import com.oauth.service.UserService;
import com.oauth.utils.NetUtil;
import com.oauth.utils.Oauth2Token;
import com.oauth.utils.SNSUserInfo;
import com.oauth.utils.WXUtil;
import com.oauth.vo.R;


/**
 * @Author: likun.
 * @Description:
 * @Date:Created in 2019/1/4 10:27.
 */
@RestController
public class WxController {

    private static Logger log = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private UserService userService;

//    @RequestMapping("/")
//    public void root(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        response.sendRedirect("http://"+Constants.URL+"/MP_verify_IQ071dRr6uE19t50.txt\"");
//    }

    @RequestMapping("/")
    public void root(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.sendRedirect("http://" + Constants.URL + "/orderlist.html");
    }

    @RequestMapping("/signout")
    public void signout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Cookie cookie = new Cookie("user_openid", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/masterlogin.html");
    }


    @RequestMapping("/getAccessToken")
    public String getAccessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String access_token = WXUtil.getAccessToken(request, response);
        return access_token;
    }

    @RequestMapping("/hello")
    public String home(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("========WechatController========= ");
        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            // out.print(name + "=" + value);
            String log = "name =" + name + "     value =" + value;
        }
        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        PrintWriter out = response.getWriter();
        out.print(echostr);
        out.close();
        return "Hello World!";
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param ，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     *
     * 用户同意授权，获取code
     */
    @RequestMapping("/authorize")
    @ResponseBody
    public void authorize(HttpServletRequest request, HttpServletResponse response) {
        log.info("跳转到用户填写登录信息页面");
        String appid = Constants.APPID;
        String uri = urlEncodeUTF8("http://" + Constants.URL + "/login.html");
        //如果cookie中有用户数据，直接跳转到注册成功页面
        String url = "http://" + Constants.URL + "/auth.html";
        String urlNameString = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + uri + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

        boolean flag = false;
        Cookie[] cookies = request.getCookies();
        //cookie为空，则去注册
        if (cookies == null) {
            log.info("cookie为空");
            try {
                response.sendRedirect(urlNameString);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //查找cookie中是否有用户信息
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cook = cookies[i];
                if (cook.getName().equalsIgnoreCase("user_openid")) { //获取键
                    log.info("user_openid:" + cook.getValue().toString());    //获取值
                    flag = true;
                }
            }
        }
        //如果没有用户信息，则去注册，有则跳转到注册成功页面
        if (flag) {
            log.info("cookie中有用户登录信息");
            try {
                response.sendRedirect(url);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            response.sendRedirect(urlNameString);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/weixinLogin")
    @ResponseBody
    public R weixinLogin(HttpServletRequest request, HttpServletResponse response, String code, String userPhone, String userName) throws Exception {

        log.info("****************code:" + code);
        // 用户同意授权
        if (code != null && !"".equals(code)) {
            // 获取网页授权access_token
            Oauth2Token oauth2Token = getOauth2AccessToken(Constants.APPID, Constants.APPSERECT, code);
            log.info("***********************************oauth2Token信息：" + oauth2Token.toString());
            // 网页授权接口访问凭证
            String accessToken = oauth2Token.getAccessToken();
            // 用户标识
            String openId = oauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = getSNSUserInfo(accessToken, openId);
            log.info("openid:" + openId);
            log.info("accessToken:" + accessToken);
            log.info("***********************************用户信息unionId：" + snsUserInfo.getUnionid() + "***:" + snsUserInfo.getNickname());

            //根据openid查询用户
            User user = null;
            user = userService.selectByPrimaryKey(openId);

            //保存用户到数据库
            if (user == null) {
                user = new User();
                user.setUserId(UUID.randomUUID().toString());
                user.setUserOpenid(openId);
                user.setUserName(userName);
                user.setUserNickName(snsUserInfo.getNickname());
                user.setCreateTime(new Date());
                user.setUserCountry(snsUserInfo.getCountry());
                user.setUserProvince(snsUserInfo.getProvince());
                user.setUserCity(snsUserInfo.getCity());
                user.setUserPassword("");
                user.setUserPhone(userPhone);
                user.setUserSex(snsUserInfo.getSex() + "");
                user.setUserStatus("0");
                user.setCreateTime(new Date());
                user.setCreatePerson(openId);
                user.setUpdateTime(new Date());
                user.setUpdatePerson(openId);
                userService.insert(user);
                log.info("用户信息保存到数据库成功");
            }
            //保存用户信息openid到cookie
            Cookie cookie = new Cookie("user_openid", openId);
            cookie.setPath("/");
            response.addCookie(cookie);
            log.info("cookie保存成功");
        }
        return R.ok();
    }

    /**
     * 获取网页授权凭证
     *
     * @param appId     公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @return WeixinAouth2Token
     */
    public static Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        Oauth2Token wat = null;
//        appSecret = Constants.APPSERECT;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(NetUtil.get(requestUrl));
        if (null != jsonObject) {
            try {
                wat = new Oauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInteger("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }


    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId      用户标识
     * @return SNSUserInfo
     */
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(NetUtil.get(requestUrl));

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInteger("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                List<String> list = JSON.parseArray(jsonObject.getString("privilege"), String.class);
                snsUserInfo.setPrivilegeList(list);
                //与开放平台共用的唯一标识，只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
                snsUserInfo.setUnionid(jsonObject.getString("unionid"));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }

    /**
     * URL编码（utf-8）
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
