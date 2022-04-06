package net.medlinker.medlinker.reactnative;

import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_COMPONENT_ANGEL_QA;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_COMPONENT_CS;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_COMPONENT_DEFAULT;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_COMPONENT_IR;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_COMPONENT_MISSIONCENTER;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_COMPONENT_TS;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_ANGEL_QA;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_CS_MYNAMECARD;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_IR_MULTIPOINT_WORKRECORD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;

import androidx.annotation.StringDef;
import androidx.fragment.app.Fragment;

import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.medlinker.analytics.MedAnalytics;
import com.medlinker.lib.utils.MedAppInfo;
import com.medlinker.lib.utils.MedImmersiveModeUtil;
import com.medlinker.reactnative.codepush.RNCodePush;
import com.medrn.base.BaseMedRNActivity;

import net.medlinker.medlinker.R;
import net.medlinker.medlinker.app.MedlinkerApp;
import net.medlinker.medlinker.businese.detail.RewardViewHelper;
import net.medlinker.medlinker.utils.AccountUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jiantao
 * @date 2017/11/30
 */

public class MedlinkerReactActivity extends BaseMedRNActivity {

    public static String mComponentName = RN_COMPONENT_DEFAULT; //默认主模块
    public static String mPageName = "";
    static Object sExtraData = null;
    static String mAnalyticsFrom = null;

    private String mCurModuleName;
    private String mCurPageName;
    private String mExtra;
    private boolean mIsAppearing = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        RewardViewHelper.dispatchTouchEvent(this, ev);
        return super.dispatchTouchEvent(ev);
    }

    public String getCurModuleName() {
        return mCurModuleName;
    }

    public String getCurPageName() {
        return mCurPageName;
    }

    /**
     *
     */
    @StringDef({RN_COMPONENT_CS, RN_COMPONENT_IR, RN_COMPONENT_TS, RN_COMPONENT_DEFAULT, RN_COMPONENT_MISSIONCENTER, RN_COMPONENT_ANGEL_QA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RnModuleName {
    }


    /**
     * 主模块
     */
    @Deprecated
    public static void launchCSReactActivity(Context context, String rnPageName, Bundle bundle, int requestCode) {
        launchReactActivity(context, RN_COMPONENT_CS, rnPageName, bundle, requestCode);
    }

    /**
     *
     */
    public static void launchCSReactActivity(Context context, String rnPageName, Bundle bundle) {
        launchCSReactActivity(context, rnPageName, bundle, 0);
    }

    /**
     * 互联网备案模块
     */
    @Deprecated
    public static void launchIRReactActivity(Context context, String rnPageName, Bundle bundle, int requestCode) {
        launchReactActivity(context, RN_COMPONENT_IR, rnPageName, bundle, requestCode);
    }

    /**
     *
     */
    public static void launchIRReactActivity(Context context, String rnPageName, Bundle bundle) {
        launchIRReactActivity(context, rnPageName, bundle, 0);
    }

    /**
     * 时空模块
     */
    @Deprecated
    public static void launchTSReactActivity(Context context, String rnPageName, Bundle bundle, int requestCode) {
        launchReactActivity(context, RN_COMPONENT_TS, rnPageName, bundle, requestCode);
    }

    /**
     *
     */
    public static void launchTSReactActivity(Context context, String rnPageName, Bundle bundle) {
        launchTSReactActivity(context, rnPageName, bundle, 0);
    }

    /**
     * 天使答人模块
     */
    @Deprecated
    public static void launchAQAReactActivity(Context context, String rnPageName, Bundle bundle, int requestCode) {
        launchReactActivity(context, RN_COMPONENT_ANGEL_QA, rnPageName, bundle, requestCode);
    }

    /**
     * 任务系统模块
     */
    @Deprecated
    public static void launchMCReactActivity(Context context, String rnPageName, Bundle bundle, int requestCode) {
        launchReactActivity(context, RN_COMPONENT_MISSIONCENTER, rnPageName, bundle, requestCode);
    }

    /**
     *
     */
    public static void launchMCReactActivity(Context context, String rnPageName, Bundle bundle) {
        launchMCReactActivity(context, rnPageName, bundle, 0);
    }

    /**
     *
     */
    public static void launchReactActivity(Context context, @RnModuleName String rnModuleName,
                                           String rnPageName, Object extra, int requestCode) {
        launchReactActivity(context, rnModuleName, rnPageName, extra, null, requestCode);
    }

    /**
     *
     */
    public static void launchReactActivity(Context context, @RnModuleName String rnModuleName,
                                           String rnPageName, Object extra, String analyticsFrom, int requestCode) {
        mComponentName = rnModuleName;
        mPageName = rnPageName;
        if (extra != null) {
            sExtraData = extra;
        }
        mAnalyticsFrom = analyticsFrom;
        if (context == null || ((context instanceof Activity) && ((Activity) context).isFinishing())) {
            return;
        }
        if (hasPermission(context, rnPageName)) {
            Intent intent = new Intent(context, MedlinkerReactActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            Bundle optionBundle = new Bundle();
            optionBundle.putString("moduleName", rnModuleName);
            optionBundle.putString("routeName", rnPageName);
            if (extra instanceof Bundle) {
                optionBundle.putBundle("extra", (Bundle) extra);
            } else if (extra instanceof String) {
                optionBundle.putString("extra", (String) extra);
            }
            if (!TextUtils.isEmpty(analyticsFrom)) {
                optionBundle.putString("analyticsFrom", analyticsFrom);
            }
            intent.putExtras(optionBundle);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, requestCode);
            } else {
                context.startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TextUtils.isEmpty(mPageName)) {
            //被回收时恢复，关闭页面
            finish();
            return;
        }
        configImmersiveMode();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        mCurModuleName = extras.getString("moduleName");
        mCurPageName = extras.getString("routeName");
        try {
            if (MedAppInfo.isDebug() && extras.containsKey("extra")) {
                mExtra = extras.getString("extra");
                if (TextUtils.isEmpty(mExtra)) {
                    Bundle extraBundle = extras.getBundle("extra");
                    if (null != extraBundle) {
                        Set<String> keySet = extraBundle.keySet();
                        Map<String, Object> values = new HashMap<>();
                        for (String key : keySet) {
                            values.put(key, extraBundle.get(key));
                        }
                        if (!values.isEmpty()) {
                            mExtra = values.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (RN_PAGE_ANGEL_QA.equals(mCurPageName)) {
            overridePendingTransition(R.anim.activity_push_bottom_in_enter, R.anim.activity_push_bottom_in_exit);
        }
        MedAnalytics.set("setActivityPageName", this, String.format("RN_%s_%s", mCurModuleName, mCurPageName));
    }

    private void configImmersiveMode() {
        // 成功设置为darkmode后才支持透明状态栏模式
        // RN页面特殊处理： >= 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MedImmersiveModeUtil.setStatusBarTransparent(this);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mIsAppearing = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsAppearing) { //如果恢复状态，通知RN界面回到前台，不放在onRestart是避免微信等分享 “留在微信” 回调onRestart
            WritableMap params = new WritableNativeMap();
            params.putString("moduleName", mCurModuleName);
            params.putString("routeName", mCurPageName);
            ReactNativeEventHelper.setEvent(ReactNativeEventHelper.EVENT_KEY_PAGE_WILL_APPEAR, params);
            mIsAppearing = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (MedlinkerApp.getApplication().isAppForeground()) { //非HOME操作通知
            try {
                //通知RN界面进入后台
                WritableMap params = new WritableNativeMap();
                params.putString("moduleName", mCurModuleName);
                params.putString("routeName", mCurPageName);
                ReactNativeEventHelper.setEvent(ReactNativeEventHelper.EVENT_KEY_PAGE_WILL_DISAPPEAR, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (RN_PAGE_ANGEL_QA.equals(mCurPageName)) {
            overridePendingTransition(R.anim.activity_push_bottom_out_enter, R.anim.activity_push_bottom_out_exit);
        }
    }

    // Workaround appcompat-1.1.0 bug https://issuetracker.google.com/issues/141132133
    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 25) {
            return;
        }
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Intent intent = new Intent("onConfigurationChanged");
        intent.putExtra("newConfig", newConfig);
        this.sendBroadcast(intent);
    }

    /**
     * rn 界面，处理用户权限
     */
    private static boolean hasPermission(Context context, String routeName) {
        boolean hasPer = true;
        switch (routeName) {
            case RN_PAGE_CS_MYNAMECARD:
                hasPer = AccountUtil.checkCertifyUser(context);
                break;
            case RN_PAGE_IR_MULTIPOINT_WORKRECORD:
                hasPer = AccountUtil.checkVisitor(context);
                break;
            default:
                break;
        }
        return hasPer;
    }

    @Override
    protected String getMainComponentName() {
        return mComponentName;
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        String pageName = mPageName;
        Object extra = sExtraData;
        MedReactActivityDelegate delegate = new MedReactActivityDelegate(this, getMainComponentName());
        Bundle optionBundle = new Bundle();
        optionBundle.putString("moduleName", mComponentName);
        optionBundle.putString("routeName", pageName);
        if (extra instanceof Bundle) {
            optionBundle.putBundle("extra", (Bundle) extra);
        } else if (extra instanceof String) {
            optionBundle.putString("extra", (String) extra);
        }
        if (!TextUtils.isEmpty(mAnalyticsFrom)) {
            optionBundle.putString("analyticsFrom", mAnalyticsFrom);
        }
        optionBundle.putString("pageId", getPageId());
        delegate.setLaunchOptions(optionBundle);
        return delegate;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RNCodePush.getInstance().removeCurrentFailedUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sExtraData = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //解决RN页面无法在Fragment中申请权限回调
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (null == fragments || fragments.isEmpty()) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
}
