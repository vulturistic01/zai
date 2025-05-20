package com.zai.weather.service;

import com.zai.weather.model.WeatherModel;

public interface WeatherServiceImpl {

    WeatherModel fetchWeather(String city);
}
