package com.zhh.base.toolkit.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Create by alex on 2019/1/7
 * desc:
 */
public class BaseApplication extends Application {
    public static Context context;
    /**
     * 这里会在  onCreate 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }
}
