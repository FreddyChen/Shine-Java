package com.freddy.shine.java.model;

/**
 * 默认的ResponseModel
 * <p>
 * {@link #code} 返回码<br />
 * {@link #msg}  返回信息<br />
 * {@link #data} 返回数据<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:51
 * @email : freddychencsc@gmail.com
 */
public class DefaultResponseModel<T> implements IResponseModel {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean isSuccessful() {
        return this.code == 0;
    }

    @Override
    public String toString() {
        return "DefaultResponseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
