package com.a82down.app.http;

import android.text.TextUtils;

import com.a82down.app.utils.UiUtils;

import org.xutils.common.util.LogUtil;

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
                try {
                    Class c = Class.forName(resultClass.getName());
                    rsp = (BaseResponse) c.newInstance();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                if (rsp == null){
                    rsp = new BaseResponse();
                }
                if (rsp.result != HttpConstance.HTTP_SUCCESS){//提示错误信息
                    if (rsp.failReason != null && !"".equals(rsp.failReason)){
                        UiUtils.showTipToast(false,rsp.failReason);
                    }
                }
                LogUtil.e(result);
            }
        }
        return rsp;
    }
}
