package com.heyrent.app.renter.utils;

import android.text.TextUtils;

public class NumberUtils {

    /**
     * 分割电话号码
     * 12345678910 -> 123-4567-8910
     */
    public static String splitPhoneBy4Char(String originStr) {
        if (TextUtils.isEmpty(originStr)) {
            return originStr;
        }
        if (originStr.length() <= 4) {
            return originStr;
        }
        if (originStr.length() <= 8) {
            String last = originStr.substring(originStr.length() - 4, originStr.length());
            return originStr.substring(0, originStr.length() - 4) + "-" + last;
        }
        String last = originStr.substring(originStr.length() - 4, originStr.length());
        String after = originStr.substring(originStr.length() - 8, originStr.length() - 4);
        return originStr.substring(0, originStr.length() - 8) + "-" + after + "-" + last;
    }
}
