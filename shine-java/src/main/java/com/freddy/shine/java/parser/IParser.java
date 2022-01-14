package com.freddy.shine.java.parser;

import com.freddy.shine.java.exception.RequestException;

import java.lang.reflect.Type;

/**
 * @author: FreddyChen
 * @date : 2022/01/14 10:42
 * @email : freddychencsc@gmail.com
 */
public interface IParser {

    <T> T parse(String data, Type type) throws RequestException;
}
