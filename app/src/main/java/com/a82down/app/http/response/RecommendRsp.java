package com.a82down.app.http.response;

import com.a82down.app.db.table.App;
import com.a82down.app.http.BaseResponse;

import java.util.List;

/**
 * Created by strike on 16/6/7.
 */
public class RecommendRsp extends BaseResponse {
    public ResultData resultData = null;

    class ResultData{
        List<App> appList = null;
    }
}
