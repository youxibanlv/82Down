package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;


/**
 * Created by strike on 16/6/20.
 */
public class DownloadUrlReq extends BaseRequest {

    RequestParam requestParams;

    public DownloadUrlReq(String app_id,String version){
        cmdType = "appService";
        methodName = "getUrlById";
        requestParams = new RequestParam(app_id,version);
    }

    class RequestParam{
        String app_id;
        String app_version;
        public RequestParam(String name,String version){
            this.app_id = name;
            this.app_version = version;
        }
    }
}
