package com.medlinker.demo.leakcanary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityTwo that = ActivityTwo.this;
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ActivityTwo 子线程执行完毕");
            }
        }).start();
    }

}