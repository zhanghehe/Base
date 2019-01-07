package com.zhh.base.toolkit.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.zhh.base.toolkit.base.BaseApplication;


public class SpUtil {
    public static void putString(String key, String value) {
        SharedPreferences sp = BaseApplication.context.getSharedPreferences(Constants.COMPANY_FILE, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defStr) {
        SharedPreferences sp = BaseApplication.context.getSharedPreferences(Constants.COMPANY_FILE, Context.MODE_PRIVATE);
        return sp.getString(key, defStr);
    }

    public static void putBoolean(String key, boolean flag) {
        SharedPreferences sp = BaseApplication.context.getSharedPreferences(Constants.COMPANY_FILE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, flag).apply();
    }

    public static Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static Boolean getBoolean(String key, boolean defBoolean) {
        SharedPreferences sp = BaseApplication.context.getSharedPreferences(Constants.COMPANY_FILE, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defBoolean);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = BaseApplication.context.getSharedPreferences(Constants.COMPANY_FILE, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static Integer getInt(String key) {
        return getInt(key, 0);
    }

    public static Integer getInt(String key, int value) {
        SharedPreferences sp = BaseApplication.context.getSharedPreferences(Constants.COMPANY_FILE, Context.MODE_PRIVATE);
        return sp.getInt(key, value);
    }

    public static void clear() {
        //用来处理某些不需要退出清空的状态

        SharedPreferences sharedPreferences = BaseApplication.context.getSharedPreferences(Constants.COMPANY_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        //赋值
    }
}
