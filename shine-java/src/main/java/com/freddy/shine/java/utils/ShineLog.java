package com.freddy.shine.java.utils;

import android.util.Log;

import com.freddy.shine.java.ShineKit;

/**
 * Shine日志工具类
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:54
 * @email : freddychencsc@gmail.com
 */
public class ShineLog {

    public static void v(String log) {
        ShineLog.v(ShineKit.getInstance().getShineOptions().getLogTag(), log);
    }

    public static void v(String tag, String log) {
        if (!ShineKit.getInstance().getShineOptions().isLogEnable()) return;
        Log.v(tag, log);
    }

    public static void d(String log) {
        ShineLog.d(ShineKit.getInstance().getShineOptions().getLogTag(), log);
    }

    public static void d(String tag, String log) {
        if (!ShineKit.getInstance().getShineOptions().isLogEnable()) return;
        Log.d(tag, log);
    }

    public static void i(String log) {
        ShineLog.i(ShineKit.getInstance().getShineOptions().getLogTag(), log);
    }

    public static void i(String tag, String log) {
        if (!ShineKit.getInstance().getShineOptions().isLogEnable()) return;
        Log.i(tag, log);
    }

    public static void w(String log) {
        ShineLog.w(ShineKit.getInstance().getShineOptions().getLogTag(), log);
    }

    public static void w(String tag, String log) {
        if (!ShineKit.getInstance().getShineOptions().isLogEnable()) return;
        Log.w(tag, log);
    }

    public static void e(String log) {
        ShineLog.e(ShineKit.getInstance().getShineOptions().getLogTag(), log);
    }

    public static void e(String tag, String log) {
        if (!ShineKit.getInstance().getShineOptions().isLogEnable()) return;
        Log.e(tag, log);
    }
}
