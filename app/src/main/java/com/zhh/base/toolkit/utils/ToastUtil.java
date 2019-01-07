package com.zhh.base.toolkit.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.zhh.base.toolkit.base.BaseApplication;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/9/19
 * Change:
 */
public class ToastUtil {
    private static Toast toast;

    public static void toast(@NonNull String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (null == toast) {
            toast = Toast.makeText(BaseApplication.context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void cancel() {
        if (null != toast) toast.cancel();
    }
}
