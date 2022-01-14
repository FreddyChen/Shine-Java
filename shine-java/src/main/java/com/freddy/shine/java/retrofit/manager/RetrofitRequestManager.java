package com.freddy.shine.java.retrofit.manager;

import android.text.TextUtils;

import com.freddy.shine.java.AbstractRequestManager;
import com.freddy.shine.java.cipher.ICipher;
import com.freddy.shine.java.config.RequestOptions;
import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.listener.OnResponseListener;
import com.freddy.shine.java.parser.IParser;
import com.freddy.shine.java.utils.ShineLog;

import java.lang.reflect.Type;

/**
 * @author: FreddyChen
 * @date : 2022/01/14 11:53
 * @email : freddychencsc@gmail.com
 */
public class RetrofitRequestManager extends AbstractRequestManager {

    private RetrofitRequestManager() {}

    public static RetrofitRequestManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitRequestManager INSTANCE = new RetrofitRequestManager();
    }

    @Override
    public <T> void request(RequestOptions options, Type type, Class<? extends IParser> parserCls, Class<? extends ICipher> cipherCls, OnResponseListener<T> listener) {
        String className = getClass().getSimpleName();
        ShineLog.d(className + "#request()\noptions = " + options + "\ntype = " + type + "\nparserCls = " + parserCls + "\ncipherCls = " + cipherCls + "\nlistener = " + listener);
        if (listener != null) {
            listener.onStart();
        }

        String function = options.getFunction();
        if (TextUtils.isEmpty(function)) {
            ShineLog.d(className + "#request() failure, reason: function is null or empty");
            if (listener != null) {
                listener.onFailure(new RequestException(RequestException.Type.NATIVE, "request failure, reason: function is null or empty"));
                listener.onFinish();
            }
            return;
        }

        String baseUrl = options.getBaseUrl();
        if (TextUtils.isEmpty(baseUrl)) {
            ShineLog.d(className + "#request() failure, reason: baseUrl is null or empty");
            if (listener != null) {
                listener.onFailure(new RequestException(RequestException.Type.NATIVE, "request failure, reason: baseUrl is null or empty"));
                listener.onFinish();
            }
            return;
        }
    }

    @Override
    public <T> void syncRequest(RequestOptions options, Type type, Class<? extends IParser> parserCls, Class<? extends ICipher> cipherCls, OnResponseListener<T> listener) {

    }
}
