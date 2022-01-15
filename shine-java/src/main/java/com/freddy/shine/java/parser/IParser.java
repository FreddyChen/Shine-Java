package com.freddy.shine.java.parser;

import com.freddy.shine.java.exception.RequestException;

import java.lang.reflect.Type;

/**
 * 数据解析器抽象接口
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:42
 * @email : freddychencsc@gmail.com
 * @see DefaultParser <br />
 */
public interface IParser {

    <T> T parse(String url, String data, Type type) throws RequestException;
}
