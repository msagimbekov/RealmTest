package com.realmtest.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.realmtest.app.RealmTestApplication;

/**
 * Created by Madi on 22.03.2016.
 */
public class AppUtil {

    public static int getIntPreference(Context context, String key, int defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(RealmTestApplication.APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    public static void setIntPreference(Context context, String key, Integer value) {
        SharedPreferences pref = context.getSharedPreferences(RealmTestApplication.APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setBooleanPreference(Context context, String key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(RealmTestApplication.APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBooleanPreference(Context context, String key, boolean defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(RealmTestApplication.APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

}
