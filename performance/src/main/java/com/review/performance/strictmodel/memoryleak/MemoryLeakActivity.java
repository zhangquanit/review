package com.review.performance.strictmodel.memoryleak;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import com.review.performance.strictmodel.App;
import com.review.performance.strictmodel.R;

/**
 * 内存泄漏
 * 1、Profiler---Memory分析
 * 来回切换页面 不断增长的内存
 * dump heap 分许内存泄露所在类
 *
 * 2、DDMS-dump heap
 * 打开DDMS...Devices,选择某个进程....点击update heap按钮...在右边的heap面板，点击cause GC。
 * 来回切换页面，点击cause GC，查看内存是否正常回收。
 * Dump HPROF File，使用MAT分析内存泄露所在类
 * @author 张全
 */

public class MemoryLeakActivity extends Activity {
    TextView textView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        textView = findViewById(R.id.textview);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        App.leakActivitys.add(this);//引用当前activity 导致无法回收


//        String data = null;
//        for (int i = 0; i < 1000; i++) {
//            data = data + "->i";
//        }

        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程开始休眠");
                    try {
                        Thread.sleep(10 * 1000);
                        boolean b = textView != null;
                        if(null!=bitmap){
                            Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程停止休眠");
                }
            }).start();
        }

    }
}
