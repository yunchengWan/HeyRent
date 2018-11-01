package com.heyrent.app.renter.utils;

public class Constant {

    //请求日志Tag
    public static final String REQUEST_TAG = "request";
    //登录相关接口日志Tag
    public static final String LOGIN_TAG = "login";

    //SharedPreferences
    public static final String S_KEY_TOKEN = "token";
    public static final String S_KEY_REFRESH_TOKEN = "refreshToken";

    //ModifyUserInfoActivity modify type
    public static final String K_MODIFY_USER_INFO_TYPE = "k_modify_user_info_type";
    public static final int MODIFY_USER_INFO_TYPE_FIRST_NAME = 0x101;
    public static final int MODIFY_USER_INFO_TYPE_LAST_NAME = 0x102;
    public static final int MODIFY_USER_INFO_TYPE_PHONE = 0x103;
}
