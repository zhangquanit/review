package com.review.performance.strictmodel.trace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;

import com.review.performance.strictmodel.R;

/**
 * 参考 TraceView.txt
 *
 * @author zhangquan
 */
public class TraceViewActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traceview);
        findViewById(R.id.btn_method).setOnClickListener(this);
        findViewById(R.id.btn_debugTrace).setOnClickListener(this);
        findViewById(R.id.btn_systrace).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_method: //profiler--cpu分析 或者DDMS-startMethodProfiling
                methodTrace();
                break;
            case R.id.btn_debugTrace: //生成trace文件，然后打开profiler--cpu 导入trace文件
                Debug.startMethodTracing("mytrace.trace");
                methodTrace();
                Debug.stopMethodTracing();
                break;
            case R.id.btn_systrace: //systrace分析
                startActivity(new Intent(this, SysTraceActivity.class));
                break;
        }
    }

    private void methodTrace() {
        Intent intent = new Intent(this, TraceViewActivity.class);
        methodA();
    }

    private void methodA() {
        String data = null;
        for (int i = 0; i < 2000; i++) {
            data += i;
            System.out.println("data=" + data);
        }
        methodB();
    }

    private void methodB() {

    }
}
