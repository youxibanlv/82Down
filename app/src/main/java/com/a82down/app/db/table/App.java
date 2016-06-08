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
    private String app_id;

    @Column(name = "last_cate_id")
    private String last_cate_id;

    @Column(name = "app_title")
    private String app_title;

    @Column(name = "app_stitle")
    private String app_stitle;

    @Column(name = "app_version")
    private String app_version;

    @Column(name = "app_update_time")
    private String app_update_time;

    @Column(name = "app_size")
    private String app_size;

    @Column(name = "app_system")
    private String app_system;

    @Column(name = "app_type")
    private String app_type;

    @Column(name = "app_logo")
    private String app_logo;

    @Column(name = "app_desc")
    private String app_desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getLast_cate_id() {
        return last_cate_id;
    }

    public void setLast_cate_id(String last_cate_id) {
        this.last_cate_id = last_cate_id;
    }

    public String getApp_title() {
        return app_title;
    }

    public void setApp_title(String app_title) {
        this.app_title = app_title;
    }

    public String getApp_stitle() {
        return app_stitle;
    }

    public void setApp_stitle(String app_stitle) {
        this.app_stitle = app_stitle;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getApp_update_time() {
        return app_update_time;
    }

    public void setApp_update_time(String app_update_time) {
        this.app_update_time = app_update_time;
    }

    public String getApp_size() {
        return app_size;
    }

    public void setApp_size(String app_size) {
        this.app_size = app_size;
    }

    public String getApp_system() {
        return app_system;
    }

    public void setApp_system(String app_system) {
        this.app_system = app_system;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_logo() {
        return app_logo;
    }

    public void setApp_logo(String app_logo) {
        this.app_logo = app_logo;
    }

    public String getApp_desc() {
        return app_desc;
    }

    public void setApp_desc(String app_desc) {
        this.app_desc = app_desc;
    }
}
