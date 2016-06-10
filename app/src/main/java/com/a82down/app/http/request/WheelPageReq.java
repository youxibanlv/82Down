package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

/**
 * Created by strike on 16/6/8.
 */
public class WheelPageReq extends BaseRequest {

    public WheelPageReq(){
        cmdType = "appService";
        methodName = "getWheelPage";
    }
}
