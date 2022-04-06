package com.review.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author 张全
 */

public class TwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Two Activity");
    }
}
