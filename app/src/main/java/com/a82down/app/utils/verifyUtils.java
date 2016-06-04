package com.a82down.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaowuyue on 16/6/4.
 */
public class VerifyUtils {

    // 判断输入用户名是否正确
    public static boolean checkUserName(String userName) {
        boolean bFlag;
        if (userName == null || userName.length() < 3 || userName.length() > 32) {
            return false;
        }

        if (userName.charAt(0) == '_' || userName.charAt(userName.length() - 1) == '_') {
            return false;
        }

        Pattern p = Pattern.compile("[a-zA-Z0-9_]");
        Matcher m = p.matcher(userName.substring(0, 1));
        bFlag = m.matches();

        return bFlag;
    }

    // 判断输入用户名是否正确
    public static boolean checkPassword(String password) {
        boolean bFlag;
        if (password == null || password.length() < 3 || password.length() > 32) {
            return false;
        }

        if (password.charAt(0) == '_' || password.charAt(password.length() - 1) == '_') {
            return false;
        }

        Pattern p = Pattern.compile("[a-zA-Z0-9_]");
        Matcher m = p.matcher(password.substring(0, 1));
        bFlag = m.matches();
        return bFlag;
    }
}
