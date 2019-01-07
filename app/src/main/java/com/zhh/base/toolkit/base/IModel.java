package com.zhh.base.toolkit.base;

import com.zhh.base.toolkit.utils.internet.ResponseCallback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/9/19
 * Change:
 */
public interface IModel {
    default <T> void net(@NonNull Observable<BaseResponse<T>> observable, @NonNull ResponseCallback callback) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryDelay(3, 2))
                .doOnSubscribe(disposable -> callback.showLoading())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        callback.hideLoading();
                    }
                })
                .subscribe(new Observer<BaseResponse<T>>() {
                    private Disposable d;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.d = d;
                    }

                    @Override
                    public void onNext(BaseResponse<T> response) {
                        switch (response.code) {
                            case "0":
                                callback.loadSuccess(response.data);
                                break;
                            default:
                                callback.loadFailure(response.code, response.message);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != callback)
                            callback.finishNet();
                        callback.loadError(e);
                    }

                    @Override
                    public void onComplete() {
                        if (null != callback)
                            callback.finishNet();
                        d.dispose();
                    }
                });
    }
}
