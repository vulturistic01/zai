package com.zai.weather.service;

import com.zai.weather.model.WeatherModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeatherService implements WeatherServiceImpl {
    private final String API_KEY = "8adfee2da155e271a1daee669a864945";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherModel fetchWeather(String city) {
        String url = String.format("http://api.weatherstack.com/current?access_key=%s&query=%s", API_KEY, city);
        try {
            Map response = restTemplate.getForObject(url, Map.class);
            Map<String, Object> current = (Map<String, Object>) response.get("current");
            return new WeatherModel((int) current.get("temperature"), (int) current.get("wind_speed"));
        } catch (Exception e) {
            throw new RuntimeException("WeatherStack failed", e);
        }
    }
}