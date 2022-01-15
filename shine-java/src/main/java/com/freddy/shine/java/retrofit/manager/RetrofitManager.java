package com.freddy.shine.java.retrofit.manager;

import android.util.ArrayMap;

import com.freddy.shine.java.cipher.ICipher;
import com.freddy.shine.java.retrofit.converter.StringConverterFactory;
import com.freddy.shine.java.retrofit.interceptor.OkHttpLoggingInterceptor;
import com.freddy.shine.java.retrofit.interceptor.OkHttpResponseDecryptInterceptor;
import com.freddy.shine.java.retrofit.interceptor.OkHttpRequestEncryptInterceptor;
import com.freddy.shine.java.retrofit.interceptor.OkHttpRequestHeaderInterceptor;
import com.freddy.shine.java.utils.ShineLog;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * Retrofit管理类，提供获取OkHttpClient、Retrofit等方法
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 15:40
 * @email : freddychencsc@gmail.com
 */
public class RetrofitManager {

    private static final String TAG = "RetrofitManager";

    private RetrofitManager() {
        this.mRetrofitMap = new ConcurrentHashMap<>();
        this.mCipherClsMap = new HashMap<>();
        this.mCipherMap = new ConcurrentHashMap<>();
        this.mHeadersMap = new HashMap<>();
    }

    public static RetrofitManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     * Retrofit集合
     * key:     baseUrl
     * value:   Retrofit
     */
    private final ConcurrentHashMap<String, Retrofit> mRetrofitMap;

    /**
     * 加解密器Cls集合
     * key:     url(baseUrl+function)
     * value:   Cipher Clazz
     */
    private final Map<String, Class<? extends ICipher>> mCipherClsMap;

    /**
     * 加解密器集合
     * key:     Cipher Clazz
     * value:   Cipher instance
     */
    private final ConcurrentHashMap<Class<? extends ICipher>, ICipher> mCipherMap;

    /**
     * 请求头集合
     * key:     url(baseUrl+function)
     * value:   headers
     */
    private final Map<String, ArrayMap<String, Object>> mHeadersMap;

    /**
     * 获取OkHttpClient
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        long timeout = 60 * 1000L;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .addInterceptor(new OkHttpRequestHeaderInterceptor())
                .addInterceptor(new OkHttpLoggingInterceptor())
                .addInterceptor(new OkHttpRequestEncryptInterceptor())
                .addInterceptor(new OkHttpResponseDecryptInterceptor());
        return builder.build();
    }

    /**
     * 根据baseUrl获取对应的Retrofit实例
     * 首次获取时同时保存起来，方便下次直接获取
     */
    Retrofit getRetrofit(String baseUrl) {
        if (mRetrofitMap.containsKey(baseUrl)) {
            return mRetrofitMap.get(baseUrl);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        mRetrofitMap.put(baseUrl, retrofit);
        return retrofit;
    }

    /**
     * 临时保存接口请求头
     *
     * @param url baseUrl+function
     */
    public void saveHeaders(String url, ArrayMap<String, Object> headers) {
        if (mHeadersMap.containsKey(url)) return;
        mHeadersMap.put(url, headers);
    }

    /**
     * 获取接口请求头，并移除
     *
     * @param url baseUrl+function
     */
    public ArrayMap<String, Object> getHeaders(String url) {
        if (!mHeadersMap.containsKey(url)) {
            return null;
        }
        ArrayMap<String, Object> headers = mHeadersMap.get(url);
        mHeadersMap.remove(url);
        return headers;
    }

    /**
     * 临时保存接口加解密器
     *
     * @param url baseUrl+function
     */
    void saveCipher(String url, Class<? extends ICipher> cipherCls) {
        if (mCipherClsMap.containsKey(url)) return;
        mCipherClsMap.put(url, cipherCls);
    }

    /**
     * 获取接口加解密器
     *
     * @param url baseUrl+function
     */
    public ICipher getCipher(String url) {
        if (!mCipherClsMap.containsKey(url)) return null;
        Class<? extends ICipher> cipherCls = mCipherClsMap.get(url);
        if (cipherCls == null) return null;
        ICipher cipher = null;
        if (mCipherMap.containsKey(cipherCls)) {
            cipher = mCipherMap.get(cipherCls);
        } else {
            try {
                cipher = (ICipher) Class.forName(cipherCls.getName()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (cipher == null) return null;
        mCipherMap.put(cipherCls, cipher);
        ShineLog.i(TAG + "#getCipher() \nurl = $url\ncipherCls = $cipherCls\ncipher = $cipher");
        return cipher;
    }

    /**
     * 移除接口加解密器
     */
    public void removeCipherCls(String url) {
        if (!mCipherClsMap.containsKey(url)) return;
        mCipherClsMap.remove(url);
    }
}
