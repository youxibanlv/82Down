package com.a82down.app.http;

import android.text.TextUtils;

import com.a82down.app.utils.LogFactory;

import gson.Gson;

/**
 * Created by strike on 16/5/31.
 */
public class BaseResponse {
    public int result;
    public String failReason;

    public static BaseResponse getRsp(String result,Class resultClass){
        BaseResponse rsp = new BaseResponse();
        if (!TextUtils.isEmpty(result)){
            Gson gson = new Gson();
            try{
                if (resultClass == BaseResponse.class || BaseResponse.class == resultClass.getSuperclass()){
                    rsp = (BaseResponse) gson.fromJson(result,resultClass);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (rsp == null){
                    rsp = new BaseResponse();
                }
            }
        }
        LogFactory.e("response :"+ result);
        return rsp;
    }
}
