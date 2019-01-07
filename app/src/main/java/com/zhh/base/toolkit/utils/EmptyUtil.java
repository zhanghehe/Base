package com.zhh.base.toolkit.utils;

import java.util.List;


/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/8/30
 * Change:
 */
public class EmptyUtil {
    public static boolean isList(List list) {
        return null == list || list.size() == 0;
    }

    public static boolean isString(String imgUrl) {
        return null==imgUrl||"".equals(imgUrl);
    }
}
