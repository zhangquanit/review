package com.review.performance.strictmodel.viewServer;

import android.app.Activity;
import android.os.Bundle;

import com.review.performance.strictmodel.R;

/**
 * Hierarchy Viewer需要Root的机器(产品机没有开启ViewServer)才可以执行.
 * 可以使用第三方的开源的ViewServer来协助我们在未Root的机器上使用Hierarchy Viewer分析.
 * @author zhangquan
 */
public class ViewServerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewserver);
        ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
}
