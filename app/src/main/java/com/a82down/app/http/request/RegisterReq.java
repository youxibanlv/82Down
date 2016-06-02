package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

import org.xutils.common.util.MD5;

/**
 * Created by xiaowuyue on 16/6/1.
 */
public class RegisterReq extends BaseRequest {

    RequestParams requestParams;

    public RegisterReq(String name,String password){
        cmdType = "userService";
        methodName = "register";
        requestParams = new RequestParams(name,password);
    }

    class RequestParams{
        String userName;
        String pass;
        public RequestParams(String name,String password){
            userName = name;
            pass = MD5.md5(password);
        }
    }
}
