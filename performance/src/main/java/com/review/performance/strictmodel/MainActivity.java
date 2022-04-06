package com.review.performance.strictmodel;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.review.performance.strictmodel.memoryleak.MemoryLeakActivity;
import com.review.performance.strictmodel.trace.TraceViewActivity;
import com.review.performance.strictmodel.viewServer.ViewServerActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tv_memoryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_memoryInfo = (TextView) findViewById(R.id.tv_memory);
        findViewById(R.id.tv_trace).setOnClickListener(this);
        findViewById(R.id.tv_viewServer).setOnClickListener(this);
        findViewById(R.id.tv_leak).setOnClickListener(this);

        memoryInfo();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_trace:
                startActivity(new Intent(this, TraceViewActivity.class));
                break;
            case R.id.tv_viewServer:
                startActivity(new Intent(this, ViewServerActivity.class));
                break;
            case R.id.tv_leak:
                startActivity(new Intent(this, MemoryLeakActivity.class));
                break;
        }
    }

    private void memoryInfo() {
        StringBuffer infoBuilder = new StringBuffer();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        int largeMemoryClass = activityManager.getLargeMemoryClass();
        infoBuilder.append("memoryClass=" + memoryClass).append("MB\n");
        infoBuilder.append("largeMemoryClass=" + largeMemoryClass).append("MB\n");


        long freeMemory = Runtime.getRuntime().freeMemory();  //APP已申请内存中剩余的大小，当内存不足时，会继续申请
        long totalMemory = Runtime.getRuntime().totalMemory(); //APP当前已申请的内存大小
        long maxMemory = Runtime.getRuntime().maxMemory(); //一般等价于memoryClass

        infoBuilder.append("freeMemory=" + Formatter.formatFileSize(this, freeMemory)).append("\n");
        infoBuilder.append("totalMemory=" + Formatter.formatFileSize(this, totalMemory)).append("\n");
        infoBuilder.append("maxMemory=" + Formatter.formatFileSize(this, maxMemory)).append("\n");

        tv_memoryInfo.setText(infoBuilder.toString());
    }


    @Override
    protected void onResume() {
        super.onResume();
        memoryInfo();

    }


}
