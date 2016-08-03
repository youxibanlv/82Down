package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

/**
 * Created by strike on 16/8/3.
 */
public class GetAppsByIdListReq extends BaseRequest {

    RequestParam requestParams;

    public GetAppsByIdListReq(String idList){
        cmdType = "appService";
        methodName = "getAppsByIdList";
        requestParams = new RequestParam(idList);
    }

    class RequestParam{
        String ids;
        public RequestParam(String idList){
            this.ids = idList;
        }
    }
}
