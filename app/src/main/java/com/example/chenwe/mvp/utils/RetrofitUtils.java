package com.example.chenwe.mvp.utils;



import com.example.chenwe.mvp.api.HttpService;
import com.example.chenwe.mvp.constant.ApiConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WeiFei Chen on 2020/7/16.
 */

public class RetrofitUtils {

    private static final HttpService mServiceApi ;

    public static HttpService getServiceApi() {
        return mServiceApi;
    }
    static {
//        TODO 请求头
//        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
//                .addHeaderParams("source", "android")
//                .addHeaderParams("appVersion", "101")
//                .build();
//                .addInterceptor(commonInterceptor)
        // Builder设计模式
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //请求超时时间
                .connectTimeout(20, TimeUnit.SECONDS)
                //写入超时时间
                .writeTimeout(20, TimeUnit.SECONDS)
                //读取超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                //安全HTTPS连接的套接字工厂和信任管理器
                .sslSocketFactory(HttpsTrustManager.createSSLSocketFactory())
                //用于主机名验证 验证服务器ca证书中的host是否和请求地址host一致
                .hostnameVerifier(new HttpsTrustManager.TrustAllHostnameVerifier())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                // 访问后台接口的主路径
                .baseUrl(ApiConstant.URL)
                // 添加解析转换工厂，Gson解析、Xml解析
                .addConverterFactory(GsonConverterFactory.create())
                // 添加OkHttpClient，不添加就是默认的光杆 OkHttpClient
                .client(okHttpClient)
                .build();
        // 创建一个实例对象，静态代理模式
        mServiceApi = retrofit.create(HttpService.class);
    }

}
