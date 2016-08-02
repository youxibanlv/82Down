package com.a82down.app.http.response;

import com.a82down.app.db.table.App;
import com.a82down.app.http.BaseResponse;

import java.util.List;

/**
 * Created by strike on 16/6/13.
 */
public class GetAppListRsp extends BaseResponse {
    public ResultData resultData = null;

    public List<App> getAppList() {
        return resultData.appList;
    }

    public int getTotalPage() {
        return resultData.totalPage;
    }

    class ResultData {
        List<App> appList = null;
        int totalPage;
    }
}
