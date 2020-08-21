package com.example.chenwe.mvp.contract;


import com.example.chenwe.mvp.bean.WeatherData;

/**
 * Created by chenwe on 2020/8/20.
 */

public class WeatherContract {

    public interface View{
        void WeatherQuerySuccess(WeatherData weatherData);
        void WeatherQueryFailure(String string);
    }

    public interface Presenter{
        void WeatherQuerySuccess(WeatherData weatherData);
        void WeatherQueryFailure(String string);
    }
}
