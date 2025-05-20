package com.zai.weather.service;

import com.zai.weather.model.WeatherModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OpenWeatherMapService implements WeatherServiceImpl {
    private final String API_KEY = "725845541008580fb45c527d8afa7f6d";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherModel fetchWeather(String city) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,AU&appid=%s&units=metric", city, API_KEY);
        try {
            System.out.println("URL:  " + url  );
            Map response = restTemplate.getForObject(url, Map.class);
            Map<String, Object> main = (Map<String, Object>) response.get("main");
            Map<String, Object> wind = (Map<String, Object>) response.get("wind");
            return new WeatherModel((int) Math.round((Double) main.get("temp")),
                    (int) Math.round((Double) wind.get("speed")));
        } catch (Exception e) {
            throw new RuntimeException("OpenWeatherMap failed", e);
        }
    }
}