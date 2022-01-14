package com.freddy.shine.java.retrofit.manager;

import android.text.TextUtils;
import android.util.ArrayMap;

import com.freddy.shine.java.AbstractRequestManager;
import com.freddy.shine.java.cipher.ICipher;
import com.freddy.shine.java.config.NetworkConfig;
import com.freddy.shine.java.config.RequestMethod;
import com.freddy.shine.java.config.RequestOptions;
import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.listener.OnResponseListener;
import com.freddy.shine.java.parser.IParser;
import com.freddy.shine.java.retrofit.api.IApiService;
import com.freddy.shine.java.utils.RxExecutor;
import com.freddy.shine.java.utils.ShineLog;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author: FreddyChen
 * @date : 2022/01/14 11:53
 * @email : freddychencsc@gmail.com
 */
public class RetrofitRequestManager extends AbstractRequestManager {

    private RetrofitRequestManager() {
    }

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

        IApiService apiService = RetrofitManager.getInstance().getRetrofit(baseUrl).create(IApiService.class);

        ArrayMap<String, Object> headers = options.getHeaders();
        RetrofitManager.getInstance().saveHeaders(baseUrl + function, headers);

        if (cipherCls != null) {
            RetrofitManager.getInstance().saveCipher(baseUrl + function, cipherCls);
        }

        Observable<String> observable = null;
        ArrayMap<String, Object> params = options.getParams();
        String contentType = options.getContentType();
        RequestMethod requestMethod = options.getRequestMethod();
        switch (requestMethod) {
            case GET: {
                if (params == null || params.isEmpty()) {
                    observable = apiService.get(function);
                } else {
                    observable = apiService.get(function, params);
                }
                break;
            }
            case POST: {
                if (params == null || params.isEmpty()) {
                    observable = apiService.post(function);
                } else {
                    observable = apiService.post(function, convertParamsToRequestBody(params, contentType));
                }
                break;
            }
            case PUT: {
                if (params == null || params.isEmpty()) {
                    observable = apiService.put(function);
                } else {
                    observable = apiService.put(function, convertParamsToRequestBody(params, contentType));
                }
                break;
            }
            case DELETE: {
                if (params == null || params.isEmpty()) {
                    observable = apiService.delete(function);
                } else {
                    observable = apiService.delete(function, params);
                }
                break;
            }
        }

        if (observable == null) {
            if (listener != null) {
                listener.onFailure(new RequestException(RequestException.Type.NETWORK, "request failure, reason: observable is null"));
                listener.onFinish();
            }
            return;
        }

        observable.subscribeOn(Schedulers.from(RxExecutor.getInstance()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    private volatile Disposable mDisposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        this.mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        try {
                            T result = parse(s, type, parserCls);
                            if (listener != null) {
                                listener.onSuccessful(result);
                            }
                            onComplete();
                        } catch (RequestException e) {
                            onError(e);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onError(new RequestException(RequestException.Type.NATIVE, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        if (mDisposable != null && !mDisposable.isDisposed()) {
                            mDisposable.dispose();
                        }
                        this.mDisposable = null;

                        if (listener != null) {
                            listener.onFinish();
                        }
                    }

                    private void onError(RequestException e) {
                        if (listener != null) {
                            listener.onFailure(e);
                        }
                        onComplete();
                    }
                });
    }

    @Override
    public <T> void syncRequest(RequestOptions options, Type type, Class<? extends IParser> parserCls, Class<? extends ICipher> cipherCls, OnResponseListener<T> listener) {

    }

    /**
     * 将请求参数转换到RequestBody
     * POST/PUT请求适用
     */
    private RequestBody convertParamsToRequestBody(ArrayMap<String, Object> params, String contentType) {
        return RequestBody.Companion.create(gson.toJson(params), MediaType.parse(contentType));
    }
}
