package com.freddy.shine.java.example;

import com.freddy.shine.java.config.RequestMethod;
import com.freddy.shine.java.config.RequestOptions;
import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.listener.OnResponseListener;

import java.util.List;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 00:56
 * @email : freddychencsc@gmail.com
 */
public class TestRepository extends BaseRepository {

    /**
     * 获取文章列表数据
     * 异步请求
     *
     * @param listener
     */
    public void fetchArticleList(OnResponseListener<ArticleList> listener) {
        RequestOptions.Builder builder = getDefaultRequestOptionsBuilder(RequestMethod.GET, "article/list/0/json");
        request(builder.build(), getType(ArticleList.class), CustomParser1.class, null, listener);
    }

    /**
     * 获取Cat数据
     * 同步请求
     *
     * @return
     * @throws RequestException
     */
    public List<Cat> fetchCatList() throws RequestException {
        RequestOptions options = new RequestOptions.Builder()
                .setRequestMethod(RequestMethod.GET)
                .setBaseUrl("https://cat-fact.herokuapp.com/")
                .setFunction("facts/random?amount=2&animal_type=cat")
                .build();
        return syncRequest(options, getListType(Cat.class), CustomParser2.class, null);
    }
}
