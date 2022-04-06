package com.review.datastorage.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author 张全
 */

public class SPUtil {
    private static final String PREFERENCE_NAME = "SP";

    private static SharedPreferences getSP(Context c) {
        SharedPreferences sh = c.getSharedPreferences(PREFERENCE_NAME, Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE | Context.MODE_MULTI_PROCESS);
        return sh;
    }
    public static void setString(Context ctx,String key,String value){
        getSP(ctx).edit().putString(key,value).commit();
    }
    public static String getString(Context ctx,String key){
       return  getSP(ctx).getString(key,null);
    }

}
