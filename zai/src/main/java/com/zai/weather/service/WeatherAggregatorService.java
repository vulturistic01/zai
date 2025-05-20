package com.zai.weather.service;

import com.zai.weather.model.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class WeatherAggregatorService {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @Autowired
    public WeatherAggregatorService(WeatherService weatherService,
                                    OpenWeatherMapService openWeatherMapService) {
        this.weatherService = weatherService;
        this.openWeatherMapService = openWeatherMapService;
    }

    private final AtomicReference<WeatherModel> cachedResponse = new AtomicReference<>();
    private Instant lastUpdated = Instant.EPOCH;

    public WeatherModel getWeather(String city) {
        Instant now = Instant.now();
        if (now.isBefore(lastUpdated.plusSeconds(3)) && cachedResponse.get() != null) {
            return cachedResponse.get();
        }

        try {
            WeatherModel response = weatherService.fetchWeather(city);
            updateCache(response);
            return response;
        } catch (Exception e1) {
            try {
                WeatherModel response = openWeatherMapService.fetchWeather(city);
                updateCache(response);
                return response;
            } catch (Exception e2) {
                if (cachedResponse.get() != null) {
                    return cachedResponse.get();
                } else {
                    throw new RuntimeException("All weather providers are down and no cached data is available");
                }
            }
        }
    }

    private void updateCache(WeatherModel response) {
        cachedResponse.set(response);
        lastUpdated = Instant.now();
    }
}