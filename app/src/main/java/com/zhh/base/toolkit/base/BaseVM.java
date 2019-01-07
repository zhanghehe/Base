package com.zhh.base.toolkit.base;

import android.arch.lifecycle.LifecycleOwner;
import android.net.ParseException;
import android.support.annotation.NonNull;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.zhh.base.toolkit.utils.ToastUtil;
import com.zhh.base.toolkit.utils.internet.ResponseCallback;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/9/26
 * Change:
 */
public abstract class BaseVM implements ResponseCallback {
    private IView view;
    //页码：1为首页标记
    protected int pageNo = 1;

    public BaseVM(IView view) {
        this.view = view;
    }

    public void showLoading() {
        view.showLoading();
    }

    public void hideLoading() {
        view.hideLoading();
    }

    @Override
    public void loadFailure(String code, String msg) {
        ToastUtil.toast(msg);
    }


    public void loadError(Throwable t) {
        if (1 == pageNo) {
            view.error();
        } else {
            String msg = "未知错误";
            if (t instanceof UnknownHostException || t instanceof ConnectException) {
                msg = "暂无网络，请稍后再试";
            } else if (t instanceof SocketTimeoutException) {
                msg = "请求网络超时";
            } else if (t instanceof HttpException) {
                HttpException httpException = (HttpException) t;
                msg = convertStatusCode(httpException);
            } else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException || t instanceof JsonIOException) {
                msg = "数据解析错误";
            }
            ToastUtil.toast(msg);
        }
    }

    public void otherState(BaseResponse response) {

    }

    public void finishNet() {
        view.finishNet();
    }


    private String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

}
