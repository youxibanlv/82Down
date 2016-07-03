package com.a82down.app.http.response;

import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.entity.Category;

import java.util.List;

/**
 * Created by strike on 16/7/2.
 */
public class GetCategoryRsp extends BaseResponse {
    public ResultData resultData = null;
    public List<Category> getResultList(){
        return resultData.list;
    }

    class ResultData {
        List<Category> list;
    }
}
