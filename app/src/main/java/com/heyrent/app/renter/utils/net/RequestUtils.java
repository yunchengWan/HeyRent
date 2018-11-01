package com.heyrent.app.renter.utils.net;

import com.heyrent.app.renter.utils.ApiConstant;

public class RequestUtils {

    /**
     * 判断是否需要加Token
     */
    public static boolean needAuth(String url) {
        return !url.endsWith(ApiConstant.LOGIN) &&
                !url.endsWith(ApiConstant.SIGN_UP) &&
                !url.endsWith(ApiConstant.GET_TOKEN_BY_REFRESH_TOKEN);
    }
}
