package com.freddy.shine.java.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.freddy.shine.java.ShineKit;
import com.freddy.shine.java.config.ShineOptions;
import com.freddy.shine.java.exception.RequestException;
import com.freddy.shine.java.listener.ResponseListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private View btn1, btn2;

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

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);

        btn1.setOnClickListener(v -> {
            Log.i(TAG, "异步请求开始");
            repository.fetchArticleList(new ResponseListAdapter<ArticleList>() {

                @Override
                public void onStart() {

                    Log.i(TAG, "fetchArticleList onStart()");
                }

                @Override
                public void onSuccessful(ArticleList result) {
                    Log.i(TAG, "fetchArticleList onSuccessful() result = " + result);
                }

                @Override
                public void onFailure(RequestException exception) {
                    Log.i(TAG, "fetchArticleList onFailure() exception = " + exception);
                }

                @Override
                public void onFinish() {
                    Log.i(TAG, "fetchArticleList onFinish()");
                }
            });
            Log.i(TAG, "异步请求结束");
        });

        btn2.setOnClickListener(v -> new Thread(() -> {
            Log.i(TAG, "同步请求开始");
            try {
                List<Cat> catList = repository.fetchCatList();
                Log.i(TAG, "catList = " + catList);
            } catch (RequestException exception) {
                exception.printStackTrace();
            }
            Log.i(TAG, "同步请求结束");
        }).start());
    }
}