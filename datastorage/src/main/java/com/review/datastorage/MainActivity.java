package com.review.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.review.datastorage.contentprovider.ProviderActivity;
import com.review.datastorage.db.DBActivity;
import com.review.datastorage.file.FileActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_sp).setOnClickListener(this);
        findViewById(R.id.btn_file).setOnClickListener(this);
        findViewById(R.id.btn_db).setOnClickListener(this);
        findViewById(R.id.btn_provider).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sp:
                break;
            case R.id.btn_file:
                startActivity(new Intent(this, FileActivity.class));
                break;
            case R.id.btn_db:
                startActivity(new Intent(this, DBActivity.class));
                break;
            case R.id.btn_provider:
                startActivity(new Intent(this, ProviderActivity.class));
                break;
        }
    }
}
