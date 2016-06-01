package com.a82down.app.base;

import android.app.Application;

import com.a82down.app.db.DbConfig;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by xiaowuyue on 16/5/31.
 */
public class MyApplication extends Application {

    public static DbManager appDb = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xutils
        AppContext.setContext(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    public static DbManager getAppDb() {
        if (appDb == null) {
            appDb = x.getDb(DbConfig.APP_DB_CONFIG);
        }
        return appDb;
    }
}
