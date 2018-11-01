package com.heyrent.app.renter.utils.net;

import com.heyrent.app.renter.model.RequestResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JsonHelper {

    /**
     * 构建Gson解析需要的类型
     *
     * @param raw  外层class
     * @param args 内层class
     */
    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    public static ParameterizedType baseRequestType(final Type... args) {
        return type(RequestResponse.class, args);
    }
}
