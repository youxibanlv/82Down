package com.a82down.app.http;

import org.xutils.common.Callback;

/**
 * Created by strike on 16/6/1.
 */
public abstract class MyCallBack implements Callback.CommonCallback<String> {

    @Override
    public abstract void onSuccess(String result);


    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public abstract void onFinished();
}
