package com.a82down.app.http;

import com.a82down.app.base.AppConfig;
import com.a82down.app.utils.LogFactory;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.body.StringBody;
import org.xutils.x;

import java.io.UnsupportedEncodingException;

import gson.Gson;

/**
 * Created by xiaowuyue on 16/5/31.
 */
public class BaseRequest {

    /**
     * 接口类型
     */
    public String cmdType = "";
    /**
     * 方法名称
     */
    public String methodName = "";
    /**
     * 令牌
     */
    public String token = "";
    /**
     * openId
     */
    public String openId = "";

    public transient RequestParams rp;


    public String getRequestData(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void sendRequest(Callback.CommonCallback<String> callback){
        final Callback.CommonCallback<String> callback1 = callback;
        this.postRequest(UrlConfig.getUrl(cmdType),this.getRequestData(),callback);
    }

    private void postRequest(String url,String requestData,final Callback.CommonCallback<String> callback){
        try {
            StringBody sb = new StringBody(requestData, AppConfig.DEFAULT_CHARSET);
            rp = new RequestParams(url,null,null,null);
            rp.setRequestBody(sb);
            LogFactory.e(rp.toString());
            x.http().request(HttpMethod.POST,rp,callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
