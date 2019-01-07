package com.zhh.base.toolkit.utils.internet;

/**
 * Describe:网络回调接口
 * Author:MysteryCode
 * Data:2018/9/25
 * Change:
 */
public interface UploadFileCallback {
    /**
     * 加载进度展示
     */
    default  void showLoading(){};

    /**
     * 加载进度结束
     */
    default void hideLoading(){};

    /**
     * 加载失败
     * @param msg
     */
    default void loadFailure(String code, String msg){};

    /**
     * 加载正常数据
     * @param data
     * @param <T> 通过instanceof判断后强转
     */
    <T> void uploadSuccess(T data);

    /**
     * 加载出错（网络错误：网络异常/返回数据异常）
     */
    default void loadError(Throwable t){};

    /**
     * 网络连接结束
     */
    default  void finishNet(){};
}
