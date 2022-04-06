package com.review.performance.strictmodel.strictmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.review.performance.strictmodel.R;
import com.review.performance.strictmodel.memoryleak.MemoryLeakActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * @author zhangquan
 */
public class StrictModelActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strictmodel);
        findViewById(R.id.btn_rw).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
        findViewById(R.id.btn_leak).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rw: //主线程读写
                File file = new File(getFilesDir(), "detect.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write("文字内容".getBytes());
                    fos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_time:  //耗时统计
                MyTaskExecutor.executeTest();
                break;
            case R.id.btn_leak: //内存泄露
                startActivity(new Intent(this, MemoryLeakActivity.class));
                break;
        }
    }
}
