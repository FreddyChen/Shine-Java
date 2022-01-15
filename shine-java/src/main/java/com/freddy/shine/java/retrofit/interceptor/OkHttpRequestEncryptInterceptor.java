package com.freddy.shine.java.retrofit.interceptor;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.freddy.shine.java.cipher.ICipher;
import com.freddy.shine.java.config.RequestMethod;
import com.freddy.shine.java.retrofit.manager.RetrofitManager;
import com.freddy.shine.java.utils.ShineLog;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import kotlin.text.Charsets;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * OkHttp请求数据加密拦截器
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 15:47
 * @email : freddychencsc@gmail.com
 */
public class OkHttpRequestEncryptInterceptor extends OkHttpBaseInterceptor {

    private static final String TAG = OkHttpRequestEncryptInterceptor.class.getSimpleName();

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        RequestMethod requestMethod = getRequestMethod(request.method());
        if (requestMethod == null) {
            return chain.proceed(request);
        }
        HttpUrl url = request.url();
        String urlString = url.toString();
        switch (requestMethod) {
            case GET:
            case DELETE: {
                if (!TextUtils.isEmpty(url.encodedQuery())) {
                    try {
                        StringBuilder apiBuilder = new StringBuilder();
                        apiBuilder.append(url.scheme());
                        apiBuilder.append("://");
                        apiBuilder.append(url.host());
                        apiBuilder.append(url.encodedPath());
                        String api = apiBuilder.toString().trim();
                        ICipher cipher = RetrofitManager.getInstance().getCipher(urlString.substring(0, urlString.indexOf("?")));
                        if (cipher != null) {
                            StringBuilder newApiBuilder = new StringBuilder();
                            newApiBuilder.append(api);
                            newApiBuilder.append(cipher.getParamName());
                            newApiBuilder.append("=");
                            newApiBuilder.append(cipher.encrypt(url.encodedQuery()));
                            String newApi = newApiBuilder.toString().trim();
                            request = request.newBuilder().url(newApi).build();
                            ShineLog.i(TAG + "#intercept()\napi = " + api + "\nnewApi = " + newApi);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ShineLog.e(TAG + "#intercept() encrypt failure, reason: " + e.getMessage());
                        chain.proceed(request);
                    }
                }
                break;
            }

            case POST:
            case PUT: {
                RequestBody requestBody = request.body();
                if (requestBody != null) {
                    Charset charset = null;
                    MediaType contentType = requestBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(Charsets.UTF_8);
                        // 如果contentType为multipart，则不进行加密
                        if ("multipart".equalsIgnoreCase(contentType.type())) {
                            return chain.proceed(request);
                        }
                    }
                    if (charset == null) {
                        return chain.proceed(request);
                    }
                    try {
                        ICipher cipher = RetrofitManager.getInstance().getCipher(urlString);
                        if (cipher != null) {
                            Buffer buffer = new Buffer();
                            requestBody.writeTo(buffer);
                            String requestData = URLDecoder.decode(buffer.readString(charset).trim(), Charsets.UTF_8.name());
                            String encryptData = cipher.encrypt(requestData);
                            if (!TextUtils.isEmpty(encryptData)) {
                                RequestBody newRequestBody = RequestBody.create(encryptData, contentType);
                                Request.Builder newRequestBuilder = request.newBuilder();
                                switch (requestMethod) {
                                    case POST: {
                                        newRequestBuilder.post(newRequestBody);
                                        break;
                                    }
                                    case PUT: {
                                        newRequestBuilder.put(newRequestBody);
                                        break;
                                    }
                                }
                                request = newRequestBuilder.build();
                                ShineLog.i(TAG + "#intercept() \nrequestBody = " + requestBody + "\nnewRequestBody = " + newRequestBody + "\nrequestData = " + requestData + "\nencryptData = " + encryptData);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ShineLog.e(TAG + "#intercept() encrypt failure, reason: " + e.getMessage());
                        chain.proceed(request);
                    }
                }
                break;
            }
        }
        return chain.proceed(request);
    }
}
