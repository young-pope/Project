package com.example.chenwe.mvp.model;


import com.example.chenwe.mvp.bean.WeatherData;
import com.example.chenwe.mvp.contract.WeatherContract;
import com.example.chenwe.mvp.utils.RetrofitUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by chenwe on 2020/8/20.
 */

public class HttpServerUtils {

    /**
     * 天气查询
     *
     * @param presenter
     */
    public static void WeatherPortal(final WeatherContract.Presenter presenter, HashMap map) {

        Call<WeatherData> call = RetrofitUtils.getServiceApi().getweatherQuery(map);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                if (response.body() != null){

                    presenter.WeatherQuerySuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

                presenter.WeatherQueryFailure(t.toString());
            }
        });


    }

}
