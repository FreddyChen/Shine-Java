package com.freddy.shine.java.listener;

import com.freddy.shine.java.exception.RequestException;

/**
 * 网络请求响应回调适配器
 * <p>
 * 可以按需实现接口回调方法<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 11:35
 * @email : freddychencsc@gmail.com
 */
public abstract class ResponseListAdapter<T> implements OnResponseListener<T> {

    @Override
    public void onStart() {

    }

    @Override
    public void onSuccessful(T result) {

    }

    @Override
    public void onFailure(RequestException exception) {

    }

    @Override
    public void onFinish() {

    }
}
