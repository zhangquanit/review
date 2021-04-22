package com.review.layout_optimize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //测试覆盖include中的id
        View view = findViewById(R.id.new_include_id);

        findViewById(R.id.btn_viewstub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewStub viewStub = findViewById(R.id.viewstub);
                if (null != viewStub) { //第一次渲染后，viewstub将从view树中移除
//                    viewStub.inflate();
                    viewStub.setVisibility(View.VISIBLE);
                }
            }
        });
        findViewById(R.id.btn_frameactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FrameLayoutActivity.class));
            }
        });
        findViewById(R.id.btn_fragactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FrameFragActivity.class));
            }
        });
    }
}