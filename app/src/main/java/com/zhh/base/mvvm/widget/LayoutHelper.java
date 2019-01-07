package com.zhh.base.mvvm.widget;

import android.databinding.BindingAdapter;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/10/26
 * Change:
 */
public class LayoutHelper {
    @BindingAdapter({"background"})
    public static void background(View v, int resId) {
        v.setBackgroundColor(resId);
    }

    @BindingAdapter({"background"})
    public static void setAdapterBG(ConstraintLayout constraintLayout, int color) {
        constraintLayout.setBackgroundColor(color);
    }


    @BindingAdapter({"qtwText"})
    public static void setQtwText(TextView textView, String s) {
        textView.setText(s);
    }

    @BindingAdapter({"qtwText"})
    public static void setQtwText(TextView textView, @StringRes int resId) {
        textView.setText(resId);
    }

    @BindingAdapter({"progress"})
    public static void setProgress(ProgressBar progressBar, String progress) {
        if (TextUtils.isEmpty(progress)) progress = "0";
        int p = Integer.parseInt(progress);
        progressBar.setProgress(p);
    }

    @BindingAdapter({"rechargeStatus"})
    public static void setRechargeStatusText(TextView textView, String status) {
        if ("0".equals(status)) {
            textView.setText("进行中");
        } else if ("1".equals(status)) {
            textView.setText("充值成功");
        } else if ("2".equals(status)) {
            textView.setText("充值失败");
        }
    }

    @BindingAdapter({"withdrawStatus"})
    public static void setWithdrawStatusText(TextView textView, String status) {
        if ("0".equals(status)) {
            textView.setText("进行中");
        } else if ("1".equals(status)) {
            textView.setText("提现成功");
        } else if ("2".equals(status)) {
            textView.setText("提现失败");
        }
    }

    @BindingAdapter({"annualReturn"})
    public static void setViewVisible(View view, String value) {
        if (TextUtils.isEmpty(value)) {
            view.setVisibility(View.GONE);
        } else {
            if ("0".equals(value) || "0.00".equals(value)) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
}
