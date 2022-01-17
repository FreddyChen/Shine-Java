package com.freddy.shine.java.example;

import com.freddy.shine.java.model.IResponseModel;
import com.google.gson.annotations.SerializedName;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 00:50
 * @email : freddychencsc@gmail.com
 */
public class CustomResponseModel1<T> implements IResponseModel {

    @SerializedName("code")
    private String code;
    @SerializedName("day")
    private String day;
    @SerializedName("result")
    private T result;

    public String getCode() {
        return code;
    }

    public String getDay() {
        return day;
    }

    public T getResult() {
        return result;
    }

    @Override
    public boolean isSuccessful() {
        return "1".equals(this.code);
    }

    @Override
    public String toString() {
        return "CustomResponseModel1{" +
                "code='" + code + '\'' +
                ", day='" + day + '\'' +
                ", result=" + result +
                '}';
    }
}
