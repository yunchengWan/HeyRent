package com.heyrent.app.renter.utils;

public class ApiConstant {
    /**
     * 基地址
     */
    public static final String BASE_URL = "http://develop.heyrent.com/";
    /**
     * 登录
     */
    public static final String LOGIN = "api/v1/login";
    /**
     * 注册
     */
    public static final String SIGN_UP = "api/v1/register";
    /**
     * refreshToken换取token
     */
    public static final String GET_TOKEN_BY_REFRESH_TOKEN = "oauth/token";
    /**
     * 查询userInfo
     */
    public static final String GET_USER_INFO = "api/v1/userInfo";
    /**
     * 修改个人信息
     */
    public static final String MODIFY_USER_INFO = "api/v1/userInfo";
    /**
     * 获取图片上传地址
     */
    public static final String GET_IMAGE_UPLOAD_URL = "api/v1/s3/url";
}
