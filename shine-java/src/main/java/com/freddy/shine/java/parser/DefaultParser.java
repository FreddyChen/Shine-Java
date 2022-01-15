package com.freddy.shine.java.parser;

import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.model.DefaultResponseModel;
import com.freddy.shine.java.utils.ShineLog;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * 默认数据解析器
 * <p>
 * ResponseModel包含<br />
 * code<br />
 * msg<br />
 * data<br />
 *
 * @author: FreddyChen
 * @date : 2022/01/14 10:43
 * @email : freddychencsc@gmail.com
 */
public class DefaultParser extends AbstractParser {

    private static final String TAG = "DefaultParser";

    @Override
    public <T> T parse(String url, String data, Type type) throws RequestException {
        ShineLog.i(TAG + "#parse()\ndata = " + data + "\ntype = " + type);
        String errMsg = null;
        DefaultResponseModel<T> responseModel = null;
        try {
            Type responseType = new TypeToken<DefaultResponseModel<T>>() {
            }.getType();
            responseModel = gson.fromJson(data, responseType);
            if (!responseModel.isSuccessful()) {
                errMsg = "responseModel is failure";
            } else {
                return gson.fromJson(gson.toJson(responseModel.getData()), type);
            }
        } catch (Exception e) {
            errMsg = e.getMessage();
        }

        RequestException exception = new RequestException();
        exception.setType(RequestException.Type.NATIVE);
        exception.setUrl(url);
        exception.setStatusCode(200);
        exception.setErrMsg(TAG + "#parse() failure\nerrMsg = " + errMsg + "\ntype = " + type + "\nresponseModel = " + responseModel + "\ndata = " + data);
        if (responseModel != null) {
            exception.setErrCode(responseModel.getCode());
        }
        throw exception;
    }
}
