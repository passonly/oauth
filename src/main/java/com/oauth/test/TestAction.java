package com.oauth.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.oauth.constant.Constants;
import com.oauth.utils.HttpClientUtil;
import com.oauth.utils.WXUtil;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by LY on 2017/3/28.
 */
public class TestAction {

    public static void main(String[] args){

        try {
            // 获取图文素材列表信息
            String accessToken = WXUtil.getAccessToken();
            Map<String, Object> pamas = new HashMap<String, Object>();
            pamas.put("type", "image"); //素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
            pamas.put("offset", "0");
            pamas.put("count", "20");
            String pamasJson = JSON.toJSONString(pamas, SerializerFeature.WriteMapNullValue);
            // 图文素材
            String urlTW = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+accessToken;
            // 获取菜单
            String urlmenu = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token="+accessToken;
            String jsonTW = HttpClientUtil.httpPostWithJSON(urlTW, pamasJson);

            String jsonMenu = HttpClientUtil.doGetRequest(urlmenu);

            System.out.print(accessToken + "\n\n");

            System.out.print(jsonTW + "\n\n");

            System.out.print(jsonMenu + "\n\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//    private static String accessToken() throws Exception {
//        try {
//            String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?" + "grant_type=client_credential"
//                    + "&appid=" + Constants.APPID
//                    + "&secret=" + Constants.APPSERECT;
//            String accessTokenInfo = HttpClientUtil.doGetRequest(tokenUrl);
//            System.out.print(accessTokenInfo + "\n\n");
//            if (accessTokenInfo != null && !"".equals(accessTokenInfo)) {
//                JSONObject j = JSON.parseObject(accessTokenInfo);
//                int expires_in = j.getIntValue("expires_in");
//                if(expires_in == 7200){
//                    return j.getString("access_token");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        throw new Exception("获取accessToken异常!");
//    }

}
