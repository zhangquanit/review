package com.review.asyncloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.review.asyncloader.asynctask.AsyncTaskActivity;
import com.review.asyncloader.loader.LoaderActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_loader).setOnClickListener(this);
        findViewById(R.id.btn_async).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loader:
                startActivity(new Intent(this, LoaderActivity.class));
                break;
            case R.id.btn_async:
                startActivity(new Intent(this, AsyncTaskActivity.class));
                break;
        }
    }
}
