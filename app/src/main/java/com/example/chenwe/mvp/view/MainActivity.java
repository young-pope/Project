package com.example.chenwe.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenwe.mvp.R;
import com.example.chenwe.mvp.bean.WeatherData;
import com.example.chenwe.mvp.contract.WeatherContract;
import com.example.chenwe.mvp.presenter.framework.WrapperPresenter;
import com.example.chenwe.mvp.presenter.presenter.WeatherPresenter;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements WeatherContract.View,View.OnClickListener{

  private WeatherPresenter weatherPresenter;
    private EditText inputCityEdit;
    private TextView weatherMessageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPresenter();

    }

    public void initView(){

        inputCityEdit = findViewById(R.id.input_city_et);
        Button queryButton = findViewById(R.id.query_bt);
        queryButton.setOnClickListener(this);
        weatherMessageText = findViewById(R.id.weather_message_tv);

    }
    public void initPresenter(){

        if (weatherPresenter == null){
            weatherPresenter = new WeatherPresenter(this, this);
            WrapperPresenter.getInstance().add(weatherPresenter);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.query_bt:
                if (inputCityEdit.getText().toString().length() == 0 || TextUtils.isEmpty(inputCityEdit.getText())){

                    Toast.makeText(this, "城市名不能为空！", Toast.LENGTH_SHORT).show();

                }else {
                    //聚合数据 接口请求次数有限
                    HashMap<String, String> map = new HashMap<>();
                    map.put("cityname",inputCityEdit.getText().toString());
                    map.put("key","7763f3e73a13f244bd6c7337660fd8bd");
                    weatherPresenter.getWeatherData(map);
                }

                break;
        }
    }

    @Override
    public void WeatherQuerySuccess(WeatherData weatherData) {
        WeatherData.ResultBean result = weatherData.getResult();
        WeatherData.ResultBean.TodayBean today = result.getToday();
        Log.d("cwf", "WeatherQuerySuccess："+weatherData.getResultcode());

        weatherMessageText.setText("城市："+today.getCity()+"\n"+
                "\n"+"日期："+today.getDate_y()+"\n"+
                "\n"+"温度："+today.getTemperature()+"\n"+
                "\n"+"风向："+today.getWind()+"\n"+
                "\n"+"穿着："+today.getDressing_advice()+"\n");

    }

    @Override
    public void WeatherQueryFailure(String string) {

        Log.d("cwf", "WeatherQueryFailure: "+string);

    }
}
