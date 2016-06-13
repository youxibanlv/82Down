package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

/**
 * Created by strike on 16/6/13.
 */
public class GetAppByKeywordReq extends BaseRequest {


    RequestParam requestParams;

    public GetAppByKeywordReq(String key) {
        cmdType = "appService";
        methodName = "getAppByKeyword";
        requestParams = new RequestParam(key);
    }

    class RequestParam {
        String keyword;

        public RequestParam(String key) {
            keyword = key;
        }
    }
}
