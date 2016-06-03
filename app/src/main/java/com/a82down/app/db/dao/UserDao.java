package com.a82down.app.db.dao;

import android.text.TextUtils;

import com.a82down.app.base.MyApplication;
import com.a82down.app.db.table.User;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

/**
 * Created by xiaowuyue on 16/6/2.
 */
public class UserDao {

    public static User getUserByUserName(String userName){
        User user = null;
        if (!TextUtils.isEmpty(userName)){
            DbManager dbManager = MyApplication.getAppDb();
            try {
                user = dbManager.selector(User.class).where("username","=",userName).findFirst();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static void saveUser(User user){
        if (user!= null){
            DbManager dbManager = MyApplication.getAppDb();
            try {
                dbManager.saveOrUpdate(user);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

}
