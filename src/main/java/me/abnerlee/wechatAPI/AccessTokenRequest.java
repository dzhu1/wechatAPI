package me.abnerlee.wechatAPI;

import me.abnerlee.utils.client.HttpClientUtils;
import me.abnerlee.utils.json.JsonConvert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 14-11-23.
 */
public class AccessTokenRequest {
    public static String getAccessToken(){
        return getAccessToken(Constant.ACCESS_TOKEN_URL);
    }

    public static String getAccessToken(String url){
        return getAccessToken(url, Constant.APPID, Constant.APPSECRET);
    }

    public static String getAccessToken(String url, String appid, String secret){
        return getAccessToken(url, "client_credential", appid, secret);
    }

    public static String getAccessToken(String url, String grantType, String appid, String secret){
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", grantType);
        params.put("appid", appid);
        params.put("secret", secret);

        Map<String, String> body = JsonConvert.jsonToMap(HttpClientUtils.request(url, params, null));
        if (body.get("errmsg") != null) {
            System.out.println(body.get("errmsg"));
            return null;
        } else {
            return body.get("access_token");
        }
    }

}