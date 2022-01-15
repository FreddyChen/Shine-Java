package com.freddy.shine.java.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 用于通过Class<?>获取Type
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/15 02:58
 * @email : freddychencsc@gmail.com
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private final Class<?> raw;
    private final Type[] args;

    public ParameterizedTypeImpl(Class<?> raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @NonNull
    @Override
    public Type getRawType() {
        return raw;
    }

    @Nullable
    @Override
    public Type getOwnerType() {
        return null;
    }
}
