package com.freddy.shine.java.example;

import java.util.List;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 01:33
 * @email : freddychencsc@gmail.com
 */
public class ArticleList {

    private int curPage;
    private List<Article> datas;

    public int getCurPage() {
        return curPage;
    }

    public List<Article> getDatas() {
        return datas;
    }

    @Override
    public String toString() {
        return "ArticleList{" +
                "curPage=" + curPage +
                ", datas=" + datas +
                '}';
    }
}
