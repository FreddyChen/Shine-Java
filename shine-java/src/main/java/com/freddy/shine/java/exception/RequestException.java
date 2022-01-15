package com.freddy.shine.java.exception;

/**
 * 封装的请求异常
 * <p>
 * * {@link #type}          异常类型<br />
 * * {@link #url}           接口地址(baseUrl+function)<br />
 * * {@link #statusCode}    http状态码<br />
 * * {@link #errCode}       业务错误码<br />
 * * {@link #errMsg}        业务错误信息<br />
 * * {@link #errBody}       错误详细信息<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 11:23
 * @email : freddychencsc@gmail.com
 */
public class RequestException extends Throwable {

    /**
     * 异常类型
     * <p>
     * {@link #NATIVE}  本地异常
     * {@link #NETWORK} 网络异常
     */
    public enum Type {
        NATIVE,
        NETWORK
    }

    private Type type;
    private String url;
    private Integer statusCode;
    private Integer errCode;
    private String errMsg;
    private String errBody;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrBody() {
        return errBody;
    }

    public void setErrBody(String errBody) {
        this.errBody = errBody;
    }

    @Override
    public String toString() {
        return "RequestException{" +
                "type=" + type +
                ", url='" + url + '\'' +
                ", statusCode=" + statusCode +
                ", errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
