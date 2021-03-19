package com.review.layout_optimize;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 根布局为FrameLayout，可以使用merge标签减少一层级
 *
 * @author zhangquan
 */
public class FrameLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

    }
}
