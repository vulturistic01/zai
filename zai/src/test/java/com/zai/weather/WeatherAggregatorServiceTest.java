package com.zai.weather;

import com.zai.weather.model.WeatherModel;
import com.zai.weather.service.OpenWeatherMapService;
import com.zai.weather.service.WeatherAggregatorService;
import com.zai.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherAggregatorServiceTest {
    private WeatherAggregatorService service;
    private WeatherService weatherService;
    private OpenWeatherMapService openWeatherMapService;

    @BeforeEach
    void setUp() {
        weatherService = mock(WeatherService.class);
        openWeatherMapService = mock(OpenWeatherMapService.class);
        service = new WeatherAggregatorService(weatherService,openWeatherMapService);
    }

    @Test
    void shouldUsePrimaryWeatherProvider() {
        when(weatherService.fetchWeather("melbourne"))
                .thenReturn(new WeatherModel(28, 10));

        WeatherModel response = service.getWeather("melbourne");
        assertEquals(28, response.temperature_degrees);
        assertEquals(10, response.wind_speed);
    }

    @Test
    void shouldFailoverToBackupProvider() {
        when(weatherService.fetchWeather("melbourne"))
                .thenThrow(new RuntimeException("Primary down"));
        when(openWeatherMapService.fetchWeather("melbourne"))
                .thenReturn(new WeatherModel(22, 7));

        WeatherModel response = service.getWeather("melbourne");
        assertEquals(22, response.temperature_degrees);
        assertEquals(7, response.wind_speed);
    }
}
