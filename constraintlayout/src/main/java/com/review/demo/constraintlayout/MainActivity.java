package com.review.demo.constraintlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.btn);
        view.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("width="+view.getWidth()+",height="+view.getHeight());
            }
        });
    }
}