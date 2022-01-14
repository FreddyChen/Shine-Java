package com.freddy.shine.java.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.freddy.shine.java.ShineKit;
import com.freddy.shine.java.config.ShineOptions;
import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.listener.OnResponseListener;
import com.freddy.shine.java.listener.ResponseListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShineOptions options = new ShineOptions.Builder()
                .setLogEnable(true)
                .setLogTag("FreddyChen")
                .setBaseUrl("https://www.wanandroid.com/")
                .setParserCls(CustomParser1.class)
                .build();
        ShineKit.getInstance().init(options);

        TestRepository repository = new TestRepository();
        repository.fetchArticleList(new OnResponseListener<ArticleList>() {

            @Override
            public void onStart() {
                Log.i(TAG, "fetchArticleList() onStart");
            }

            @Override
            public void onSuccessful(ArticleList result) {
                Log.i(TAG, "fetchArticleList() onSuccessful result = " + result);
            }

            @Override
            public void onFailure(RequestException exception) {
                Log.i(TAG, "fetchArticleList() onFailure exception = " + exception);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "fetchArticleList() onFinish");
            }
        });

        repository.fetchCatList(new ResponseListAdapter<List<Cat>>() {

            @Override
            public void onSuccessful(List<Cat> result) {
                Log.i(TAG, "fetchCatList() onSuccessful result = " + result);
            }

            @Override
            public void onFailure(RequestException exception) {
                Log.i(TAG, "fetchCatList() onFailure exception = " + exception);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "fetchCatList() onFinish");
            }
        });
    }
}