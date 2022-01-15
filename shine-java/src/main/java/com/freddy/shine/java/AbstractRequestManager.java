package com.freddy.shine.java;

import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.interf.IRequest;
import com.freddy.shine.java.parser.IParser;
import com.freddy.shine.java.utils.ShineLog;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RequestManager抽象类，自定义RequestManager需继承此类
 *
 * @author: FreddyChen
 * @date : 2022/01/14 11:54
 * @email : freddychencsc@gmail.com
 * @see com.freddy.shine.java.retrofit.manager.RetrofitRequestManager
 */
public abstract class AbstractRequestManager implements IRequest {

    protected Gson gson = new Gson();

    private final ConcurrentHashMap<Class<? extends IParser>, IParser> mParserMap = new ConcurrentHashMap<>();

    /**
     * 解析数据
     */
    protected <T> T parse(String url, String data, Type type, Class<? extends IParser> parserCls) throws RequestException {
        ShineLog.i(getClass().getSimpleName(), "#parse()\ndata = " + data + "\ntype = " + type + "\nparserCls = " + parserCls);
        try {
            return Objects.requireNonNull(getParser(url, parserCls)).parse(url, data, type);
        } catch (Exception e) {
            RequestException exception = new RequestException();
            exception.setType(RequestException.Type.NATIVE);
            exception.setUrl(url);
            exception.setStatusCode(200);
            exception.setErrMsg(getClass().getSimpleName() + "#parse failure\nreason: " + e.getMessage());
            throw exception;
        }
    }

    /**
     * 获取Parser
     */
    private IParser getParser(String url, Class<? extends IParser> parserCls) throws RequestException {
        ShineLog.i(getClass().getSimpleName() + "#getParser()\nparserCls = " + parserCls + "\nmParserMap = " + mParserMap);
        Class<? extends IParser> cls = parserCls != null ? parserCls : ShineKit.getInstance().getShineOptions().getParserCls();
        if (cls == null) return null;
        IParser parser;
        if (mParserMap.containsKey(cls)) {
            parser = mParserMap.get(cls);
        } else {
            try {
                parser = (IParser) Class.forName(cls.getName()).newInstance();
            } catch (Exception e) {
                RequestException exception = new RequestException();
                exception.setType(RequestException.Type.NATIVE);
                exception.setUrl(url);
                exception.setStatusCode(200);
                exception.setErrMsg(getClass().getSimpleName() + "#getParser failure\nreason: " + e.getMessage());
                throw exception;
            }
        }
        if (parser == null) return null;
        mParserMap.put(cls, parser);
        return parser;
    }
}
