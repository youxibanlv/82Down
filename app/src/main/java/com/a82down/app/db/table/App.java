package com.a82down.app.db.table;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by strike on 16/6/7.
 */
@Table(name = "appcms_app")
public class App {

    @Column(name = "id",isId = true)
    private int id;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "last_cate_id")
    private String last_cate_id;

    @Column(name = "app_title")
    private String appTitle;

    @Column(name = "app_stitle")
    private String appSitle;

    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "app_update_time")
    private String appUpdateTime;

    @Column(name = "app_size")
    private String appSize;

    @Column(name = "app_system")
    private String appSystem;

    @Column(name = "app_type")
    private String appType;

    @Column(name = "app_logo")
    private String appLogo;

    @Column(name = "app_desc")
    private String appDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLast_cate_id() {
        return last_cate_id;
    }

    public void setLast_cate_id(String last_cate_id) {
        this.last_cate_id = last_cate_id;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppSitle() {
        return appSitle;
    }

    public void setAppSitle(String appSitle) {
        this.appSitle = appSitle;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppUpdateTime() {
        return appUpdateTime;
    }

    public void setAppUpdateTime(String appUpdateTime) {
        this.appUpdateTime = appUpdateTime;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(String appSystem) {
        this.appSystem = appSystem;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }
}
