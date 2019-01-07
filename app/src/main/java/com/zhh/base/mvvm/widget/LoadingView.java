package com.zhh.base.mvvm.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.zhh.base.R;

/**
 * Created by zhh on 2016/3/30.
 */
public class LoadingView extends Dialog {

    public LoadingView(Context context) {
        super(context, R.style.widget_loading_view_style);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_loading_view);
        initView();
    }

    private void initView() {
        setCanceledOnTouchOutside(false);
    }

}
