package net.medlinker.medlinker.reactnative;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import net.medlinker.medlinker.reactnative.modules.CommonRTCModule;
import net.medlinker.medlinker.reactnative.modules.MedBusinessModule;
import net.medlinker.medlinker.reactnative.modules.ReactNotificationModule;
import net.medlinker.medlinker.reactnative.uicomponents.animationImage.RNAnimationImageViewManager;
import net.medlinker.medlinker.reactnative.uicomponents.richeditor.ReactRichEditorViewManager;
import net.medlinker.medlinker.reactnative.uicomponents.swipeRefresh.ReactSwipeRefreshLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiantao
 * @date 2017/12/5
 */

public class CommonReactPackage implements ReactPackage {

    /**
     * cache ReactApplicationContext 用于notify react js
     */
    private static ReactApplicationContext reactApplicationContext;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        reactApplicationContext = reactContext;
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new CommonRTCModule(reactContext));
        modules.add(new ReactNotificationModule(reactContext));
        modules.add(new MedBusinessModule(reactContext));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new ReactSwipeRefreshLayoutManager(),
                new RNAnimationImageViewManager(),
                new ReactRichEditorViewManager(reactContext)
        );
    }

    static ReactApplicationContext getReactApplicationContext() {
        return reactApplicationContext;
    }
}
