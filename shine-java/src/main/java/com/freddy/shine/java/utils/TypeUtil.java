package com.freddy.shine.java.utils;

import java.lang.reflect.Type;

/**
 * Type工具类
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/15 03:24
 * @email : freddychencsc@gmail.com
 */
public class TypeUtil {

    public static <T> Type getType(Class<?> rawType, Class<T> clazz) {
        return new ParameterizedTypeImpl(rawType, new Class[]{clazz});
    }
}
