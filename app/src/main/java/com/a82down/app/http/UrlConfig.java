package com.a82down.app.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by strike on 16/5/31.
 */
public class UrlConfig {

    public static final Map<String,String> URL_MAP = new HashMap<>();

    /***
     * 服务器地址
     * **/
//    public static String BASE_URL = "http://192.168.98.108/82down/app/";//baidudu
//    public static String BASE_URL = "http://192.168.206.22/82down/app/";//公司
    public static String BASE_URL = "http://123.57.86.113/app/";//外网

    //注册登录相关
    public static String GET_VERIFY_CODE = BASE_URL+"userService.php";



    static {
        URL_MAP.put("userService",GET_VERIFY_CODE);
    }


    public static String getUrl(String cmdType){
        return URL_MAP.get(cmdType);
    }

}
