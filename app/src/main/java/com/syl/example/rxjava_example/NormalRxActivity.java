package com.syl.example.rxjava_example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by syl on 2017/6/10.
 */

public class NormalRxActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TAG";
    private TextView mText;
    private TextView mEdit;
    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        initView();
    }

    private void initView() {

        mText = (TextView) findViewById(R.id.text1);
        mEdit = (TextView) findViewById(R.id.edit1);
        mBtn = (Button) findViewById(R.id.button);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (mText.getText().toString().isEmpty() ) {
                    start();
                    Log.d(TAG, "onStart: ");
                } else {
                    mText.setText("");
                    Log.d(TAG, "onClick: ");
                }

        }
    }

    private void start() {
        Observable observable=createObservable();
        Subscriber subscriber = createSubscriber();
        mText.append("订阅了！！！\n");
        observable.subscribe(subscriber);
    }

    private Subscriber createSubscriber() {
        Subscriber subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mText.append("执行观察者中onCompleted()……\n");
                mText.append("执行完毕，结束观察……\n");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                mText.append("执行onNext...\n");
                mText.append(s+"……\n");

            }


        };
        return subscriber;
    }

    private Observable createObservable() {
        Observable observable=Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("一");
                subscriber.onNext("二");
                subscriber.onNext("三");
                subscriber.onNext("四");
                subscriber.onNext("五");
                subscriber.onCompleted();
            }
        });
        return observable;
    }
}
