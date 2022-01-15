package com.freddy.shine.java;

import com.freddy.shine.java.interf.IRequest;
import com.freddy.shine.java.retrofit.manager.RetrofitRequestManager;

/**
 * RequestManager工厂，提供获取RequestManager方法，应用层直接调用{@link #getRequestManager()}即可，无需关心内部实现逻辑
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 12:14
 * @email : freddychencsc@gmail.com
 */
public class RequestManagerFactory {

    public static IRequest getRequestManager() {
        return RetrofitRequestManager.getInstance();
    }
}
