package com.syl.example.rxjava_example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by syl on 2017/6/13.
 */

public class RxFlatMapActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView medit;
    private Button mbtn;
    private TextView mtext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        initView();
        initData();
    }

    private SchoolClass[] mSchoolClasses = new SchoolClass[2];
    private void initData() {
        Student[] student = new Student[5];
        for (int i = 0; i < 5; i++) {
            Student s=new Student(i+"     ",1+"");
            student[i]=s;
        }
        mSchoolClasses[0]=new SchoolClass(student);
        Student[] student2 = new Student[5];
        for (int i = 0; i < 5; i++) {
            Student s = new Student("小" + i + "   ", 2 + "");
            student2[i]=s;
        }
        mSchoolClasses[1] = new SchoolClass(student2);
    }

    private void initView() {

        mtext = (TextView) findViewById(R.id.text1);
        mbtn = (Button) findViewById(R.id.button);
        medit = (TextView) findViewById(R.id.edit1);
        mtext.setText("打印一个学校的所有班级所有学生姓名");
        mbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (mtext.getText().toString().isEmpty()) {
                    mtext.setText("");

                }
                start();
                break;
        }
    }

    public SchoolClass[] getSchoolClass() {
        return mSchoolClasses;
    }
    private void start() {
        Observable.from(getSchoolClass())
                .flatMap(new Func1<SchoolClass, Observable<Student>>() {
            @Override
            public Observable<Student> call(SchoolClass schoolClass) {
                return Observable.from(schoolClass.getStudents());
            }
        })
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        mtext.append("打印单个学生信息：\n");
                        mtext.append("name:"+student.name+"    age: "+student.age+"\n");

                    }
                });
    }
    }

    class SchoolClass{
        Student[] std;

        public SchoolClass(Student[] std) {
            this.std = std;
        }

        public Student[] getStudents() {
            return std;
        }
    }
    class Student {
        String name;
        String age;

        public Student(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }

