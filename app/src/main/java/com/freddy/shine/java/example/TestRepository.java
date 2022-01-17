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
     * 获取历史列表数据
     * 异步请求
     *
     * @param listener
     */
    public void fetchHistoryList(OnResponseListener<List<History>> listener) {
        RequestOptions.Builder builder = getDefaultRequestOptionsBuilder(RequestMethod.GET, "lishi/api.php");
        request(builder.build(), getListType(History.class), CustomParser1.class, null, listener);
    }

    /**
     * 获取新闻列表数据
     * 同步请求
     *
     * @return
     * @throws RequestException
     */
    public List<Journalism> fetchJournalismList() throws RequestException {
        RequestOptions options = new RequestOptions.Builder()
                .setRequestMethod(RequestMethod.GET)
                .setBaseUrl("http://is.snssdk.com/")
                .setFunction("api/news/feed/v51/")
                .build();
        return syncRequest(options, getListType(Journalism.class), CustomParser2.class, null);
    }
}
