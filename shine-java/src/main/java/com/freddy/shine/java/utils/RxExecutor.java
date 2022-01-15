package com.freddy.shine.java.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义RxJava线程池
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 16:48
 * @email : freddychencsc@gmail.com
 */
public class RxExecutor extends ThreadPoolExecutor {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();// CPU个数
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));// 线程池中核心线程的数量
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;// 线程池中最大线程数量
    private static final long KEEP_ALIVE_TIME = 30L;// 非核心线程的超时时长，当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收。如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
    private static final int WAIT_COUNT = 128; // 最多排队个数，这里控制线程创建的频率

    public RxExecutor() {
        super(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(WAIT_COUNT));
    }

    private static final class SingletonHolder {
        private static final RxExecutor INSTANCE = new RxExecutor();
    }

    public static RxExecutor getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
