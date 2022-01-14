package com.freddy.shine.java.retrofit.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author: FreddyChen
 * @date : 2022/01/14 15:46
 * @email : freddychencsc@gmail.com
 */
public class OkHttpRequestHeaderInterceptor extends OkHttpBaseInterceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return null;
    }
}
