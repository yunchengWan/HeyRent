package com.heyrent.app.renter.net;

import com.heyrent.app.renter.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestBodyBuilder {

    private JSONObject baseJson;

    public RequestBodyBuilder() {
        baseJson = new JSONObject();
    }

    public RequestBodyBuilder put(String key, Object value) {
        try {
            baseJson.put(key, value);
        } catch (JSONException e) {
            LogUtil.e("request", "build request body error" + e.getMessage());
        }
        return this;
    }

    public RequestBody build() {
        return RequestBody.create(MediaType.parse("application/json"), baseJson.toString());
    }
}
