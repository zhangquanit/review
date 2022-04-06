package net.medlinker.medlinker.reactnative;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * react-native events tools
 *
 * @author jiantao
 * @date 2018/1/9
 */

public class ReactNativeEventHelper {
    /**
     * 更新用户信息
     */
    public static final String EVENT_KEY_UPDATE_USERINFO = "updateUserInfo";
    /**
     * 用户登录
     */
    public static final String EVENT_KEY_USER_LOGIN = "newUserLogin";
    /**
     * 用户注销
     */
    public static final String EVENT_KEY_USER_LOGOUT = "userLogout";
    /**
     * 更新消息
     */
    public static final String EVENT_KEY_TS_MESSAGE = "timeSpaceMessage";
    /**
     * Fragment可见
     */
    public static final String EVENT_KEY_FRAGMENT_WILL_APPEAR = "RNFragmentWillAppear";
    /**
     * Fragment不可见
     */
    public static final String EVENT_KEY_FRAGMENT_WILL_DISAPPEAR = "RNFragmentWillDisappear";
    /**
     * /**
     * 页面回到前台
     */
    public static final String EVENT_KEY_PAGE_WILL_APPEAR = "RNPageWillAppear";
    /**
     * 页面进入后台
     */
    public static final String EVENT_KEY_PAGE_WILL_DISAPPEAR = "RNPageWillDisappear";
    /**
     * 发布患教、更新患教成功
     */
    public static final String EVENT_KEY_PATIENT_MATERIAL = "publishOrUpdatePatientMaterial";

    /**
     * 开药门诊，退回订单池
     */
    public static final String EVENT_KEY_TRADE_REMOVE = "tradeRemove";

    /**
     * 加入小组
     */
    public static final String EVENT_KEY_JOIN_GROUP = "joinGroup";


    /**
     * native 发送事件通知react js
     *
     * @param eventName
     * @param params
     */
    public static void setEvent(String eventName, @Nullable WritableMap params) {
        WritableMap paramsCloneMap = null;
        if (params != null) {
            paramsCloneMap = new WritableNativeMap();
            paramsCloneMap.merge(params);
        }
        final ReactApplicationContext reactContext = CommonReactPackage.getReactApplicationContext();
        if (reactContext != null && reactContext.hasActiveCatalystInstance()) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
            // clean code后，通知用同一个
            WritableMap realMap = new WritableNativeMap();
            realMap.putString("type", eventName);
            realMap.putMap("data", paramsCloneMap);
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("MEDRN_BUSINESS_EVENT", realMap);
        }
    }
}
