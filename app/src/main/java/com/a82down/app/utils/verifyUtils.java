package com.a82down.app.utils;

import java.util.regex.Pattern;

/**
 * Created by strike on 16/6/4.
 */
public class VerifyUtils {

    // 判断输入用户名是否正确
    public static boolean checkUserName(String userName) {
        String regexWifiName = "^[a-zA-Z0-9_]{3,32}";
        return Pattern.matches(regexWifiName, userName);
    }

    // 判断输入用户名是否正确
    public static boolean checkPassword(String password) {
        String regexWifiName = "^[a-zA-Z0-9_]{3,32}";
        return Pattern.matches(regexWifiName, password);
    }
}
