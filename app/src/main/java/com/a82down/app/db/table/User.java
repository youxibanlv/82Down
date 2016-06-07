package com.a82down.app.db.table;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by strike on 16/6/2.
 */
@Table(name = "appcms_user")
public class User {
    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "uid")
    private String uid;//用户id

    @Column(name = "username")
    private String username;//账号

    @Column(name = "password")
    private String password;//密码

    @Column(name = "token")
    private String token;//令牌

    @Column(name = "phone")
    private int phone;//电话号码

    @Column(name = "nickname")
    private String nickname;//昵称

    @Column(name = "icon")
    private String icon;//头像路径

    @Column(name = "alipay")
    private String alipay;//支付宝账号

    @Column(name = "point")
    private int point;//积分点

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
