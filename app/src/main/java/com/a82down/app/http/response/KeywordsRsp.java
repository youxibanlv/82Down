package com.a82down.app.http.response;

import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.entity.Keyword;

import java.util.List;

/**
 * Created by strike on 16/6/12.
 */
public class KeywordsRsp extends BaseResponse {
    public ResultData resultData = null;
    public List<Keyword> getKeywords(){
        return resultData.keywords;
    }

    class ResultData {
        List<Keyword> keywords;
    }
}
