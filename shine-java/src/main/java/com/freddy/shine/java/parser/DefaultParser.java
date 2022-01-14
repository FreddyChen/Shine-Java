package com.freddy.shine.java.parser;

import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.model.DefaultResponseModel;
import com.freddy.shine.java.utils.ShineLog;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author: FreddyChen
 * @date : 2022/01/14 10:43
 * @email : freddychencsc@gmail.com
 */
public class DefaultParser extends AbstractParser {

    private static final String TAG = "DefaultParser";

    @Override
    public <T> T parse(String data, Type type) throws RequestException {
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

        if (responseModel == null) {
            throw new RequestException(
                    RequestException.Type.NATIVE,
                    TAG + "#parse() failure\nerrMsg = " + errMsg + "\ntype = " + type + "\nresponseModel = " + responseModel + "\ndata = " + data
            );
        } else {
            throw new RequestException(
                    RequestException.Type.NATIVE,
                    responseModel.getCode(),
                    TAG + "#parse() failure\nerrMsg = " + errMsg + "\ntype = " + type + "\nresponseModel = " + responseModel + "\ndata = " + data
            );
        }
    }
}
