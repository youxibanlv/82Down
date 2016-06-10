package com.a82down.app.http.response;

import com.a82down.app.http.entity.WheelPage;
import com.a82down.app.http.BaseResponse;

import java.util.List;

/**
 * Created by strike on 16/6/8.
 */
public class WheelPageRsp extends BaseResponse {

    public ResultData resultData = null;

    public List<WheelPage> getWheelPages(){
        return  resultData.wheelPages;
    }
    class ResultData {
        List<WheelPage> wheelPages;
    }
}
