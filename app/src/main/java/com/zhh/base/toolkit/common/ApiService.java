package com.zhh.base.toolkit.common;

import com.zhh.base.toolkit.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Describe:所有Api管理类
 * Author:MysteryCode
 * Data:2018/9/19
 * Change:
 */
public interface ApiService {
    /**
     * 1.1.9.	检查手机号是否注册接口
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("userRegister/checkPhone")
    Observable<BaseResponse<Object>> checkPhone(@FieldMap Map<String, Object> map);
}
