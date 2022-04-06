package net.medlinker.medlinker.reactnative;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactDelegate;
import com.facebook.react.ReactRootView;
import com.medlinker.base.utils.StringUtil;
import com.medlinker.lib.utils.MedStringUtil;

import net.medlinker.medlinker.common.track.TrackManager;
import net.medlinker.medlinker.reactnative.view.ReactRootLayout;

import java.lang.reflect.Field;

import javax.annotation.Nullable;

import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_ANGEL_QA;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_CS_HOME;
import static net.medlinker.medlinker.reactnative.ModuleConfig.RN_PAGE_TS_HOME;

/**
 * 便于传值到react
 *
 * @author jiantao
 * @date 2017/12/7
 */

public class MedReactActivityDelegate extends ReactActivityDelegate {

    private Bundle bundle;
    private ReactRootLayout mReactRootLayout;
    private ReactDelegate mReactDelegateNew;

    public MedReactActivityDelegate(ReactActivity fragmentActivity, @Nullable String mainComponentName) {
        super(fragmentActivity, mainComponentName);
    }

    @Nullable
    @Override
    protected Bundle getLaunchOptions() {
        return bundle;
    }

    public void setLaunchOptions(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Class<?> clazz = ReactActivityDelegate.class;
        String mainComponentName = getMainComponentName();
        try {
            Field reactDelegateField = clazz.getDeclaredField("mReactDelegate");
            reactDelegateField.setAccessible(true);
            reactDelegateField.set(this, mReactDelegateNew =
                    new ReactDelegate(getPlainActivity(), getReactNativeHost(), mainComponentName, getLaunchOptions()) {

                        @Override
                        public void loadApp(String appKey) {
                            Class<?> clazz = ReactDelegate.class;
                            try {
                                Field reactRootViewField = clazz.getDeclaredField("mReactRootView");
                                reactRootViewField.setAccessible(true);
                                mReactRootLayout = new ReactRootLayout(getContext());
                                ReactRootView reactRootView = mReactRootLayout.getReactRootView();
                                reactRootView.startReactApplication(
                                        getReactNativeHost().getReactInstanceManager(),
                                        appKey,
                                        getLaunchOptions());
                                reactRootViewField.set(this, reactRootView);
                                reactRootViewField.setAccessible(false);
                            } catch (Exception ignored) {
                            }
                        }
                    });
            reactDelegateField.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getMainComponentName() != null) {
            loadApp(mainComponentName);
        }

        TrackManager.getInstance().onJumpPage(getPageName());
    }

    @Override
    protected void loadApp(String appKey) {
        mReactDelegateNew.loadApp(appKey);
        getPlainActivity().setContentView(mReactRootLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPageName() != null) {
            TrackManager.getInstance().onResumePage(getPageName());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getPageName() != null) {
            TrackManager.getInstance().onPausePage(getPageName());
        }
    }

    public String getPageName() {
        String routeName = "react-native-page";
        if (bundle != null) {
            routeName = bundle.getString("routeName");
        }
        switch (routeName) {
            case RN_PAGE_CS_HOME:
                routeName = StringUtil.buildPage("myCustomerServices");
                break;
            case RN_PAGE_TS_HOME:
                routeName = StringUtil.buildPage("spaceTimeList");
                break;
            case RN_PAGE_ANGEL_QA:
                routeName = StringUtil.buildPage("answerindex");
                break;
            default:
                routeName = StringUtil.buildPage(routeName);
                break;
        }
        return routeName;
    }

}
