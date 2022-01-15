package com.freddy.shine.java.retrofit.interceptor;

import android.util.ArrayMap;

import androidx.annotation.NonNull;

import com.freddy.shine.java.retrofit.manager.RetrofitManager;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp请求头拦截器
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 15:46
 * @email : freddychencsc@gmail.com
 */
public class OkHttpRequestHeaderInterceptor extends OkHttpBaseInterceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();
        Headers headers = getHeaders(url);
        if (headers == null) {
            return chain.proceed(request);
        }
        return chain.proceed(request.newBuilder().headers(headers).build());
    }

    private Headers getHeaders(String url) {
        Headers.Builder headersBuilder = new Headers.Builder();
        ArrayMap<String, Object> headers = RetrofitManager.getInstance().getHeaders(url);
        if (headers == null || headers.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            headersBuilder.add(entry.getKey(), (String) entry.getValue());
        }
        return headersBuilder.build();
    }
}
