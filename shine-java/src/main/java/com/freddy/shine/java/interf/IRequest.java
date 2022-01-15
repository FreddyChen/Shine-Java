package com.freddy.shine.java.interf;

import com.freddy.shine.java.cipher.ICipher;
import com.freddy.shine.java.config.RequestOptions;
import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.listener.OnResponseListener;
import com.freddy.shine.java.parser.IParser;

import java.lang.reflect.Type;

/**
 * 抽象的接口请求封装，自定义RequestManager实现此接口即可
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 11:09
 * @email : freddychencsc@gmail.com
 */
public interface IRequest {

    <T> void request(RequestOptions options, Type type, Class<? extends IParser> parserCls, Class<? extends ICipher> cipherCls, OnResponseListener<T> listener);

    <T> T syncRequest(RequestOptions options, Type type, Class<? extends IParser> parserCls, Class<? extends ICipher> cipherCls) throws RequestException;
}
