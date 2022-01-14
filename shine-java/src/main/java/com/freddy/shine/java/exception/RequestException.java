package com.freddy.shine.java.exception;

/**
 * 封装的请求异常
 * <p>
 * * [type]         异常类型
 * * [statusCode]   http状态码
 * * [errCode]      业务错误码
 * * [errMsg]       业务错误信息
 *
 * @author: FreddyChen
 * @date : 2022/01/14 11:23
 * @email : freddychencsc@gmail.com
 */
public class RequestException extends Throwable {

    public enum Type {
        NATIVE,
        NETWORK
    }

    private Type type;
    private int statusCode;
    private Integer errCode;
    private String errMsg;

    public RequestException(String errMsg) {
        this(Type.NATIVE, 200, 0xfc1, errMsg);
    }

    public RequestException(int errCode, String errMsg) {
        this(Type.NATIVE, 200, errCode, errMsg);
    }

    public RequestException(int statusCode, int errCode, String errMsg) {
        this(Type.NATIVE, statusCode, errCode, errMsg);
    }

    public RequestException(Type type, String errMsg) {
        this(type, 200, 0xfc1, errMsg);
    }

    public RequestException(Type type, int errCode, String errMsg) {
        this(type, 200, errCode, errMsg);
    }

    public RequestException(Type type, int statusCode, int errCode, String errMsg) {
        super(errMsg);
        this.type = type;
        this.statusCode = statusCode;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "RequestException{" +
                "type=" + type +
                ", statusCode=" + statusCode +
                ", errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
