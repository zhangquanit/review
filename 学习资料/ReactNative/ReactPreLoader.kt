package net.medlinker.medlinker.reactnative

import android.content.Context
import com.facebook.react.ReactApplication
import com.facebook.react.ReactRootView


/**
 * @author hmy
 * @time 2020/6/11 13:45
 */
class ReactPreLoader {

    companion object {

        private var mIsPreLoaded = false

        @JvmStatic
        fun preLoad(context: Context, componentName: String) {
            if (mIsPreLoaded) {
                return
            }
            mIsPreLoaded = true
            val reactRootView = ReactRootView(context)
            reactRootView.startReactApplication(
                    (context as ReactApplication).reactNativeHost.reactInstanceManager,
                    componentName,
                    null)
        }
    }
}