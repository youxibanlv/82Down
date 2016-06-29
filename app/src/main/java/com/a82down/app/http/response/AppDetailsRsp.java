package com.a82down.app.http.response;

import com.a82down.app.db.table.App;
import com.a82down.app.http.BaseResponse;

/**
 * Created by strike on 16/6/29.
 */
public class AppDetailsRsp extends BaseResponse {
    public ResultData resultData = null;

    public App getApp(){
        return resultData.app;
    }
    class ResultData {
        App app = null;
    }
}
