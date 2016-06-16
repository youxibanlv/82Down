package com.a82down.app.http;

import com.a82down.app.R;
import com.a82down.app.base.AppContext;
import com.a82down.app.utils.UiUtils;

import org.xutils.common.Callback;

import java.util.concurrent.TimeoutException;

/**
 * Created by strike on 16/6/1.
 */
public abstract class NormalCallBack implements Callback.CommonCallback<String> {

    @Override
    public abstract void onSuccess(String result);


    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        if (ex instanceof TimeoutException){
            UiUtils.showTipToast(false, AppContext.getContext().getString(R.string.connect_timeout));
        }
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public abstract void onFinished();
}
