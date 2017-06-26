package com.syl.example.rxjava_example;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by syl on 2017/6/26.
 */

public class HttpMethods {

    private static final int DEFAULT_TIMEOUT =5 ;
    private static final String BASE_URL ="https://api.douban.com/v2/movie/";
    private final MovieService movieService;

    public HttpMethods() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        movieService = retrofit.create(MovieService.class);

    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();

    }

    public static HttpMethods getInstance(){


        return SingletonHolder.INSTANCE;
    }

    public void getTopMovie(Subscriber<MovieEntity> subscriber, int start, int count) {

        movieService.getTopMoive(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
