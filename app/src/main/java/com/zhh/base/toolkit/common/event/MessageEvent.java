package com.zhh.base.toolkit.common.event;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/10/19
 * Change:
 */
public class MessageEvent {
    private String msg;
    public String getMsg() {
        return msg;
    }
    public MessageEvent(String msg) {
        this.msg = msg;
    }


    private Class<?> clazz;
    public Class<?> getClazz() {
        return clazz;
    }
    public MessageEvent(Class<?> clazz) {
        this.clazz = clazz;
    }
}
