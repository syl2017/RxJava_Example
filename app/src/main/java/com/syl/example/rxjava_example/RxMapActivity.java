package com.syl.example.rxjava_example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by syl on 2017/6/11.
 */

public class RxMapActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mtext;
    private Button mbtn;
    private TextView medit;
    private Integer [] number={1,2,3,4,5,6};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        initView();

    }

    private void initView() {
        mtext = (TextView) findViewById(R.id.text1);
        mbtn = (Button) findViewById(R.id.button);
        medit = (TextView) findViewById(R.id.edit1);
        mbtn.setText("判断数组中的小于三的数");
        medit.setText("输入Integer(int): 1....6\n"+"\n"+"输出：type：true/false \n");
        mbtn.setOnClickListener(this);
        mtext.setOnClickListener(this);
        medit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (mtext.getText().toString().isEmpty()) {
                    start();
                } else {
                    mtext.setText("");
                }
                break;
        }
    }

    private void start() {
        mtext.append("\n输入参数： 1,2,3,4,5,6\n");
        Observable.from(number).map(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                mtext.append("\n\n map() Integer -->   ");
                return (integer<3);
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mtext.append("\n\n观察到结果");
                mtext.append(aBoolean.toString());
            }
        });
    }
}
