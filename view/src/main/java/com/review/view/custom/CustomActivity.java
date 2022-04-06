package com.review.view.custom;

import android.app.Activity;
import android.customview.R;
import android.os.Bundle;

/**
 * @author 张全
 */

public class CustomActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
    }
}
