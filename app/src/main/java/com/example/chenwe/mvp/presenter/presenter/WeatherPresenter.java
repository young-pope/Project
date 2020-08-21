package com.example.chenwe.mvp.presenter.presenter;

import android.content.Context;

import com.example.chenwe.mvp.bean.WeatherData;
import com.example.chenwe.mvp.contract.WeatherContract;
import com.example.chenwe.mvp.model.HttpServerUtils;
import com.example.chenwe.mvp.presenter.framework.Presenter;

import java.util.HashMap;

/**
 * Created by chenwe on 2020/8/20.
 */

public class WeatherPresenter extends Presenter implements WeatherContract.Presenter{

    private final Context mContext;
    private final WeatherContract.View mView;

    public WeatherPresenter(WeatherContract.View view, Context context) {
        this.mContext = context;
        this.mView = view;
    }
    public void getWeatherData(HashMap map) {
        HttpServerUtils.WeatherPortal(this,map);
    }
    @Override
    public void WeatherQuerySuccess(WeatherData weatherData) {
        mView.WeatherQuerySuccess(weatherData);
    }

    @Override
    public void WeatherQueryFailure(String string) {
    mView.WeatherQueryFailure(string);
    }
}
