package com.freddy.shine.java.example;

import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.parser.AbstractParser;
import com.freddy.shine.java.utils.ShineLog;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 00:48
 * @email : freddychencsc@gmail.com
 */
public class CustomParser2 extends AbstractParser {

    private static final String TAG = "CustomParser1";

    @Override
    public <T> T parse(String url, String data, Type type) throws RequestException {
        ShineLog.i(TAG + "#parse()\ndata = " + data + "\ntype = " + type);
        String errMsg = null;
        CustomResponseModel2<T> responseModel = null;
        try {
            Type responseType = new TypeToken<CustomResponseModel2<T>>() {
            }.getType();
            responseModel = gson.fromJson(data, responseType);
            if (!responseModel.isSuccessful()) {
                errMsg = "responseModel is failure";
            } else {
                return gson.fromJson(gson.toJson(responseModel.getData()), type);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }

        RequestException exception = new RequestException();
        exception.setType(RequestException.Type.NATIVE);
        exception.setUrl(url);
        exception.setStatusCode(200);
        exception.setErrMsg(TAG + "#parse() failure\nerrMsg = " + errMsg + "\ntype = " + type + "\nresponseModel = " + responseModel + "\ndata = " + data);

        throw exception;
    }
}