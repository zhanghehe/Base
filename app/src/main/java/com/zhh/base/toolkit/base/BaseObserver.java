package com.zhh.base.toolkit.base;

import com.zhh.base.toolkit.common.Api;
import com.zhh.base.toolkit.utils.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/11/2
 * Change:
 */
public abstract class BaseObserver<K> implements Observer<BaseResponse<K>>{
    private Disposable d;
    private IView view;

    public BaseObserver(IView view) {
        this.view = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(BaseResponse<K> t) {
        switch (t.code) {
            case Api.API_SUCCESS:
                loadSuccess(t.data);
                break;
            default:
                if (intercept(t)) return;
                ToastUtil.toast(t.message);
                break;
        }
    }

    protected boolean intercept(BaseResponse<K> t){
        return false;
    }

    protected abstract void loadSuccess(K data);

    @Override
    public void onError(Throwable e) {
        d.dispose();
    }

    @Override
    public void onComplete() {
        d.dispose();
    }

    public void showLoading() {
        view.showLoading();
    }

    public void hideLoading() {
        view.hideLoading();
    }
}