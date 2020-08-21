package com.example.chenwe.mvp.api;


import com.example.chenwe.mvp.bean.WeatherData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


/**
 * Created by WeiFei Chen on 2020/7/16.
 */

public interface HttpService {

    /**
     * 聚合 天气查询接口
     */
    @GET("index?")
    Call<WeatherData> getweatherQuery(@QueryMap Map<String, String> parmas);


}
