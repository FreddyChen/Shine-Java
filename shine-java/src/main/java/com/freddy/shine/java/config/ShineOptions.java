package com.freddy.shine.java.config;

import com.freddy.shine.java.parser.DefaultParser;
import com.freddy.shine.java.parser.IParser;

/**
 * Shine配置类
 * <p>
 * logEnable          日志开关<br>
 * logTag             日志Tag<br>
 * defaultBaseUrl     默认baseUrl<br>
 * defaultParserCls   默认数据解析器cls<br>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:38
 * @email : freddychencsc@gmail.com
 */
public class ShineOptions {

    private boolean logEnable;
    private String logTag;
    private String baseUrl;
    private Class<? extends IParser> parserCls;

    private ShineOptions(Builder builder) {
        this.logEnable = builder.logEnable;
        this.logTag = builder.logTag;
        this.baseUrl = builder.baseUrl;
        this.parserCls = builder.parserCls;
    }

    public boolean isLogEnable() {
        return logEnable;
    }

    public String getLogTag() {
        return logTag;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Class<? extends IParser> getParserCls() {
        return parserCls;
    }

    @Override
    public String toString() {
        return "ShineOptions{" +
                "logEnable=" + logEnable +
                ", logTag='" + logTag + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", parserCls=" + parserCls +
                '}';
    }

    public static class Builder {
        private boolean logEnable;
        private String logTag;
        private String baseUrl;
        private Class<? extends IParser> parserCls;

        public Builder() {
            this.logEnable = ShineConfig.DEFAULT_LOG_ENABLE;
            this.logTag = ShineConfig.DEFAULT_LOG_TAG;
            this.parserCls = DefaultParser.class;
        }

        public Builder setLogEnable(boolean logEnable) {
            this.logEnable = logEnable;
            return this;
        }

        public Builder setLogTag(String logTag) {
            this.logTag = logTag;
            return this;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setParserCls(Class<? extends IParser> parserCls) {
            this.parserCls = parserCls;
            return this;
        }

        public ShineOptions build() {
            return new ShineOptions(this);
        }
    }
}
