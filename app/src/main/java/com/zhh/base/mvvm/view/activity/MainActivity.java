package com.zhh.base.mvvm.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhh.base.R;
import com.zhh.base.databinding.ActivityMainBinding;
import com.zhh.base.toolkit.base.BaseBindingActivity;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
