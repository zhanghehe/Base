package com.zhh.base.toolkit.utils.internet;

import com.zhh.base.toolkit.base.BaseApplication;
import com.zhh.base.toolkit.common.Api;
import com.zhh.base.toolkit.common.ApiService;
import com.zhh.base.toolkit.utils.LogUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import me.jessyan.progressmanager.ProgressManager;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Describe:
 * Author:MysteryCode
 * Data:2018/9/19
 * Change:
 */
public class Net {
    private Retrofit mRetrofit;

    private Net() {
        Retrofit.Builder builder = new Retrofit.Builder();
        mRetrofit = builder.baseUrl(Api.BASE_URL)
                .client(genericClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static Net net = null;

    public static Net newInstance() {
        if (null == net) {
            synchronized (Net.class) {
                if (null == net) {
                    net = new Net();
                }
            }
        }
        return net;
    }

    public ApiService api() {
        return mRetrofit.create(ApiService.class);
    }

    public OkHttpClient genericClient() {
        long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
        String cacheFile = BaseApplication.context.getCacheDir() + "/http";
        Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
        ProgressManager.getInstance().with(builder);
        return builder.connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager)
                .cache(cache)
                /*.addInterceptor(chain -> {
                    Request original = chain.request();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("accessToken", SpUtil.getString(Constant.ACCESS_TOKEN));
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })*/
                .addInterceptor(new HttpLoggingInterceptor(message -> LogUtil.e(message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
}
