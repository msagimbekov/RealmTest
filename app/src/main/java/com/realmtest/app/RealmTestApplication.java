package com.realmtest.app;

import android.app.Application;

import com.realmtest.helper.Counter;
import com.realmtest.util.AppUtil;

/**
 * Created by Madi on 20.03.2016.
 */
public class RealmTestApplication extends Application {

    public final static boolean AUTO_INCREMENT_DEFAULT_VALUE = false;
    public final static String APP_SHARED_PREFERENCE = "APP_SHARED_PREFERENCE";
    public final static String COUNTER_VALUE = "COUNTER_VALUE";
    public final static String AUTO_INCREMENT_VALUE = "AUTO_INCREMENT_VALUE";
    public final static int COUNTER_DEFAULT_VALUE = 0;
    public static boolean AUTO_INCREMENT = AUTO_INCREMENT_DEFAULT_VALUE;

    @Override
    public void onCreate() {
        super.onCreate();

        /*we get saved counter value, and assign it to newly created Counter object*/
        Counter.getInstance().setValue(AppUtil.getIntPreference(getApplicationContext(), COUNTER_VALUE, COUNTER_DEFAULT_VALUE));
    }

}
