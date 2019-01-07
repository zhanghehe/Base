package com.zhh.base.mvvm.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zhh.base.R;
import com.zhh.base.databinding.StateNoDataBinding;
import com.zhh.base.databinding.StateErrorBinding;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/9/26
 * Change:
 */
public class StateLayout extends FrameLayout {
    private LayoutInflater mInflater;
    private View mLoadingView;

    private View mErrorView;

    private View mEmptyView;

    public StateLayout(@NonNull Context context) {
        this(context, null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (mInflater == null) mInflater = LayoutInflater.from(context);
    }

    public void setContentView() {
        if (mEmptyView != null) mEmptyView.setVisibility(GONE);
        if (mErrorView != null) mErrorView.setVisibility(GONE);
        if (mLoadingView != null) mLoadingView.setVisibility(GONE);
    }

    public void showLoadingView() {
        if (mLoadingView == null) {
            mLoadingView = mInflater.inflate(R.layout.state_loading, null);
            addView(mLoadingView);
        }
        mLoadingView.setVisibility(VISIBLE);
        if (mEmptyView != null) mEmptyView.setVisibility(GONE);
        if (mErrorView != null) mErrorView.setVisibility(GONE);
    }

    public void hideLoadingView() {
        if (mLoadingView != null) mLoadingView.setVisibility(GONE);
    }

    public void setEmptyView() {
        setEmptyView(R.mipmap.ic_launcher, "暂无数据", "", null);
    }

    public void setEmptyView(@DrawableRes int res, String msg) {
        setEmptyView(res, msg, "", null);
    }

    public void setEmptyView(@DrawableRes int res, String msg, String btnText, OnClickListener clickListener) {
        if (mEmptyView == null) {
            mEmptyView = mInflater.inflate(R.layout.state_no_data, null);
            StateNoDataBinding binding = DataBindingUtil.bind(mEmptyView);
            binding.setImageResource(res);
            binding.setDes(msg);
            if (!TextUtils.isEmpty(btnText)) {
                binding.setBtnText(btnText);
                binding.setBtnVisible(true);
                binding.btnNext.setOnClickListener(clickListener);
            } else {
                binding.setBtnVisible(false);
            }
            addView(mEmptyView);
        }
        mEmptyView.setVisibility(VISIBLE);
        if (mErrorView != null) mErrorView.setVisibility(GONE);
        if (mLoadingView != null) mLoadingView.setVisibility(GONE);
    }

    public void setErrorView() {
        setErrorView(null);
    }

    public void setErrorView(OnClickListener clickListener) {
        if (mErrorView == null) {
            mErrorView = mInflater.inflate(R.layout.state_error, null);
            StateErrorBinding binding = DataBindingUtil.bind(mErrorView);
            if (clickListener == null) {
                binding.tvRefresh.setVisibility(GONE);
            } else {
                binding.tvRefresh.setVisibility(VISIBLE);
                binding.cl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(v);
                        setContentView();
                    }
                });
            }
            addView(mErrorView);
        }
        mErrorView.setVisibility(VISIBLE);
        if (mEmptyView != null) mEmptyView.setVisibility(GONE);
        if (mLoadingView != null) mLoadingView.setVisibility(GONE);
    }


}
