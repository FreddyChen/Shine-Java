package com.freddy.shine.java.example;

import com.google.gson.annotations.SerializedName;

/**
 * @author: FreddyChen
 * @date : 2022/01/17 16:33
 * @email : freddychencsc@gmail.com
 */
public class History {

    @SerializedName("date")
    private String date;
    @SerializedName("title")
    private String title;

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "History{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
