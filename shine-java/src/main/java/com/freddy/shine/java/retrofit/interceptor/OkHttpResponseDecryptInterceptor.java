package com.freddy.shine.java.retrofit.interceptor;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.freddy.shine.java.cipher.ICipher;
import com.freddy.shine.java.config.RequestMethod;
import com.freddy.shine.java.retrofit.manager.RetrofitManager;
import com.freddy.shine.java.utils.ShineLog;

import java.io.IOException;
import java.nio.charset.Charset;

import kotlin.text.Charsets;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * OkHttp响应数据解密拦截器
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 15:48
 * @email : freddychencsc@gmail.com
 */
public class OkHttpResponseDecryptInterceptor extends OkHttpBaseInterceptor {

    private static final String TAG = OkHttpResponseDecryptInterceptor.class.getSimpleName();

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        HttpUrl requestUrl = request.url();
        String urlString = requestUrl.toString();
        String url;

        RequestMethod requestMethod = getRequestMethod(request.method());
        if (requestMethod == null) return response;
        switch (requestMethod) {
            case GET:
            case DELETE: {
                if (TextUtils.isEmpty(requestUrl.encodedQuery())) {
                    url = urlString;
                } else {
                    url = urlString.substring(0, urlString.indexOf("?"));
                }
                break;
            }

            case POST:
            case PUT:
            default: {
                url = urlString;
                break;
            }
        }

        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                try {
                    ICipher cipher = RetrofitManager.getInstance().getCipher(url);
                    if (cipher == null) {
                        return response;
                    }
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer = source.getBuffer();
                    Charset charset = Charsets.UTF_8;
                    MediaType contentType = responseBody.contentType();
                    if (contentType == null) {
                        return response;
                    }
                    charset = contentType.charset(charset);
                    if (charset == null) {
                        return response;
                    }
                    String responseData = buffer.clone().readString(charset).trim();
                    String decryptData = cipher.decrypt(responseData);
                    if (TextUtils.isEmpty(decryptData)) {
                        return response;
                    }
                    ResponseBody newResponseBody = ResponseBody.create(decryptData, contentType);
                    response = response.newBuilder().body(newResponseBody).build();
                    ShineLog.i(TAG + "#intercept() \nresponseBody = " + responseBody + "\nnewResponseBody = " + newResponseBody + "\nresponseData = " + responseData + "\ndecryptData = " + decryptData);
                } catch (Exception e) {
                    e.printStackTrace();
                    ShineLog.e(TAG + "#intercept() decrypt failure, reason:${e.message}");
                }
            }
        }
        RetrofitManager.getInstance().removeCipherCls(url);
        return response;
    }
}
