package com.syl.example.rxjava_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TAG";
    private Observable observable2;
    Integer[] a = {1, 2, 34, 5};
    private Button clickMeBN;
    private TextView resultTV;
    private Subscriber<MovieEntity> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = (TextView) findViewById(R.id.result_TV);
        clickMeBN = (Button) findViewById(R.id.click_me_BN);
    clickMeBN.setOnClickListener(this);
//        Observable observable=Observable.create(new Observable.OnSubscribe<Integer>() {
//
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (Integer integer : a) {
//                    subscriber.onNext(integer);
//                }
//            }
//        });
////    Observable observable1=Observable.from(new ArrayList<String>)
//        observable2 = observable.map(new Func1<Integer,String>() {
//            @Override
//            public String call(Integer integer) {
//                return integer.toString();
//            }
//        });
//        observable2.filter(new Func1<String,Boolean>() {
//
//            @Override
//            public Boolean call(String s) {
//                return null;
//            }
//        });
//        observable2.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//
//
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click_me_BN:
                getMovie();
                break;
        }
    }

    private void getMovie() {
        String baseUrl = "http://api.douban.com/v2/movie/";
        Retrofit retrofit =new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
//        Call<MovieEntity> call = movieService.getTopMoive(0, 10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                resultTV.setText(response.body().getSubjects().get(0).getOriginal_title());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });
//
//        movieService.getTopMoive(0,10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(MainActivity.this, "Get finish", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity movieEntity) {
//                        resultTV.setText(movieEntity.toString());
//                    }
//                });

        subscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                resultTV.setText(movieEntity.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber,0,10);
    }
}
