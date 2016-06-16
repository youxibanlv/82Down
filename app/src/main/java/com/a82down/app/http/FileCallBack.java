package com.a82down.app.http;

import org.xutils.common.Callback;

import java.io.File;

/**
 * Created by strike on 16/6/16.
 */
public abstract class FileCallBack implements Callback.CommonCallback<File> {

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }
}
