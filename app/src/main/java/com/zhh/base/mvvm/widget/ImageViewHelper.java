package com.zhh.base.mvvm.widget;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Describe:DataBind模式下ImageView的辅助类，不支持的功能实践在此实现
 * Author:MysteryCode
 * Data:2018/10/10
 * Change:
 */

public class ImageViewHelper {
    /**
     * mv_vm xml 传入url 加载图片
     * imageUrl 为xml中 的命名
     *
     * @param iv  imageView
     * @param url 图片路径
     */
    @BindingAdapter({"imageUrl", "placeHolder", "circleCrop"})
    public static void loadUrl(ImageView iv, String url, Drawable placeHolder, boolean circleCrop) {
        RequestOptions options;
        if (circleCrop) {
            options = new RequestOptions()
                    .placeholder(placeHolder)
                    .error(placeHolder)
                    .circleCrop();
        } else {
            options = new RequestOptions()
                    .placeholder(placeHolder)
                    .error(placeHolder);
        }
        Glide.with(iv.getContext()).load(url).apply(options).into(iv);
    }

    /**
     * mv_vm xml 传入url 加载图片
     * imageUrl 为xml中 的命名
     *
     * @param iv  imageView
     * @param url 图片路径
     */
    @BindingAdapter({"imageUrl", "placeHolder"})
    public static void loadUrl(ImageView iv, String url, Drawable placeHolder) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolder)
                .error(placeHolder);
        Glide.with(iv.getContext()).load(url).apply(options).into(iv);
    }

    /**
     * mv_vm xml 设置 drawable Resource
     *
     * @param iv    imageView
     * @param resId resource id
     */
    @BindingAdapter({"src"})
    public static void loadSrc(ImageView iv, int resId) {
        iv.setImageResource(resId);
    }

}
