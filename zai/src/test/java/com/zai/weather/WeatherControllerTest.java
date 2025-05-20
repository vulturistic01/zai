package com.zai.weather;

import com.zai.weather.controller.WeatherController;
import com.zai.weather.model.WeatherModel;
import com.zai.weather.service.WeatherAggregatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherAggregatorService weatherAggregatorService;

    @Test
    public void testGetWeather() throws Exception {
        Mockito.when(weatherAggregatorService.getWeather("melbourne"))
                .thenReturn(new WeatherModel(25, 12));

        mockMvc.perform(get("/v1/weather?city=melbourne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature_degrees", is(25)))
                .andExpect(jsonPath("$.wind_speed", is(12)));
    }
}