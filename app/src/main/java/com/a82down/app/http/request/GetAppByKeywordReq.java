package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

/**
 * Created by strike on 16/6/13.
 */
public class GetAppByKeywordReq extends BaseRequest {


    RequestParam requestParams;

    public GetAppByKeywordReq(String key,int pageNo,int pageSize) {
        cmdType = "appService";
        methodName = "getAppByKeyword";
        requestParams = new RequestParam(key,pageNo,pageSize);
    }

    class RequestParam {
        String keyword;
        int pageNo;
        int pageSize;

        public RequestParam(String key,int pageNo,int pageSize) {
            keyword = key;
            this.pageNo = pageNo;
            this.pageSize = pageSize;
        }
    }
}
