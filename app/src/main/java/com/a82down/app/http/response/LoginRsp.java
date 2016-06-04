package com.a82down.app.http.response;

import com.a82down.app.http.BaseResponse;

/**
 * Created by strike on 16/6/3.
 */
public class LoginRsp extends BaseResponse {
    public ResultData resultData = null;
    class ResultData {
        int uid;
        String username;
        String password;
        String token;
        int phone;
        String nickname;
        String icon;
        String alipay;
        int point;
    }
}
