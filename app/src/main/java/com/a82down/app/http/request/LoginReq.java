package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

import org.xutils.common.util.MD5;

/**
 * Created by strike on 16/6/3.
 */
public class LoginReq extends BaseRequest {
    RequestParam requestParams;

    public LoginReq(String name,String password){
        cmdType = "userService";
        methodName = "login";
        requestParams = new RequestParam(name,password);
    }

    class RequestParam{
        String username;
        String password;
        public RequestParam(String name,String pass){
            username = name;
            password = MD5.md5(pass);
        }
    }
}
