package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

/**
 * Created by strike on 16/6/29.
 */
public class AppDetailsReq extends BaseRequest {
    RequestParam requestParams;

    public AppDetailsReq(String app_id){
        cmdType = "appService";
        methodName = "getDetailsById";
        requestParams = new RequestParam(app_id);
    }

    class RequestParam{
        String app_id;
        public RequestParam(String appId){
            this.app_id = appId;
        }
    }
}
