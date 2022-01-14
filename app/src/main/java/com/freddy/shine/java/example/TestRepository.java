package com.freddy.shine.java.example;

import com.freddy.shine.java.config.RequestMethod;
import com.freddy.shine.java.config.RequestOptions;
import com.freddy.shine.java.listener.OnResponseListener;

import java.util.List;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 00:56
 * @email : freddychencsc@gmail.com
 */
public class TestRepository extends BaseRepository {

    public void fetchArticleList(OnResponseListener<ArticleList> listener) {
        RequestOptions.Builder builder = getDefaultRequestOptionsBuilder(RequestMethod.GET, "article/list/0/json");
        request(builder.build(), getType(ArticleList.class), listener);
    }

    public void fetchCatList(OnResponseListener<List<Cat>> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setRequestMethod(RequestMethod.GET)
                .setBaseUrl("https://cat-fact.herokuapp.com/")
                .setFunction("facts/random?amount=2&animal_type=cat")
                .build();
        request(options, getListType(Cat.class), CustomParser2.class, null, listener);
    }
}
