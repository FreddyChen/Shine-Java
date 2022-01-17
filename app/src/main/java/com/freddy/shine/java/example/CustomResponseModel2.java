package com.freddy.shine.java.example;

import com.freddy.shine.java.model.IResponseModel;
import com.google.gson.annotations.SerializedName;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 00:50
 * @email : freddychencsc@gmail.com
 */
public class CustomResponseModel2<T> implements IResponseModel {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean isSuccessful() {
        return "success".equals(this.message);
    }

    @Override
    public String toString() {
        return "CustomResponseModel2{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
