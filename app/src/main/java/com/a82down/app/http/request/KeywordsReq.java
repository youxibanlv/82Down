package com.a82down.app.http.request;

import com.a82down.app.http.BaseRequest;

/**
 * Created by strike on 16/6/12.
 */
public class KeywordsReq extends BaseRequest {

    RequestParam requestParams;

    public KeywordsReq(String key,int size){
        cmdType = "appService";
        methodName = "getKeywords";
        requestParams = new RequestParam(key,size);
    }

    class RequestParam{
        String key;
        int size;
        public RequestParam(String keyword,int keySize){
            key = keyword;
            size = keySize;
        }
    }
}
