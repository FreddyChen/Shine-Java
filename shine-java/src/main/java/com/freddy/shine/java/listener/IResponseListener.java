package com.freddy.shine.java.listener;

import com.freddy.shine.java.exception.RequestException;

/**
 * 网路请求响应回调
 * <p>
 * {@link #onStart()}                   请求开始<br />
 * {@link #onSuccessful(T)}}            请求成功<br />
 * {@link #onFailure(RequestException)} 请求失败<br />
 * {@link #onFinish()}                  请求完成<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 11:22
 * @email : freddychencsc@gmail.com
 */
public interface IResponseListener<T> {

    void onStart();

    void onSuccessful(T result);

    void onFailure(RequestException exception);

    void onFinish();
}
