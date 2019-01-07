package com.zhh.base.toolkit.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhanghehe on 2018/11/1.
 * desc:
 */
public abstract class BaseBindingActivity<B extends ViewDataBinding> extends AppCompatActivity {
    protected B binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    public abstract int getLayoutId();

}
