package com.freddy.shine.java.example;

import com.google.gson.annotations.SerializedName;

/**
 * @author: FreddyChen
 * @date : 2022/01/17 16:32
 * @email : freddychencsc@gmail.com
 */
public class Journalism {

    @SerializedName("code")
    private String code;
    @SerializedName("content")
    private String content;

    public String getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Journalism{" +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
