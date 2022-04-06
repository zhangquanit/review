package com.review.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_changeIcon).setOnClickListener(this);
        findViewById(R.id.btn_changeOtherIcon).setOnClickListener(this);
        findViewById(R.id.btn_configchange).setOnClickListener(this);

        //获取当前Component
        ComponentName component = getIntent().getComponent();
        System.out.println("component="+component);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_changeIcon:
                changeIcon("com.review.activity.MainActivity");
                break;
            case R.id.btn_changeOtherIcon:
                changeIcon("com.review.activity.MainActivityAlias");
                break;
            case R.id.btn_configchange:
                startActivity(new Intent(this,ConfigChangeActivity.class));
                break;
        }
    }

    /**
     * 注意，修改之后，需要稍等片刻才能看到变化。
     * 如果想在修改完成之后立即看到变化，只能通过 Intent 重启 Launcher 应用
     * @param activityPath
     */
    public void changeIcon(String activityPath) {
        PackageManager pm = getPackageManager();
        //将当前组件设为disabled
        ComponentName curComponentName = getComponentName();//ComponentInfo{com.review.activity/com.review.activity.MainActivity}
        System.out.println("getComponentName="+getComponentName());
        pm.setComponentEnabledSetting(curComponentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        //启用其他组件
        pm.setComponentEnabledSetting(new ComponentName(this, activityPath),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        /**
         * 重启桌面 加速显示
         * 注意：某些机型上，当系统的桌面检测到我们的app的icon跟上一次不一样的时候，我们再次点击我们的app会报一个错误
         */
//        restartSystemLauncher(pm);
    }
    public void restartSystemLauncher(PackageManager pm) {
        ActivityManager am = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = pm.queryIntentActivities(i, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
    }
}
