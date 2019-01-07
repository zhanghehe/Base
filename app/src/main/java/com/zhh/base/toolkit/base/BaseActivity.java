package com.zhh.base.toolkit.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhh.base.R;
import com.zhh.base.mvvm.view.activity.MainActivity;
import com.zhh.base.mvvm.widget.LoadingView;
import com.zhh.base.mvvm.widget.StateLayout;
import com.zhh.base.toolkit.common.event.MessageEvent;
import com.zhh.base.toolkit.utils.Constants;
import com.zhh.base.toolkit.utils.Density;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by zhanghehe on 2018/11/1.
 * desc:
 */
public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity implements ISupportActivity, IView {
    protected B binding;
    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);
    public BaseActivity context;
    private SmartRefreshLayout srl;
    private StateLayout slRoot;
    protected RxPermissions permissions;
    protected boolean intercept;
    private LoadingView loadingView;
    protected boolean pullToRefresh = true;

    protected ImageView ivBack;
    protected TextView tvTitle;
    protected TextView tvRightLarge;
    protected TextView tvRightSmall;
    protected ImageView ivRight;

    private void initTitle() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        tvRightLarge = findViewById(R.id.tv_right_large);
        tvRightSmall = findViewById(R.id.tv_right_small);
        ivRight = findViewById(R.id.iv_right);
    }

    public void back(View v) {
        onBackPressedSupport();
    }

    public void setRightLargeText(CharSequence c) {
        tvRightLarge.setVisibility(View.VISIBLE);
        tvRightLarge.setText(c);
    }

    public void setRightLargeClickLinsener(View.OnClickListener clickLinsener) {
        tvRightLarge.setOnClickListener(clickLinsener);
    }

    public void setRightSmallText(CharSequence c) {
        tvRightSmall.setVisibility(View.VISIBLE);
        tvRightSmall.setText(c);
    }

    public void setRightSmallClickLinsener(View.OnClickListener clickLinsener) {
        tvRightSmall.setOnClickListener(clickLinsener);
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }


    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDelegate.onCreate(savedInstanceState);
        Density.setCustomDensity(this, getApplication());
        EventBus.getDefault().register(this);
        context = this;
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        permissions = new RxPermissions(this);

        if (null == loadingView)
            loadingView = new LoadingView(this);
        initTitle();
        if (null == slRoot) {
            slRoot = findViewById(R.id.sl_root);
        }
        if (null == srl) {
            srl = findViewById(R.id.srl);
        }
        initView();
        initData();
        if (null != srl) {
            if (!intercept)
                srl.autoRefresh();
        }
        if (null != tvTitle) {
            tvTitle.setText(getTitle());
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    /****************************************以下为可选方法(Optional methods)******************************************************/

    // 选择性拓展其他方法
    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadMultipleRootFragment(int containerId, int showPosition, @NonNull ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * Pop the fragment.
     */
    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * icon_back stack.
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }

    public void showHideFragment(ISupportFragment showFragment, ISupportFragment preFragment) {
        mDelegate.showHideFragment(showFragment, preFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    /**
     * 通过String刷新页面通过重写refresh(msg);
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Class<?> clazz = event.getClazz();
        if (null == clazz) {
            String msg = event.getMsg();
            if (TextUtils.isEmpty(msg)) {
            } else {
                if (Constants.REFRESH_ALL.equals(msg)) {
                    if (this instanceof MainActivity) return;
                    initData();
                } else {
                    refresh(msg);
                }
            }
        } else {
            String name = clazz.getName();
            if (TextUtils.isEmpty(name)) {

            } else {
                String clazzName = this.getClass().getName();
                if (name.equals(clazzName)) {
                    initData();
                }
            }
        }
    }

    /**
     * 统一封装加载进度显示
     */
    @Override
    public void showLoading() {
        if (null == srl || intercept) {
            showProgressing();
        }
    }

    protected void showProgressing() {
        if (!isFinishing() && loadingView != null) {
            loadingView.show();
        }
    }

    /**
     * 统一封装加载进度隐藏
     */
    @Override
    public void hideLoading() {
        if (null == srl || intercept) {
            hideProgressing();
        } else {
            if (pullToRefresh) {
                srl.finishRefresh();
            } else {
                srl.finishLoadMore();
            }
        }
    }

    protected void hideProgressing() {
        if (!isFinishing() && loadingView != null && loadingView.isShowing()) {
            loadingView.dismiss();
        }
    }

    /**
     * 错误页面
     */
    @Override
    public void error() {
        if (null != slRoot)
            slRoot.setErrorView(view -> {
                if (null != srl) {
                    srl.autoRefresh();
                } else
                    initData();
//                slRoot.setContentView();
            });
    }


    @Override
    public void noneData() {
        if (null != slRoot)
            slRoot.setEmptyView();
    }

    /**
     * 无数据页面
     *
     * @param drawableId
     * @param description
     */
    @Override
    public void noneData(int drawableId, String description) {
        if (null != slRoot)
            slRoot.setEmptyView(drawableId, description);
    }

    @Override
    public void noneData(int drawableId, String description, String btnText, View.OnClickListener clickListener) {
        if (null != slRoot)
            slRoot.setEmptyView(drawableId, description, btnText, clickListener);
    }

    @Override
    public <T> void otherState(BaseResponse<T> response) {

    }

    @Override
    public void finishNet() {

    }

    public abstract int getLayoutId();

    /**
     * 绑定布局
     */
    protected abstract void initView();

    protected abstract void initData();

    /**
     * 局部刷新使用
     *
     * @param msg
     */
    protected void refresh(String msg) {
    }
}
