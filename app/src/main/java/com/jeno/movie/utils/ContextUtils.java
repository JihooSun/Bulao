package com.jeno.movie.utils;

import android.content.Context;

import com.jeno.movie.R;

public class ContextUtils {

    private static Context applicationContext;

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(Context applicationContext) {
        synchronized (ContextUtils.class) {
            ContextUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        return Integer.parseInt(applicationContext.getString(R.string.versionCode));
    }

    public static String getVersionName() {
        return applicationContext.getString(R.string.versionName);
    }

}
