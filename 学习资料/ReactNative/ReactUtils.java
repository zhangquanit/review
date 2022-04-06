package net.medlinker.medlinker.reactnative;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * @author jiantao
 * @date 2018/7/25
 */
public class ReactUtils {

    public static WritableArray convertJsonArrayToWritable(JSONArray jsonArr) {
        WritableArray arr = Arguments.createArray();
        for (int i = 0; i < jsonArr.length(); i++) {
            Object obj = null;
            try {
                obj = jsonArr.get(i);
            } catch (JSONException jsonException) {
                // Should not happen.
                throw new RuntimeException(i + " should be within bounds of array " + jsonArr.toString(), jsonException);
            }

            if (obj instanceof JSONObject)
                arr.pushMap(convertJsonObjectToWritable((JSONObject) obj));
            else if (obj instanceof JSONArray)
                arr.pushArray(convertJsonArrayToWritable((JSONArray) obj));
            else if (obj instanceof String)
                arr.pushString((String) obj);
            else if (obj instanceof Double)
                arr.pushDouble((Double) obj);
            else if (obj instanceof Integer)
                arr.pushInt((Integer) obj);
            else if (obj instanceof Boolean)
                arr.pushBoolean((Boolean) obj);
            else if (obj instanceof Long)
                arr.pushString((String) obj);
            else if (obj == null)
                arr.pushNull();
            else
                throw new RuntimeException("Unrecognized object: " + obj);
        }

        return arr;
    }

    public static WritableMap convertJsonObjectToWritable(JSONObject jsonObj) {
        WritableMap map = Arguments.createMap();
        Iterator<String> it = jsonObj.keys();
        while (it.hasNext()) {
            String key = it.next();
            Object obj = null;
            try {
                obj = jsonObj.get(key);
            } catch (JSONException jsonException) {
                // Should not happen.
                throw new RuntimeException("Key " + key + " should exist in " + jsonObj.toString() + ".", jsonException);
            }

            if (obj instanceof JSONObject)
                map.putMap(key, convertJsonObjectToWritable((JSONObject) obj));
            else if (obj instanceof JSONArray)
                map.putArray(key, convertJsonArrayToWritable((JSONArray) obj));
            else if (obj instanceof String)
                map.putString(key, (String) obj);
            else if (obj instanceof Double)
                map.putDouble(key, (Double) obj);
            else if (obj instanceof Integer)
                map.putInt(key, (Integer) obj);
            else if (obj instanceof Boolean)
                map.putBoolean(key, (Boolean) obj);
            else if (obj instanceof Long)
                map.putString(key, (String) obj);
            else if (obj == null)
                map.putNull(key);
            else
                throw new RuntimeException("Unrecognized object: " + obj);
        }

        return map;
    }

}
