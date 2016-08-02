package com.a82down.app.http.response;

import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.entity.Subject;

import java.util.List;

/**
 * Created by strike on 16/8/2.
 */
public class SubjectRsp extends BaseResponse {
    public ResultData resultData = null;

    public List<Subject> getSubjects(){
        return resultData.subjects;
    }
    public int getTotalPage(){
        return resultData.totalPage;
    }
    class ResultData {
        List<Subject> subjects = null;
        int totalPage;
    }
}
