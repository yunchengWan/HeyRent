package com.heyrent.app.renter.model;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heyrent.app.renter.net.HeyRentApi;
import com.heyrent.app.renter.net.RequestBodyBuilder;
import com.heyrent.app.renter.utils.ApiConstant;
import com.heyrent.app.renter.utils.EncryptUtil;
import com.heyrent.app.renter.utils.LogUtil;
import com.heyrent.app.renter.utils.LoginManager;
import com.heyrent.app.renter.utils.net.JsonHelper;
import com.heyrent.app.renter.view.login.LoginActivity;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.HttpException;

public class UserRepository {

    @Inject
    HeyRentApi mApi;
    @Inject
    LoginManager mLoginManager;
    @Inject
    Context mAppContext;

    @Inject
    public UserRepository() {

    }

    public Observable<UserLoginInfo> login(String email, String password) {
        RequestBody requestBody = new RequestBodyBuilder()
                .put("client_id", "client")
                .put("client_secret", "secret")
                .put("grant_type", "password")
                .put("password", EncryptUtil.md5(password))
                .put("scope", "all")
                .put("userType", 2)
                .put("username", email)
                .build();

        return mApi.doPostRequestByJson(ApiConstant.LOGIN, requestBody)
                .compose(RxSchedulers.commonScheduler())
                .map(s -> new Gson().fromJson(s, UserLoginInfo.class))
                .doOnNext(userLoginInfo -> {
                    if (userLoginInfo.getResult() == 0) {
                        mLoginManager.onLoginSuccess(userLoginInfo);
                    }
                });

    }

    public Observable<UserLoginInfo> signUp(String email, String password, String firstName, String lastName) {
        RequestBody requestBody = new RequestBodyBuilder()
                .put("email", email)
                .put("password", EncryptUtil.md5(password))
                .put("userType", 2)
                .put("firstName", firstName)
                .put("lastName", lastName)
                .build();

        return mApi.doPostRequestByJson(ApiConstant.SIGN_UP, requestBody)
                .compose(RxSchedulers.commonScheduler())
                .map((Function<String, RequestResponse<String>>) s ->
                        new Gson().fromJson(s, JsonHelper.type(RequestResponse.class, String.class)))
                .flatMap(stringRequestResponse -> {
                            if (stringRequestResponse.isSuccess()) {
                                return login(email, password);
                            }
                            return Observable.create(emitter -> emitter.onError(new Throwable("Sign up failed")));
                        }
                )
                .doOnNext(userLoginInfo -> {
                    if (userLoginInfo.getResult() == 0) {
                        mLoginManager.onLoginSuccess(userLoginInfo);
                    }
                });
    }

    /**
     * 用refreshToken换取token接口
     */
    public Observable<RefreshGetTokenBean> getTokenByRefreshToken() {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("client_id", "client");
        paraMap.put("client_secret", "secret");
        paraMap.put("grant_type", "refresh_token");
        paraMap.put("refresh_token", mLoginManager.getRefreshToken());

        return mApi.doPostRequest(ApiConstant.GET_TOKEN_BY_REFRESH_TOKEN, paraMap)
                .compose(RxSchedulers.commonScheduler())
                .map(s -> new Gson().fromJson(s, RefreshGetTokenBean.class))
                .flatMap(
                        (Function<RefreshGetTokenBean, ObservableSource<RefreshGetTokenBean>>) response -> {
                            if (!TextUtils.isEmpty(response.getAccess_token()) &&
                                    !TextUtils.isEmpty(response.getRefresh_token())) {
                                mLoginManager.updateToken(
                                        response.getAccess_token(),
                                        response.getRefresh_token()
                                );
                                return Observable.create(emitter -> emitter.onNext(response));
                            }
                            return Observable.create(emitter -> emitter.onError(new Throwable("RequestFailed")));
                        })
                .doOnError(
                        throwable -> {
                            if ((throwable instanceof HttpException &&
                                    ((HttpException) throwable).code() == 401) ||
                                    throwable.getMessage().equals("RequestFailed")) {
                                LoginActivity.startActivity(mAppContext, new Bundle());
                            }
                        }
                );
    }

    /**
     * 获取UserInfo
     */
    public Observable<RequestResponse<UserInfoBean>> getUserInfo() {
        Map<String, Object> paraMap = new HashMap<>();
        return mApi.doGetRequest(ApiConstant.GET_USER_INFO, paraMap)
                .compose(RxSchedulers.commonScheduler())
                .map((Function<String, RequestResponse<UserInfoBean>>) response -> new Gson().fromJson(
                        response,
                        JsonHelper.type(RequestResponse.class, UserInfoBean.class))
                )
                .retryWhen(
                        throwableObservable -> throwableObservable.flatMap(
                                throwable -> {
                                    if (throwable instanceof HttpException &&
                                            ((HttpException) throwable).code() == 401) {
                                        //Token过期
                                        return getTokenByRefreshToken();
                                    }
                                    return Observable.error(throwable);
                                }
                        )
                )
                .doOnNext(
                        response -> {
                            if (response.isSuccess()) {
                                mLoginManager.setUserInfo(response.getData());
                            }
                        }
                );
    }

    /**
     * 修改个人信息
     */
    public Observable<RequestResponse<String>> modifyUserInfo(String firstName, String lastName, String phone, String avatar) {
        RequestBodyBuilder builder = new RequestBodyBuilder();
        if (!TextUtils.isEmpty(firstName)) {
            builder.put("firstName", firstName);
        }
        if (!TextUtils.isEmpty(lastName)) {
            builder.put("lastName", lastName);
        }
        if (!TextUtils.isEmpty(phone)) {
            builder.put("phone", phone);
        }
        if (!TextUtils.isEmpty(avatar)) {
            builder.put("avatar", avatar);
        }
        if (mLoginManager.getUserInfo() != null) {
            builder.put("id", mLoginManager.getUserInfo().getUserInfo().getId())
                    .put("userId", mLoginManager.getUserInfo().getUserInfo().getUserId());
        }
        RequestBody requestBody = builder.build();

        return mApi.doPutRequestByJson(ApiConstant.MODIFY_USER_INFO, requestBody)
                .compose(RxSchedulers.commonScheduler())
                .map(
                        s -> new Gson().fromJson(s, JsonHelper.baseRequestType(String.class))
                );
    }

    /**
     * 获取图片上传地址
     */
    public Observable<RequestResponse<List<String>>> getImageUploadUrl() {
        Map<String, Object> paraMap = new HashMap<>();
        return mApi.doGetRequest(ApiConstant.GET_IMAGE_UPLOAD_URL, paraMap)
                .compose(RxSchedulers.commonScheduler())
                .map(
                        s -> new Gson().fromJson(s, JsonHelper.baseRequestType(new TypeToken<List<String>>() {
                        }.getType()))
                );
    }

    /**
     * 根据url上传图片到s3
     */
    public Observable<String> uploadImage(String url, String filePath) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), new File(filePath));
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "heyrentAvatar", requestFile);
        return mApi.doPutUploadByFullUrl(url, body)
                .compose(RxSchedulers.commonScheduler());
    }

    /**
     * uploadAvatar
     */
    public void uploadAvatar(String filePath) {
        getImageUploadUrl()
                .flatMap(
                        urlListResponse -> uploadImage(urlListResponse.getData().get(0), filePath)
                )
                .subscribe(
                        response -> {
                            LogUtil.d("", "");
                        },
                        throwable -> {
                            throwable.getMessage();
                        }
                );
    }
}
