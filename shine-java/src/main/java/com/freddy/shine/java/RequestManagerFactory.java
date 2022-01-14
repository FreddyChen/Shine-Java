package com.freddy.shine.java;

import com.freddy.shine.java.interf.IRequest;
import com.freddy.shine.java.retrofit.manager.RetrofitRequestManager;

/**
 * @author: FreddyChen
 * @date : 2022/01/14 12:14
 * @email : freddychencsc@gmail.com
 */
public class RequestManagerFactory {

    public static IRequest getRequestManager() {
        return RetrofitRequestManager.getInstance();
    }
}
