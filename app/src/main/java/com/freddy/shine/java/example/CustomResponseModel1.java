package com.freddy.shine.java.example;

import com.freddy.shine.java.model.IResponseModel;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 00:50
 * @email : freddychencsc@gmail.com
 */
public class CustomResponseModel1<T> implements IResponseModel {

    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean isSuccessful() {
        return this.errorCode == 0;
    }

    @Override
    public String toString() {
        return "CustomResponseModel1{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
