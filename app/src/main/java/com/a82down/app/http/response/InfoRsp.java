package com.a82down.app.http.response;

import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.entity.Info;

import java.util.List;

/**
 * Created by strike on 16/8/4.
 */
public class InfoRsp extends BaseResponse {
    public ResultData resultData = null;

    public List<Info> getInfoLists() {
        return resultData.infoList;
    }

    public int getTotalPage() {
        return resultData.totalPage;
    }

    class ResultData {
        List<Info> infoList = null;
        int totalPage;
    }
}
