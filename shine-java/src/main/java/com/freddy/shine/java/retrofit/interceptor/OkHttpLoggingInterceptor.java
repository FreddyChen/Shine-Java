package com.freddy.shine.java.retrofit.interceptor;

import androidx.annotation.NonNull;

import com.freddy.shine.java.utils.ShineLog;

import java.io.IOException;
import java.util.Iterator;

import kotlin.Pair;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp 日志拦截器
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 15:47
 * @email : freddychencsc@gmail.com
 */
public class OkHttpLoggingInterceptor extends OkHttpBaseInterceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        long startTime = System.currentTimeMillis();
        Request request = chain.request();
        Response response = chain.proceed(request);
        String method = request.method();
        StringBuilder logBuilder = new StringBuilder();
        Headers headers = request.headers();
        String headerString = null;
        if (headers.size() == 0) {
            headerString = null;
        } else {
            StringBuilder headerBuilder = new StringBuilder();
            for (Iterator<Pair<String, String>> it = headers.iterator(); it.hasNext(); ) {
                Pair<String, String> header = it.next();
                headerBuilder.append(header.getFirst()).append(":").append(header.getSecond()).append("\t");
            }
            headerString = headerBuilder.toString();
        }
        logBuilder.append("...\n接口地址：").append(request.url())
                .append("\n请求方式：").append(method)
                .append("\n请求头：").append(headerString)
                .append("\n请求参数：").append(getRequestInfo(request, method));
        long endTime = System.currentTimeMillis();
        logBuilder.append("\n请求耗时：").append(endTime - startTime).append("ms");
        logBuilder.append("\n请求响应：").append(getResponseInfo(response));
        ShineLog.i(logBuilder.toString());
        return response;
    }
}
