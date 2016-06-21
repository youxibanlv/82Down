package com.a82down.app.base;

import android.os.Environment;

import java.io.File;

/**
 * Created by strike on 16/5/31.
 */
public class AppConfig {

    /*****
     * 本地文件的默认存储路径
     ****/
    public static final String BASE_PATH = Environment.getExternalStorageDirectory() + File.separator + "82down"+File.separator;
    /**
     * 日志的存储路径
     */
    public static final String LOG_PATH = BASE_PATH + "/log/";
    /**
     * 默认的字符格式
     */
    /**
     * app 默认数据库
     */
    public static final String DEFAULT_APP_DB = "82down.db";
    /**
     * APP默认数据库版本号
     */
    public static final int DEFAULT_DB_VERSION = 1;
    /**
     * APP默认编码格式
     */
    public static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 是否debug调试
     */
    public static boolean DEBUG = true;

    public static final String DOWNLOAD_APK_NAME = "82down.apk";
}
