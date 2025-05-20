package com.zai.weather.model;

public class WeatherModel {
    public int temperature_degrees;
    public int wind_speed;

    public WeatherModel(int temperature_degrees, int wind_speed) {
        this.temperature_degrees = temperature_degrees;
        this.wind_speed = wind_speed;
    }
}