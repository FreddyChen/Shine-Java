package com.freddy.shine.java.retrofit.converter;

import androidx.annotation.Nullable;

import com.freddy.shine.java.config.NetworkConfig;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 自定义StringConverterFactory
 * <p>
 *
 * @author: FreddyChen
 * @date : 2022/01/14 15:56
 * @email : freddychencsc@gmail.com
 */
public class StringConverterFactory extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse(NetworkConfig.DEFAULT_CONTENT_TYPE);
    private static final String UTF_8 = "UTF-8";
    private static final int BUFFER_SIZE = 4096;

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type != String.class) {
            return null;
        }

        return (Converter<ResponseBody, String>) value -> getStringFrom(value);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (type != String.class) {
            return null;
        }

        return (Converter<String, RequestBody>) value -> RequestBody.Companion.create(value, MEDIA_TYPE);
    }

    private String getStringFrom(ResponseBody value) throws IOException {
        InputStream inputStream = value.byteStream();
        return getStringFrom(inputStream);
    }

    private String getStringFrom(InputStream inputStream) throws IOException {
        String result = null;
        try {
            if (inputStream != null) {
                result = writeStreamToString(inputStream);
            }
        } finally {
            closeQuietly(inputStream);
        }
        return result;
    }

    private String writeStreamToString(InputStream inputStream) throws IOException {
        String result;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            result = readStreamAndConvert(inputStream, outputStream);
        } finally {
            closeQuietly(outputStream);
        }

        return result;
    }

    private String readStreamAndConvert(InputStream inputStream, ByteArrayOutputStream outputStream) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        return outputStream.toString(UTF_8);
    }

    private void closeQuietly(Closeable stream) {
        try {
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
