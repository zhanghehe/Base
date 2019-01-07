package com.zhh.base.toolkit.base;

import android.view.View;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/9/19
 * Change:
 */
public interface IView {

    void showLoading();

    void hideLoading();

    void error();

    /**
     * 默认
     */
    void noneData();

    /**
     * 包含图片文字
     * @param drawableId
     * @param description
     */
    void noneData(int drawableId, String description);


    /**
     * 包含按钮的点击事件
     * @param drawableId
     * @param description
     * @param btnText
     * @param clickListener
     */
    void noneData(int drawableId, String description, String btnText, View.OnClickListener clickListener);

    <T>void otherState(BaseResponse<T> response);

    void finishNet();
}
