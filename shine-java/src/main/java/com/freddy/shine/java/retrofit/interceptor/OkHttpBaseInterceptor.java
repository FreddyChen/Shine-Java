package com.freddy.shine.java.retrofit.interceptor;

import com.freddy.shine.java.config.RequestMethod;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;

import kotlin.text.Charsets;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * OkHttp拦截器基类，所有OkHttp拦截器都继承此类
 *
 * @author: FreddyChen
 * @date : 2022/01/14 15:46
 * @email : freddychencsc@gmail.com
 * @see OkHttpLoggingInterceptor
 */
public abstract class OkHttpBaseInterceptor implements Interceptor {

    protected String getRequestInfo(Request request, String method) {
        RequestMethod requestMethod = getRequestMethod(method);
        if (requestMethod == null) return null;

        switch (requestMethod) {
            case GET:
            case DELETE: {
                HttpUrl httpUrl = request.url();
                Set<String> paramKeys = httpUrl.queryParameterNames();
                if (paramKeys.isEmpty()) {
                    return null;
                }
                StringBuilder resultBuilder = new StringBuilder();
                for (String key : paramKeys) {
                    String value = httpUrl.queryParameter(key);
                    resultBuilder.append(key).append("=").append(value).append("\n");
                }
                resultBuilder.deleteCharAt(resultBuilder.length() - 1);
                return resultBuilder.toString();
            }
            case POST:
            case PUT: {
                RequestBody requestBody = request.body();
                if (requestBody == null) {
                    return null;
                }
                try {
                    Buffer bufferedSink = new Buffer();
                    requestBody.writeTo(bufferedSink);
                    Charset charset = Charsets.UTF_8;
                    return bufferedSink.readString(charset);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    protected String getResponseInfo(Response response) {
        String str = null;
//        if (!response.isSuccessful) {
//            return response.message
//        }
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return null;
        }
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.getBuffer();
        Charset charset = Charset.forName(Charsets.UTF_8.name());
        if (contentLength != 0L) {
            str = buffer.clone().readString(charset);
        }
        return str;
    }

    protected RequestMethod getRequestMethod(String method) {
        RequestMethod requestMethod = null;
        switch (method.toUpperCase()) {
            case "GET": {
                requestMethod = RequestMethod.GET;
                break;
            }
            case "POST": {
                requestMethod = RequestMethod.POST;
                break;
            }
            case "PUT": {
                requestMethod = RequestMethod.PUT;
                break;
            }
            case "DELETE": {
                requestMethod = RequestMethod.DELETE;
                break;
            }
        }
        return requestMethod;
    }
}
