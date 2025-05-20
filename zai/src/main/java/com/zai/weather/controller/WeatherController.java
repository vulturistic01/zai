package com.zai.weather.controller;

import com.zai.weather.model.WeatherModel;
import com.zai.weather.service.WeatherAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    @Autowired
    private WeatherAggregatorService weatherAggregatorService;

    @GetMapping
    public ResponseEntity<WeatherModel> getWeather(@RequestParam(defaultValue = "melbourne") String city) {
        return ResponseEntity.ok(weatherAggregatorService.getWeather(city));
    }
}