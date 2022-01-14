package com.freddy.shine.java;

import com.freddy.shine.java.config.ShineOptions;
import com.freddy.shine.java.interf.IRequest;

/**
 * Shine核心类
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:56
 * @email : freddychencsc@gmail.com
 */
public class ShineKit {

    private ShineKit() {
        options = new ShineOptions.Builder().build();
    }

    public static ShineKit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ShineKit INSTANCE = new ShineKit();
    }

    private ShineOptions options;

    public void init(ShineOptions options) {
        this.options = options;
    }

    public ShineOptions getShineOptions() {
        return this.options;
    }

    public IRequest getRequestManager() {
        return RequestManagerFactory.getRequestManager();
    }
}
