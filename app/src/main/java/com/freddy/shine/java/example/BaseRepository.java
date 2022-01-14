package com.freddy.shine.java.example;

import android.util.Log;

import com.freddy.shine.java.ShineKit;
import com.freddy.shine.java.cipher.ICipher;
import com.freddy.shine.java.config.NetworkConfig;
import com.freddy.shine.java.config.RequestMethod;
import com.freddy.shine.java.config.RequestOptions;
import com.freddy.shine.java.listener.OnResponseListener;
import com.freddy.shine.java.parser.IParser;
import com.freddy.shine.java.utils.ParameterizedTypeImpl;
import com.freddy.shine.java.utils.TypeUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 00:57
 * @email : freddychencsc@gmail.com
 */
public abstract class BaseRepository {

    protected <T> void request(RequestOptions options, Type type, OnResponseListener<T> listener) {
        this.request(options, type, CustomParser1.class, null, listener);
    }

    protected <T> void request(RequestOptions options, Type type, Class<? extends ICipher> cipherCls, OnResponseListener<T> listener) {
        this.request(options, type, CustomParser1.class, cipherCls, listener);
    }

    protected <T> void request(RequestOptions options, Type type, Class<? extends IParser> parserCls, Class<? extends ICipher> cipherCls, OnResponseListener<T> listener) {
        ShineKit.getInstance().getRequestManager().request(
                options,
                type,
                parserCls,
                cipherCls,
                listener
        );
    }

    protected RequestOptions.Builder getDefaultRequestOptionsBuilder(RequestMethod requestMethod, String function) {
        return new RequestOptions.Builder()
                .setRequestMethod(requestMethod)
                .setBaseUrl("https://www.wanandroid.com/")
                .setFunction(function)
                .setContentType(NetworkConfig.DEFAULT_CONTENT_TYPE);
    }

    protected <T> Type getType(Class<T> clazz) {
        return TypeUtil.getType(clazz, clazz);
    }

    protected <T> Type getListType(Class<T> clazz) {
        return TypeUtil.getType(List.class, clazz);
    }
}
