package com.freddy.shine.java.config;

import android.util.ArrayMap;

/**
 * 请求配置
 * <p>
 * requestMethod 请求方式<br>
 * baseUrl baseUrl<br>
 * function 接口地址<br>
 * headers 请求头<br>
 * params 请求参数<br>
 * contentType contentType<br>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:22
 * @email : freddychencsc@gmail.com
 */
public class RequestOptions {

    private RequestMethod requestMethod;
    private String baseUrl;
    private String function;
    private ArrayMap<String, Object> headers;
    private ArrayMap<String, Object> params;
    private String contentType;

    private RequestOptions(Builder builder) {
        this.requestMethod = builder.requestMethod;
        this.baseUrl = builder.baseUrl;
        this.function = builder.function;
        this.headers = builder.headers;
        this.params = builder.params;
        this.contentType = builder.contentType;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getFunction() {
        return function;
    }

    public ArrayMap<String, Object> getHeaders() {
        return headers;
    }

    public ArrayMap<String, Object> getParams() {
        return params;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "RequestOptions{" +
                "requestMethod=" + requestMethod +
                ", baseUrl='" + baseUrl + '\'' +
                ", function='" + function + '\'' +
                ", headers=" + headers +
                ", params=" + params +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    public static class Builder {
        private RequestMethod requestMethod;
        private String baseUrl;
        private String function;
        private ArrayMap<String, Object> headers;
        private ArrayMap<String, Object> params;
        private String contentType;

        public Builder() {
            this.requestMethod = RequestMethod.GET;
            this.contentType = NetworkConfig.DEFAULT_CONTENT_TYPE;
        }

        public Builder setRequestMethod(RequestMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setFunction(String function) {
            this.function = function;
            return this;
        }

        public Builder setHeaders(ArrayMap<String, Object> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setParams(ArrayMap<String, Object> params) {
            this.params = params;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(this);
        }
    }
}
