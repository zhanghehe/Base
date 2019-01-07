package com.zhh.base.toolkit.utils.internet;

import com.zhh.base.toolkit.base.BaseResponse;


/**
 * Describe:网络回调接口
 * Author:MysteryCode
 * Data:2018/9/25
 * Change:
 */
public interface ResponseCallback{
    /**
     * 加载进度展示
     */
    void showLoading();

    /**
     * 加载进度结束
     */
    void hideLoading();

    /**
     * 加载失败
     * @param msg
     */
    void loadFailure(String code, String msg);

    /**
     * 加载正常数据
     * @param data
     * @param <T> 通过instanceof判断后强转
     */
    <T> void loadSuccess(T data);

    /**
     * 加载出错（网络错误：网络异常/返回数据异常）
     */
    void loadError(Throwable t);

    /**
     * 备用特别Code做特殊处理
     * @param response
     * @param <T>
     */
    <T> void otherState(BaseResponse<T> response);

    /**
     * 网络连接结束
     */
    void finishNet();
}
